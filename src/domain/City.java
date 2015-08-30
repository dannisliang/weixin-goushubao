package domain;

import java.util.Set;

/**
 * Created by Lixiao on 8/28/2015.
 */
public class City {
    private Integer id;
    private String name;
    private Set<ServiceArea> serviceAreas;

    public City() {
    }

    public City(Integer id, String name, Set<ServiceArea> serviceAreas) {
        this.id = id;
        this.name = name;
        this.serviceAreas = serviceAreas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ServiceArea> getServiceAreas() {
        return serviceAreas;
    }

    public void setServiceAreas(Set<ServiceArea> serviceAreas) {
        this.serviceAreas = serviceAreas;
    }
}
