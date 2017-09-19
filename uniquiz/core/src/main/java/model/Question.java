package model;

import dto.AnswerDTO;
import dto.QuestionDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by fabiolourenco on 08/09/17.
 */
@Entity
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long pk;

    @Version
    private Long version;

    /**
     * Question content
     */
    private String question;

    /**
     * List of possible answers
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "QUESTION_PK")
    private List<Answer> answers;

    /**
     * Question's right answer Justification
     */
    private String justification;

    public Question() {
        answers = new LinkedList<>();
    }

    public Question(String question) {
        this.question = question;
        this.answers = new LinkedList<>();
    }

    /**
     * Method to get the Database Pk
     * @return
     */
    public Long getPk() {
        return pk;
    }

    /**
     * Method to set the DataBase pk
     * @param pk
     */
    public void setPk(Long pk) {
        this.pk = pk;
    }

    /**
     * Method to get the question's content
     * @return
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Method to set the question's content
     * @param question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Method to get the list of answers
     * @return
     */
    public List<Answer> getAnswers() {
        return answers;
    }

    /**
     * Method to set the list of answers
     * @param answers
     */
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        return pk != null ? pk.equals(question.pk) : question.pk == null;
    }

    @Override
    public int hashCode() {
        return pk != null ? pk.hashCode() : 0;
    }

    public QuestionDTO toDTO(){
        List<AnswerDTO> answers = new LinkedList<>();
        for(Answer a : this.answers){
            answers.add(a.toDTO());
        }
        return new QuestionDTO(this.pk, this.question, answers, justification);
    }
}
