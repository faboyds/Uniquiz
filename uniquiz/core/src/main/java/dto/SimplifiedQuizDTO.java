package dto;

/**
 * Created by Rafael Santos on 08-09-2017.
 */
public class SimplifiedQuizDTO {

    private Long pk;
    private String difficulty;
    private Long subjectPk;
    private String subjectName;
    private Long coursePk;
    private String title;
    private Double averageRating;
    private String author ;

    public SimplifiedQuizDTO(Long pk, String difficulty, Long subjectPk,
                            String subjectName, Long coursePk, String title, double averageRating,String author){
        this.setPk(pk);
        this.setDifficulty(difficulty);
        this.setSubjectPk(subjectPk);
        this.setSubjectName(subjectName);
        this.setCoursePk(coursePk);
        this.setTitle(title);
        this.setAverageRating(averageRating);
        setAuthor(author);
    }



    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
