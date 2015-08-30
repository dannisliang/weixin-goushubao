package domain;

/**
 * Orderitem entity. @author MyEclipse Persistence Tools
 */

public class Orderitem implements java.io.Serializable {

    // Fields

    private Integer id;
    private Book book;
    private SalesBook salesBook;
    private Orders orders;
    private Integer isOld;
    private Double subFee;
    private Integer num;
    private Integer state;

    // Constructors

    /** default constructor */
    public Orderitem() {
    }

    /** full constructor */
    public Orderitem(Book book, SalesBook salesBook, Orders orders,
                     Integer isOld, Double subFee, Integer num, Integer state) {
        this.book = book;
        this.salesBook = salesBook;
        this.orders = orders;
        this.isOld = isOld;
        this.subFee = subFee;
        this.num = num;
        this.state = state;
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

    public SalesBook getSalesBook() {
        return this.salesBook;
    }

    public void setSalesBook(SalesBook salesBook) {
        this.salesBook = salesBook;
    }

    public Orders getOrders() {
        return this.orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Integer getIsOld() {
        return this.isOld;
    }

    public void setIsOld(Integer isOld) {
        this.isOld = isOld;
    }

    public Double getSubFee() {
        return this.subFee;
    }

    public void setSubFee(Double subFee) {
        this.subFee = subFee;
    }

    public Integer getNum() {
        return this.num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}