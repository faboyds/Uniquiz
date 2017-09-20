package controller;

import dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import repositories.UserRepository;
import request.UserRequest;

/**
 * Created by Rafael Santos on 09-09-2017.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDTO> sendRequest(@RequestBody UserRequest userRequest) {
        try {
            return new ResponseEntity<>(
                    new UserRepository().findByUsernameAndPassword(userRequest.getUsername().trim(), userRequest.getPassword().trim()).toDTO(),
                    HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
