package dao;

import domain.ServiceArea;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lixiao on 8/30/2015.
 */
public class ServiceAreaDao extends HibernateDaoSupport{

    public Map<String ,String> getServiceAreaByCity(int cityId){
        Map<String,String> result = new LinkedHashMap<String ,String>();
        String hql = "from ServiceArea s where s.city.id=?";
        List<ServiceArea> find = this.getHibernateTemplate().find(hql,cityId);
        for(int i =0;i<find.size();i++){
            result.put(find.get(i).getId().toString(),find.get(i).getName());
        }

        return result;
    }

    public ServiceArea getServiceAreaById(int id){
        ServiceArea serviceArea = this.getHibernateTemplate().get(ServiceArea.class,id);
        return serviceArea;
    }
}
