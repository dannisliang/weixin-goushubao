package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */

public class Category implements java.io.Serializable {

    // Fields

    private Integer id;
    private String name;
    private Set schoolCategories = new HashSet(0);
    private Set salesBooks = new HashSet(0);

    // Constructors

    /** default constructor */
    public Category() {
    }

    /** full constructor */
    public Category(String name, Set schoolCategories, Set salesBooks) {
        this.name = name;
        this.schoolCategories = schoolCategories;
        this.salesBooks = salesBooks;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getSchoolCategories() {
        return this.schoolCategories;
    }

    public void setSchoolCategories(Set schoolCategories) {
        this.schoolCategories = schoolCategories;
    }

    public Set getSalesBooks() {
        return this.salesBooks;
    }

    public void setSalesBooks(Set salesBooks) {
        this.salesBooks = salesBooks;
    }

}