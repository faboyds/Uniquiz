package controller;

import dto.QuizDTO;
import model.Quiz;
import model.Rating;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.QuizRepository;

import javax.persistence.NoResultException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by fabiolourenco on 08/09/17.
 */
@RestController
@RequestMapping("/quizzes")
public class QuizController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<QuizDTO>> findAll(@RequestParam(required=false) Long subjectPk,
                                                    @RequestParam(required = false) Long coursePk){
        QuizRepository repo = new QuizRepository();
        List<QuizDTO> quizList = new LinkedList<>();

        if(subjectPk != null){
            for(Quiz quiz : repo.findBySubject(subjectPk)){
                quizList.add(quiz.toDTO());
            }
        }else if(subjectPk == null && coursePk == null){
            for(Quiz quiz : repo.findAll()){
                quizList.add(quiz.toDTO());
            }
        }else if(coursePk != null){
            for(Quiz quiz : repo.findByCourse(coursePk)){
                quizList.add(quiz.toDTO());
            }
        }
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{quizPk}")
    public ResponseEntity<QuizDTO> findOne(@PathVariable Long quizPk ) {
        try {
            QuizRepository repo = new QuizRepository();

            QuizDTO quiz = repo.findOne(quizPk).get().toDTO();

            return new ResponseEntity<>(quiz, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/quiz")
    public ResponseEntity<List<QuizDTO>> findByTitle(@RequestParam("title") String title) {
        try {
            QuizRepository repo = new QuizRepository();

            List<QuizDTO> quizzes = new LinkedList<>();
            for(Quiz quiz : repo.findByTitle(title)){
                quizzes.add(quiz.toDTO());
            }

            return new ResponseEntity<>(quizzes, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<QuizDTO> add(@RequestBody Quiz quiz) {
        try {
            if(quiz.getPk() != null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            QuizRepository repo = new QuizRepository();

            Quiz newQuiz = new Quiz(quiz);
            quiz = repo.save(newQuiz);

            return new ResponseEntity<>(quiz.toDTO(), HttpStatus.CREATED);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{quizPk}")
    public ResponseEntity<QuizDTO> update(@PathVariable Long quizPk, @RequestBody Quiz quiz) {
        try {
            QuizRepository repo = new QuizRepository();
            Quiz newQuiz = repo.findOne(quizPk).get();

            newQuiz.setPk(quiz.getPk());
            newQuiz.setCoursePk(quiz.getCoursePk());
            newQuiz.setDifficulty(quiz.getDifficulty());
            newQuiz.setPopularityCounter(quiz.getPopularityCounter());
            newQuiz.setQuestions(quiz.getQuestions());
            newQuiz.setTitle(quiz.getTitle());

            newQuiz = repo.save(newQuiz);

            return new ResponseEntity<>(newQuiz.toDTO(), HttpStatus.OK);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rate/{quizPk}")
    public ResponseEntity<QuizDTO> rateQuiz(@PathVariable Long quizPk, @RequestBody Rating rating) {
        try {
            QuizRepository repo = new QuizRepository();
            Quiz newQuiz = repo.findOne(quizPk).get();

            newQuiz.addRating(rating);

            newQuiz = repo.save(newQuiz);

            return new ResponseEntity<>(newQuiz.toDTO(), HttpStatus.OK);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
