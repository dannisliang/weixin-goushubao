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
    private Set<School> school;
    private String username;
    private String password;
    private int state;
    private String tel;
    private String mark;
    private String addr;
    private String headImage;
    private Set salesBooks = new HashSet(0);
    private Set orderses = new HashSet(0);
    private Set feedbacks = new HashSet(0);

    // Constructors

    /** default constructor */
    public Seller() {
    }

    /** full constructor */
    public Seller( String username, String password,
                  Set salesBooks, Set orderses, Set feedbacks,String headImage,int state,String name,String mark,
                  String addr) {
        this.username = username;
        this.password = password;
        this.salesBooks = salesBooks;
        this.orderses = orderses;
        this.feedbacks = feedbacks;
        this.state = state;
        this.name = name;
        this.mark = mark;
        this.addr = addr;
        this.headImage = headImage;
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

    public Set<School> getSchool() {
        return school;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setSchool(Set<School> school) {
        this.school = school;
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

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getHeadImage() {
        return headImage;
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