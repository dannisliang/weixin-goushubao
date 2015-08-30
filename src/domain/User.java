package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

    // Fields

    private Integer id;
    private School school;
    private String tel;
    private String password;
    private String name;
    private String addr;
    private Integer state;
    private Integer sex;
    private String headImage;
    private String registDate;
    private Set orderses = new HashSet(0);
    private Set subscribes = new HashSet(0);
    private Set carts = new HashSet(0);
    private Set weixinUsers = new HashSet(0);
    private Set orderAddrs = new HashSet(0);

    // Constructors

    /** default constructor */
    public User() {
    }

    /** full constructor */
    public User(School school, String tel, String password, String name,
                String addr, Integer state, Integer sex, String headImage,
                String registDate, Set orderses, Set subscribes, Set carts,
                Set weixinUsers, Set orderAddrs) {
        this.school = school;
        this.tel = tel;
        this.password = password;
        this.name = name;
        this.addr = addr;
        this.state = state;
        this.sex = sex;
        this.headImage = headImage;
        this.registDate = registDate;
        this.orderses = orderses;
        this.subscribes = subscribes;
        this.carts = carts;
        this.weixinUsers = weixinUsers;
        this.orderAddrs = orderAddrs;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public School getSchool() {
        return this.school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return this.addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHeadImage() {
        return this.headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getRegistDate() {
        return this.registDate;
    }

    public void setRegistDate(String registDate) {
        this.registDate = registDate;
    }

    public Set getOrderses() {
        return this.orderses;
    }

    public void setOrderses(Set orderses) {
        this.orderses = orderses;
    }

    public Set getSubscribes() {
        return this.subscribes;
    }

    public void setSubscribes(Set subscribes) {
        this.subscribes = subscribes;
    }

    public Set getCarts() {
        return this.carts;
    }

    public void setCarts(Set carts) {
        this.carts = carts;
    }

    public Set getWeixinUsers() {
        return this.weixinUsers;
    }

    public void setWeixinUsers(Set weixinUsers) {
        this.weixinUsers = weixinUsers;
    }

    public Set getOrderAddrs() {
        return this.orderAddrs;
    }

    public void setOrderAddrs(Set orderAddrs) {
        this.orderAddrs = orderAddrs;
    }

}