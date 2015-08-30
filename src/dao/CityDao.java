package dao;

import domain.City;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.*;

/**
 * Created by Lixiao on 8/30/2015.
 */
public class CityDao extends HibernateDaoSupport {
    public Map<String ,String> getCities(){
        String hql = "from City";
        List<City> find = this.getHibernateTemplate().find(hql);
        Map<String,String> result = new LinkedHashMap<String ,String >();
        for(int i=0;i<find.size();i++){
           result.put(find.get(i).getId().toString(),find.get(i).getName());
        }
        return result;
    }

    public City getCityById(int id){
        City city  = this.getHibernateTemplate().get(City.class,id);
        return city;
    }
}
