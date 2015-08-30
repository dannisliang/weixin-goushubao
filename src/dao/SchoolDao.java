package dao;

import domain.School;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lixiao on 8/30/2015.
 */
public class SchoolDao extends HibernateDaoSupport {
    public Map<String ,String > getSchoolByServiceArea(int said){
        Map<String ,String > result = new LinkedHashMap<String ,String >();
        String hql = "from School s where s.serviceArea.id = ?";
        List<School> find = this.getHibernateTemplate().find(hql,said);
        for(int i = 0;i<find.size();i++){
            result.put(find.get(i).getId().toString(),find.get(i).getName());
        }
        return result;
    }

    public School getSchoolById(int id){
        School school =this.getHibernateTemplate().get(School.class, id);
        return school;
    }

    public void update(School school){
        this.getHibernateTemplate().update(school);
    }

}
