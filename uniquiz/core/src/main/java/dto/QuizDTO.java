package dto;

import java.util.List;

/**
 * Created by Rafael Santos on 08-09-2017.
 */
public class QuizDTO {

    private Long pk;
    private List<QuestionDTO> questions;
    private String difficulty;
    private Long subjectPk;
    private String subjectName;
    private Long coursePk;
    private String courseName;
    private String title;
    private Long popularityCounter;
    private Double averageRating;

    public QuizDTO(Long pk, List<QuestionDTO> questions, String difficulty,
                    Long subjectPk, String subjectName, Long coursePk, String courseName, String title,
                   long popularityCounter, double averageRating){
        this.setPk(pk);
        this.setQuestions(questions);
        this.setDifficulty(difficulty);
        this.setSubjectPk(subjectPk);
        this.setSubjectName(subjectName);
        this.setCoursePk(coursePk);
        this.setCourseName(courseName);
        this.setTitle(title);
        this.setPopularityCounter(popularityCounter);
        this.setAverageRating(averageRating);
    }

    public QuizDTO() {
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Long getSubjectPk() {
        return subjectPk;
    }

    public void setSubjectPk(Long subjectPk) {
        this.subjectPk = subjectPk;
    }

    public Long getCoursePk() {
        return coursePk;
    }

    public void setCoursePk(Long coursePk) {
        this.coursePk = coursePk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubjectName(){
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setPopularityCounter(Long popularityCounter) {
        this.popularityCounter = popularityCounter;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
