package controller;

import dto.UserDTO;
import model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.UserRepository;

import javax.persistence.NoResultException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by fabiolourenco on 09/09/17.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll(){
        UserRepository repo = new UserRepository();

        List<UserDTO> userList = new LinkedList<>();
        for(User user : repo.findAll()){
            userList.add(user.toDTO());
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{username}")
    public ResponseEntity<UserDTO> findOne(@PathVariable String username ) {
        try {
            UserRepository repo = new UserRepository();

            UserDTO user = repo.findByUsername(username).toDTO();

            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDTO> add(@RequestBody User user) {
        try {
            UserRepository repo = new UserRepository();
            user = repo.save(user);

            return new ResponseEntity<>(user.toDTO(), HttpStatus.CREATED);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> update(@RequestBody User user) {
        try {
            UserRepository repo = new UserRepository();
            User newUser = repo.findOne(user.getEmail()).get();

            newUser.setEmail(user.getEmail());
            newUser.setName(user.getName());
            newUser.setPassword(user.getPassword());
            newUser.setUsername(user.getUsername());
            newUser.setRoles(user.getRoles());

            newUser = repo.save(newUser);

            return new ResponseEntity<>(newUser.toDTO(), HttpStatus.OK);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
