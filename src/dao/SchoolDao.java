package dao;

import domain.School;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static void main(String[] args) {
        String match = "^[A-Za-z0-9_]{6,14}[A-Za-z0-9_]$";
        Pattern pattern = Pattern.compile(match);
        Matcher matcher = pattern.matcher("sjdfsjfjs");
        System.out.println(matcher.find());
    }
}
