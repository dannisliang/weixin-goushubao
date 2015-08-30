package domain;

/**
 * Feedback entity. @author MyEclipse Persistence Tools
 */

public class Feedback implements java.io.Serializable {

    // Fields

    private Integer id;
    private Admin admin;
    private Seller seller;
    private String content;
    private String title;
    private Integer from;
    private Integer state;
    private String createDate;

    // Constructors

    /** default constructor */
    public Feedback() {
    }

    /** full constructor */
    public Feedback(Admin admin, Seller seller, String content, String title,
                    Integer from, Integer state, String createDate) {
        this.admin = admin;
        this.seller = seller;
        this.content = content;
        this.title = title;
        this.from = from;
        this.state = state;
        this.createDate = createDate;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Admin getAdmin() {
        return this.admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Seller getSeller() {
        return this.seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getFrom() {
        return this.from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

}