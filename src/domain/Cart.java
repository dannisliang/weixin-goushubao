package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Cart entity. @author MyEclipse Persistence Tools
 */

public class Cart implements java.io.Serializable {

    // Fields

    private Integer id;
    private User user;
    private Double totalFee;
    private Set cartitems = new HashSet(0);

    // Constructors

    /** default constructor */
    public Cart() {
    }

    /** full constructor */
    public Cart(User user, Double totalFee, Set cartitems) {
        this.user = user;
        this.totalFee = totalFee;
        this.cartitems = cartitems;
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

    public Set getCartitems() {
        return this.cartitems;
    }

    public void setCartitems(Set cartitems) {
        this.cartitems = cartitems;
    }

}