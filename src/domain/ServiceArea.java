package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * ServiceArea entity. @author MyEclipse Persistence Tools
 */

public class ServiceArea implements java.io.Serializable {

    // Fields

    private Integer id;
    private String name;
    private City city;
    private Set schools = new HashSet(0);

    // Constructors

    /** default constructor */
    public ServiceArea() {
    }

    /** full constructor */
    public ServiceArea(String name, Set schools,City city) {
        this.name = name;
        this.schools = schools;
        this.city = city;
    }

    // Property accessors

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

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

    public Set getSchools() {
        return this.schools;
    }

    public void setSchools(Set schools) {
        this.schools = schools;
    }

}