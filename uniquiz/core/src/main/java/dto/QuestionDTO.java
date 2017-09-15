package dto;

import java.util.List;

/**
 * Created by Rafael Santos on 08-09-2017.
 */
public class QuestionDTO {

    private Long pk;
    private String question;
    private List<AnswerDTO> answers;
    private String justification;

    public QuestionDTO(Long pk, String question, List<AnswerDTO> answers, String justification){
        this.setPk(pk);
        this.setQuestion(question);
        this.setAnswers(answers);
        this.setJustification(justification);
    }

    public QuestionDTO(Long pk, String question, List<AnswerDTO> answers){
        this.setPk(pk);
        this.setQuestion(question);
        this.setAnswers(answers);
    }

    public QuestionDTO() {
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }
}
