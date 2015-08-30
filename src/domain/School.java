package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * School entity. @author MyEclipse Persistence Tools
 */

public class School implements java.io.Serializable {

    // Fields

    private Integer id;
    private ServiceArea serviceArea;
    private String name;
    private String addr;
    private Seller seller;
    private Set schoolCategories = new HashSet(0);
    private Set users = new HashSet(0);

    // Constructors

    /** default constructor */
    public School() {
    }

    /** full constructor */
    public School(ServiceArea serviceArea, String name, String addr,
                  Set sellers, Set schoolCategories, Set users) {
        this.serviceArea = serviceArea;
        this.name = name;
        this.addr = addr;
        this.schoolCategories = schoolCategories;
        this.users = users;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServiceArea getServiceArea() {
        return this.serviceArea;
    }

    public void setServiceArea(ServiceArea serviceArea) {
        this.serviceArea = serviceArea;
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

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Set getSchoolCategories() {
        return this.schoolCategories;
    }

    public void setSchoolCategories(Set schoolCategories) {
        this.schoolCategories = schoolCategories;
    }

    public Set getUsers() {
        return this.users;
    }

    public void setUsers(Set users) {
        this.users = users;
    }

}