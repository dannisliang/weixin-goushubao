package service;

import dao.SchoolDao;
import domain.School;

import java.util.Map;

/**
 * Created by Lixiao on 8/30/2015.
 */
public class SchoolService {
    private SchoolDao schoolDao;

    public Map<String ,String > getSchoolByServiceArea(int said){

        return schoolDao.getSchoolByServiceArea(said);
    }

    public void update(School school){
        schoolDao.update(school);
    }
    public void setSchoolDao(SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }
    public School getSchoolById(int id){
        return schoolDao.getSchoolById(id);
    }
}
