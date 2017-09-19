package model;

import dto.CourseDTO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fabiolourenco on 08/09/17.
 */
@Entity
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long pk;

    @Version
    private Long version;

    /**
     * The course name
     */
    private String courseName;
    /**
     * The name of course's field
     */
    private String fieldName;
    /**
     * The pk of course's field
     */
    private Long fieldPk;

    public Course() {
    }

    public Course(String courseName, String fieldName, Long fieldPk) {
        this.courseName = courseName;
        this.fieldName = fieldName;
        this.fieldPk = fieldPk;
    }

    /**
     * Method to get the Database Pk
     * @return
     */
    public Long getPk() {
        return pk;
    }

    /**
     * Method to change the Database pk
     * @param pk
     */
    public void setPk(Long pk) {
        this.pk = pk;
    }

    /**
     * Method to get the course name
     * @return
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Method to define the course name
     * @param courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Method to get the pk of the course's field
     * @return
     */
    public Long getFieldPk() {
        return fieldPk;
    }

    /**
     * Method to set the pk of the course's field
     * @param fieldPk
     */
    public void setFieldPk(Long fieldPk) {
        this.fieldPk = fieldPk;
    }

    /**
     * Method to get the name of the course's field
     * @return
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Method to define the name of the course's field
     * @param fieldName
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return courseName != null ? courseName.equalsIgnoreCase(course.courseName) : false;
    }

    @Override
    public int hashCode() {
        return courseName != null ? courseName.hashCode() : 0;
    }

    public CourseDTO toDTO(){
        return new CourseDTO(this.pk, this.courseName, this.fieldName, this.fieldPk);
    }
}
