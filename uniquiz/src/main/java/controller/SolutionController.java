package controller;

import dto.SolutionDTO;
import model.Solution;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.SolutionRepository;
import services.QuizzesPopularityService;
import services.StatisticsService;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by fabiolourenco on 09/09/17.
 */
@RestController
@RequestMapping("/solutions")
public class SolutionController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SolutionDTO>> findAll(){
        SolutionRepository repo = new SolutionRepository();

        List<SolutionDTO> solutionList = new LinkedList<>();
        for(Solution solution : repo.findAll()){
            solutionList.add(solution.toDTO());
        }
        return new ResponseEntity<>(solutionList, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{solutionPk}")
    public ResponseEntity<SolutionDTO> findOne(@PathVariable Long solutionPk ) {
        try {
            SolutionRepository repo = new SolutionRepository();

            SolutionDTO solution = repo.findOne(solutionPk).get().toDTO();

            return new ResponseEntity<>(solution, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/latest")
    public ResponseEntity<SolutionDTO> findOne(@RequestParam("quiz") Long quiz,
                                                @RequestParam("email") String email) {
        try {
            SolutionRepository repo = new SolutionRepository();

            SolutionDTO solution = repo.findLatestByQuizAndEmail(quiz, email).toDTO();

            return new ResponseEntity<>(solution, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SolutionDTO> add(@RequestBody Solution solution) {
        try {
            if(solution.getPk() != null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            SolutionRepository repo = new SolutionRepository();

            solution.setSolvedOn(LocalDateTime.now());

            solution = repo.save(solution);

            new StatisticsService().recalculateUserStatistics(solution);
            new QuizzesPopularityService().incrementPopularity(solution);

            return new ResponseEntity<>(solution.toDTO(), HttpStatus.CREATED);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<SolutionDTO> update(@RequestBody Solution solution) {
        try {
            SolutionRepository repo = new SolutionRepository();
            Solution newSolution = repo.findOne(solution.getPk()).get();

            newSolution.setPk(solution.getPk());
            newSolution.setQuizPk(solution.getQuizPk());
            newSolution.setRightAnswers(solution.getRightAnswers());
            newSolution.setWrongAnswers(solution.getWrongAnswers());
            newSolution.setSolvedOn(solution.getSolvedOn());
            newSolution.setEmail(solution.getEmail());

            newSolution = repo.save(newSolution);

            return new ResponseEntity<>(newSolution.toDTO(), HttpStatus.OK);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

