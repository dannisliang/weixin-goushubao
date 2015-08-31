package dao;

import domain.Category;
import domain.SchoolCategory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lixiao on 8/31/2015.
 */
public class CategoryDao extends HibernateDaoSupport {

    public  List<Category> getCategories(int school){
        String hql = "from SchoolCategory s where s.school.id=?";
        List<SchoolCategory> find = this.getHibernateTemplate().find(hql,school);
        List<Category> result = new LinkedList<Category>();
        for(int i=0;i<find.size();i++){
            result.add(find.get(i).getCategory());
        }
        return result;
    }
}
