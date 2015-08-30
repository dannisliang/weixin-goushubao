package domain;

/**
 * SubscribeItem entity. @author MyEclipse Persistence Tools
 */

public class SubscribeItem implements java.io.Serializable {

    // Fields

    private Integer id;
    private Book book;
    private Subscribe subscribe;
    private SalesBook salesBook;
    private Integer num;
    private Double subFee;
    private Integer isOld;

    // Constructors

    /** default constructor */
    public SubscribeItem() {
    }

    /** full constructor */
    public SubscribeItem(Book book, Subscribe subscribe, SalesBook salesBook,
                         Integer num, Double subFee, Integer isOld) {
        this.book = book;
        this.subscribe = subscribe;
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

    public Subscribe getSubscribe() {
        return this.subscribe;
    }

    public void setSubscribe(Subscribe subscribe) {
        this.subscribe = subscribe;
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