package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Orders entity. @author MyEclipse Persistence Tools
 */

public class Orders implements java.io.Serializable {

    // Fields

    private Integer id;
    private OrderAddr orderAddr;
    private User user;
    private Seller seller;
    private Double totalFee;
    private String ordertime;
    private Integer state;
    private Integer isPay;
    private String date;
    private Set orderitems = new HashSet(0);

    // Constructors

    /** default constructor */
    public Orders() {
    }

    /** minimal constructor */
    public Orders(Double totalFee) {
        this.totalFee = totalFee;
    }

    /** full constructor */
    public Orders(OrderAddr orderAddr, User user, Seller seller,
                  Double totalFee, String ordertime, Integer state, Integer isPay,
                  String date, Set orderitems) {
        this.orderAddr = orderAddr;
        this.user = user;
        this.seller = seller;
        this.totalFee = totalFee;
        this.ordertime = ordertime;
        this.state = state;
        this.isPay = isPay;
        this.date = date;
        this.orderitems = orderitems;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderAddr getOrderAddr() {
        return this.orderAddr;
    }

    public void setOrderAddr(OrderAddr orderAddr) {
        this.orderAddr = orderAddr;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seller getSeller() {
        return this.seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Double getTotalFee() {
        return this.totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public String getOrdertime() {
        return this.ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIsPay() {
        return this.isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Set getOrderitems() {
        return this.orderitems;
    }

    public void setOrderitems(Set orderitems) {
        this.orderitems = orderitems;
    }

}