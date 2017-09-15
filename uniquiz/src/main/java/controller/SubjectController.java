package controller;

import dto.SubjectDTO;
import model.Subject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.SubjectRepository;

import javax.persistence.NoResultException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SubjectDTO>> findAll(){
        SubjectRepository repo = new SubjectRepository();

        List<SubjectDTO> subjectList = new LinkedList<>();
        for(Subject subject : repo.findAll()){
            subjectList.add(subject.toDTO());
        }
        return new ResponseEntity<>(subjectList, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{subjectPk}")
    public ResponseEntity<SubjectDTO> findOne(@PathVariable Long subjectPk ) {
        try {
            SubjectRepository repo = new SubjectRepository();

            SubjectDTO subjectDTO = repo.findOne(subjectPk).get().toDTO();

            return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SubjectDTO> add(@RequestBody Subject subject) {
        try {
            if(subject.getPk() != null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            SubjectRepository repo = new SubjectRepository();
            subject = repo.save(subject);

            return new ResponseEntity<>(subject.toDTO(), HttpStatus.CREATED);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{subjectPk}")
    public ResponseEntity<SubjectDTO> update(@PathVariable Long subjectPk, @RequestBody Subject subject) {
        try {
            SubjectRepository repo = new SubjectRepository();
            Subject newSubject = repo.findOne(subjectPk).get();

            newSubject.setPk(subject.getPk());
            newSubject.setSubjectName(subject.getSubjectName());

            newSubject = repo.save(newSubject);

            return new ResponseEntity<>(newSubject.toDTO(), HttpStatus.OK);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
