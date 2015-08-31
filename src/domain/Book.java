package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Book entity. @author MyEclipse Persistence Tools
 */

public class Book implements java.io.Serializable {

    // Fields

    private String isbn;
    private String title;
    private String publisher;
    private String author;
    private Double price;
    private String image;
    private String bigImage;
    private Set subscribeItems = new HashSet(0);
    private Set salesBooks = new HashSet(0);
    private Set cartitems = new HashSet(0);
    private Set orderitems = new HashSet(0);

    // Constructors

    /** default constructor */
    public Book() {
    }

    /** minimal constructor */
    public Book(String isbn) {
        this.isbn = isbn;
    }

    /** full constructor */
    public Book(String isbn, String title, String publisher, String author,
                Double price, String image, Set subscribeItems, Set salesBooks,
                Set cartitems, Set orderitems,String bigImage) {
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisher;
        this.author = author;
        this.price = price;
        this.image = image;
        this.subscribeItems = subscribeItems;
        this.salesBooks = salesBooks;
        this.cartitems = cartitems;
        this.orderitems = orderitems;
        this.bigImage = bigImage;
    }

    // Property accessors

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set getSubscribeItems() {
        return this.subscribeItems;
    }

    public void setSubscribeItems(Set subscribeItems) {
        this.subscribeItems = subscribeItems;
    }

    public Set getSalesBooks() {
        return this.salesBooks;
    }

    public void setSalesBooks(Set salesBooks) {
        this.salesBooks = salesBooks;
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

    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
    }

    public String getBigImage() {
        return bigImage;
    }
}