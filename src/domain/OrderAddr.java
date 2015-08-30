package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * OrderAddr entity. @author MyEclipse Persistence Tools
 */

public class OrderAddr implements java.io.Serializable {

    // Fields

    private Integer id;
    private User user;
    private String name;
    private String phone;
    private String addr;
    private Set orderses = new HashSet(0);

    // Constructors

    /** default constructor */
    public OrderAddr() {
    }

    /** full constructor */
    public OrderAddr(User user, String name, String phone, String addr,
                     Set orderses) {
        this.user = user;
        this.name = name;
        this.phone = phone;
        this.addr = addr;
        this.orderses = orderses;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return this.addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Set getOrderses() {
        return this.orderses;
    }

    public void setOrderses(Set orderses) {
        this.orderses = orderses;
    }

}