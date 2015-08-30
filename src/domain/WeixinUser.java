package domain;

/**
 * WeixinUser entity. @author MyEclipse Persistence Tools
 */

public class WeixinUser implements java.io.Serializable {

    // Fields

    private String openid;
    private User user;

    // Constructors

    /** default constructor */
    public WeixinUser() {
    }

    /** minimal constructor */
    public WeixinUser(String openid) {
        this.openid = openid;
    }

    /** full constructor */
    public WeixinUser(String openid, User user) {
        this.openid = openid;
        this.user = user;
    }

    // Property accessors

    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}