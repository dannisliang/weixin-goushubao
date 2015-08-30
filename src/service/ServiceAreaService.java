package service;

import dao.ServiceAreaDao;
import domain.ServiceArea;

import java.util.Map;

/**
 * Created by Lixiao on 8/30/2015.
 */
public class ServiceAreaService {
    private ServiceAreaDao serviceAreaDao;

    public Map<String,String > getServiceAreaByCity(int cityId){
        return serviceAreaDao.getServiceAreaByCity(cityId);
    }

    public ServiceArea getServiceAreaById(int id){
        return serviceAreaDao.getServiceAreaById(id);
    }
    public void setServiceAreaDao(ServiceAreaDao serviceAreaDao) {
        this.serviceAreaDao = serviceAreaDao;
    }
}
