package services;

import model.Solution;
import model.User;
import model.UserStatistics;
import repositories.UserRepository;

/**
 * Created by fabiolourenco on 09/09/17.
 */
public class StatisticsService {
    public synchronized void recalculateUserStatistics(Solution solution){
        //new Thread(new Runnable() {
          //  @Override
          //  public void run() {
                UserRepository userRepository = new UserRepository();
                User user = userRepository.findOne(solution.getEmail()).get();

                UserStatistics userStatistics = user.getUserStatistics();
                userStatistics.addRightAnswers(solution.getRightAnswers());
                userStatistics.addWrongAnswers(solution.getWrongAnswers());
                userStatistics.incrementSolvedQuiz(solution.getRightAnswers() >= solution.getWrongAnswers());

                userRepository.save(user);
            }
        //}).start();
    }

