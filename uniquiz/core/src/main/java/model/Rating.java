package model;

import javax.persistence.*;

/**
 * Created by fabiolourenco on 15/09/17.
 */
@Entity
public class Rating {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long pk;

    @Version
    private Long version;

    private int value;

    private int userPk;

    public Rating() {
        //for your ORM eyez only
    }

    public Rating(int value, int userPk) {
        if(value < 1 || value > 5){
            throw new IllegalArgumentException("The rate must be between 1 and 5.");
        }
        this.value = value;
        this.userPk = userPk;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getUserPk() {
        return userPk;
    }

    public void setUserPk(int userPk) {
        this.userPk = userPk;
    }

    public Long getPk() {
        return pk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating that = (Rating) o;

        return pk != null ? pk.equals(that.pk) : that.pk == null;
    }

    @Override
    public int hashCode() {
        return pk != null ? pk.hashCode() : 0;
    }
}
