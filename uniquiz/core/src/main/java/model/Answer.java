package model;

import dto.AnswerDTO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fabiolourenco on 08/09/17.
 */
@Entity
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long pk;

    @Version
    private Long version;
    /**
     * Answer content
     */
    private String answer;
    /**
     * Boolean that informs if the answer is right or wrong
     */
    private boolean rightAnswer;

    public Answer() {
    }

    public Answer(String answer, boolean rightAnswer) {
        this.answer = answer;
        this.rightAnswer = rightAnswer;
    }

    /**
     * Method to get the Database Pk
     * @return pk
     */
    public Long getPk() {
        return pk;
    }

    /**
     * Method to change the pk
     * @param pk
     */
    public void setPk(Long pk) {
        this.pk = pk;
    }

    /**
     * Method to get the content of the answer
     * @return answer String
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Method to change the content of the answer
     * @param answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Method to check if the answer is right or wrong
     * @return
     */
    public boolean isRightAnswer() {
        return rightAnswer;
    }

    /**
     * Method to change if the answer is right or wron
     * @param rightAnswer
     */
    public void setRightAnswer(boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        return pk != null ? pk.equals(answer.pk) : answer.pk == null;
    }

    @Override
    public int hashCode() {
        return pk != null ? pk.hashCode() : 0;
    }

    /**
     * Method to convert this object into a AnswerDTO object
     * @return
     */
    public AnswerDTO toDTO(){
        return new AnswerDTO(this.pk, this.answer, this.rightAnswer);
    }
}
