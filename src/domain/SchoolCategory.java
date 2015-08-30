package domain;

/**
 * SchoolCategory entity. @author MyEclipse Persistence Tools
 */

public class SchoolCategory implements java.io.Serializable {

    // Fields

    private Integer id;
    private Category category;
    private School school;

    // Constructors

    /** default constructor */
    public SchoolCategory() {
    }

    /** full constructor */
    public SchoolCategory(Category category, School school) {
        this.category = category;
        this.school = school;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public School getSchool() {
        return this.school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

}