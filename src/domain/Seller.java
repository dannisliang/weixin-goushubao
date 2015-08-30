package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Seller entity. @author MyEclipse Persistence Tools
 */

public class Seller implements java.io.Serializable {

    // Fields

    private Integer id;
    private String name;
    private Set<School> schools;
    private String username;
    private String password;
    private int state;
    private String tel;
    private String mark;
    private Set salesBooks = new HashSet(0);
    private Set orderses = new HashSet(0);
    private Set feedbacks = new HashSet(0);

    // Constructors

    /** default constructor */
    public Seller() {
    }

    /** full constructor */
    public Seller( String username, String password,
                  Set salesBooks, Set orderses, Set feedbacks,String licenceImage,int state,String name,String mark) {
        this.username = username;
        this.password = password;
        this.salesBooks = salesBooks;
        this.orderses = orderses;
        this.feedbacks = feedbacks;
        this.state = state;
        this.name = name;
        this.mark = mark;
    }

    // Property accessors

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<School> getSchools() {
        return schools;
    }

    public void setSchools(Set<School> schools) {
        this.schools = schools;
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

    public Set getSalesBooks() {
        return this.salesBooks;
    }

    public void setSalesBooks(Set salesBooks) {
        this.salesBooks = salesBooks;
    }

    public Set getOrderses() {
        return this.orderses;
    }

    public void setOrderses(Set orderses) {
        this.orderses = orderses;
    }

    public Set getFeedbacks() {
        return this.feedbacks;
    }

    public void setFeedbacks(Set feedbacks) {
        this.feedbacks = feedbacks;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}