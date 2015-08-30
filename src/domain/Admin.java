package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin implements java.io.Serializable {

    // Fields

    private Integer id;
    private String username;
    private String password;
    private Set feedbacks = new HashSet(0);

    // Constructors

    /** default constructor */
    public Admin() {
    }

    /** full constructor */
    public Admin(String username, String password, Set feedbacks) {
        this.username = username;
        this.password = password;
        this.feedbacks = feedbacks;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set getFeedbacks() {
        return this.feedbacks;
    }

    public void setFeedbacks(Set feedbacks) {
        this.feedbacks = feedbacks;
    }

}