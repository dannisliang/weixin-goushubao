package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * SalesBook entity. @author MyEclipse Persistence Tools
 */

public class SalesBook implements java.io.Serializable {

    // Fields

    private Integer id;
    private Book book;
    private Category category;
    private Seller seller;
    private Integer num;
    private Double price;
    private String shelfTime;
    private Float discount;
    private Set subscribeItems = new HashSet(0);
    private Set cartitems = new HashSet(0);
    private Set orderitems = new HashSet(0);

    // Constructors

    /** default constructor */
    public SalesBook() {
    }

    /** full constructor */
    public SalesBook(Book book, Category category, Seller seller, Integer num,
                     Double price, String shelfTime, Float discount, Set subscribeItems,
                     Set cartitems, Set orderitems) {
        this.book = book;
        this.category = category;
        this.seller = seller;
        this.num = num;
        this.price = price;
        this.shelfTime = shelfTime;
        this.discount = discount;
        this.subscribeItems = subscribeItems;
        this.cartitems = cartitems;
        this.orderitems = orderitems;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Seller getSeller() {
        return this.seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Integer getNum() {
        return this.num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getShelfTime() {
        return this.shelfTime;
    }

    public void setShelfTime(String shelfTime) {
        this.shelfTime = shelfTime;
    }

    public Float getDiscount() {
        return this.discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Set getSubscribeItems() {
        return this.subscribeItems;
    }

    public void setSubscribeItems(Set subscribeItems) {
        this.subscribeItems = subscribeItems;
    }

    public Set getCartitems() {
        return this.cartitems;
    }

    public void setCartitems(Set cartitems) {
        this.cartitems = cartitems;
    }

    public Set getOrderitems() {
        return this.orderitems;
    }

    public void setOrderitems(Set orderitems) {
        this.orderitems = orderitems;
    }

}