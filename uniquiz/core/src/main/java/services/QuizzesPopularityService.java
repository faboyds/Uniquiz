package services;

import model.Quiz;
import model.Solution;
import model.User;
import model.UserStatistics;
import repositories.QuizRepository;
import repositories.UserRepository;

/**
 * Created by fabiolourenco on 09/09/17.
 */
public class QuizzesPopularityService {
    public synchronized static void incrementPopularity(Solution solution){

        QuizRepository quizRepository = new QuizRepository();
            Quiz quiz = quizRepository.findOne(solution.getQuizPk()).get();

            quiz.incrementPopularity();

            quizRepository.save(quiz);

    }
}
