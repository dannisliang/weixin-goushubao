package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Subscribe entity. @author MyEclipse Persistence Tools
 */

public class Subscribe implements java.io.Serializable {

    // Fields

    private Integer id;
    private User user;
    private Double totalFee;
    private Set subscribeItems = new HashSet(0);

    // Constructors

    /** default constructor */
    public Subscribe() {
    }

    /** full constructor */
    public Subscribe(User user, Double totalFee, Set subscribeItems) {
        this.user = user;
        this.totalFee = totalFee;
        this.subscribeItems = subscribeItems;
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

    public Double getTotalFee() {
        return this.totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public Set getSubscribeItems() {
        return this.subscribeItems;
    }

    public void setSubscribeItems(Set subscribeItems) {
        this.subscribeItems = subscribeItems;
    }

}