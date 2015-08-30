package domain;

/**
 * Cartitem entity. @author MyEclipse Persistence Tools
 */

public class Cartitem implements java.io.Serializable {

    // Fields

    private Integer id;
    private Book book;
    private Cart cart;
    private SalesBook salesBook;
    private Integer num;
    private Double subFee;
    private Integer isOld;

    // Constructors

    /** default constructor */
    public Cartitem() {
    }

    /** full constructor */
    public Cartitem(Book book, Cart cart, SalesBook salesBook, Integer num,
                    Double subFee, Integer isOld) {
        this.book = book;
        this.cart = cart;
        this.salesBook = salesBook;
        this.num = num;
        this.subFee = subFee;
        this.isOld = isOld;
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

    public Cart getCart() {
        return this.cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public SalesBook getSalesBook() {
        return this.salesBook;
    }

    public void setSalesBook(SalesBook salesBook) {
        this.salesBook = salesBook;
    }

    public Integer getNum() {
        return this.num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getSubFee() {
        return this.subFee;
    }

    public void setSubFee(Double subFee) {
        this.subFee = subFee;
    }

    public Integer getIsOld() {
        return this.isOld;
    }

    public void setIsOld(Integer isOld) {
        this.isOld = isOld;
    }

}