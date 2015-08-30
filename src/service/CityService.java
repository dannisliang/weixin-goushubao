package service;

import dao.CityDao;
import domain.City;

import java.util.Map;

/**
 * Created by Lixiao on 8/30/2015.
 */
public class CityService {
    private CityDao cityDao;

    public Map<String ,String > getCities(){
        return cityDao.getCities();
    }

    public City getCityById(int id){
        return cityDao.getCityById(id);
    }
    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }


}
