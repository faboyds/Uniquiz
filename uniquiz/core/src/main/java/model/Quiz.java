package model;

import dto.QuestionDTO;
import dto.QuizDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Quiz implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long pk;

    @Version
    private Long version;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "QUIZ_PK")
    private List<Question> questions;

    private Difficulty difficulty;

    private Long subjectPk;
    private String subjectName;

    private Long coursePk;
    private String courseName;

    private String title;

    private long popularityCounter;

    private int numQuestionsPerSolution;

    private int totalNumQuestions;

    private double averageRating;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "QUIZ_PK")
    private List<Rating> ratings;

    public Quiz() {
        questions = new LinkedList<>();
    }

    public Quiz(Difficulty difficulty, Long subjectPk, String subjectName, Long coursePk,
                String courseName, String title, int numQuestionsPerSolution, List<Question> questions) {

        setDifficulty(difficulty);
        setSubjectPk(subjectPk);
        setSubjectName(subjectName);
        setCoursePk(coursePk);
        setCourseName(courseName);
        setTitle(title);
        setQuestions(questions);
        setNumQuestionsPerSolution(numQuestionsPerSolution);
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Long getSubjectPk() {
        return subjectPk;
    }

    public void setSubjectPk(Long subjectPk) {
        if(subjectPk < 1) throw new IllegalArgumentException("Subject pk must not be null.");
        this.subjectPk = subjectPk;
    }

    public Long getCoursePk() {
        return coursePk;
    }

    public void setCoursePk(Long coursePk) {
        if(coursePk < 1) throw new IllegalArgumentException("Course pk must not be null.");
        this.coursePk = coursePk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title == null || title.isEmpty()) throw new IllegalArgumentException("The quiz must have a title.");
        this.title = title;
    }

    public String getSubjectName(){
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        this.totalNumQuestions = questions.size();
    }

    public long getPopularityCounter() {
        return popularityCounter;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void incrementPopularity(){
        this.popularityCounter++;
    }

    public int getNumQuestionsPerSolution() {
        return numQuestionsPerSolution;
    }

    public void setNumQuestionsPerSolution(int numQuestionsPerSolution) {
        if(numQuestionsPerSolution < 0 || numQuestionsPerSolution > totalNumQuestions)
            throw new IllegalArgumentException("To resolve a quiz, must be shown at least 1 question and less than the total question.");
        this.numQuestionsPerSolution = numQuestionsPerSolution;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public int getTotalNumQuestions() {
        return totalNumQuestions;
    }

    public void addRating(Rating rating){
        averageRating = (averageRating * ratings.size() + rating.getValue()) / (ratings.size()+1);
        ratings.add(rating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quiz quiz = (Quiz) o;

        return pk != null ? pk.equals(quiz.pk) : quiz.pk == null;
    }

    @Override
    public int hashCode() {
        return pk != null ? pk.hashCode() : 0;
    }

    public QuizDTO toDTO(){
        List<QuestionDTO> questions = new LinkedList<>();
        for(Question q : this.questions){
            questions.add(q.toDTO());
        }
        return new QuizDTO(this.pk, questions, difficulty.name(), this.subjectPk, this.subjectName, this.coursePk, this.courseName, this.title, popularityCounter, this.averageRating);
    }
}
