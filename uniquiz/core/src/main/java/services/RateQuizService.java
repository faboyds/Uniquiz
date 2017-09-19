package services;

import model.Quiz;
import model.Rating;
import repositories.QuizRepository;

/**
 * Created by pedroneto on 19/09/17.
 */
public class RateQuizService {

    public synchronized Quiz rateService(long quizPk, Rating rating){
        QuizRepository repo = new QuizRepository();
        Quiz newQuiz = repo.findOne(quizPk).get();

        newQuiz.addRating(rating);

        newQuiz = repo.save(newQuiz);
        return newQuiz;

    }

}
