package dao;

import domain.Category;
import domain.School;
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

    public Category update(int cid,String name,int sid){
        Category result =null;
        String hql = "from Category where name=?";
        List<Category> find = this.getHibernateTemplate().find(hql,name);
        if(!find.isEmpty()){
            hql="from SchoolCategory s where s.category.id=? and s.school.id=?";
            List<SchoolCategory> find1 = this.getHibernateTemplate().find(hql,find.get(0).getId(),sid);
            if(!find1.isEmpty()){

            }else {
                find1 = this.getHibernateTemplate().find(hql, cid, sid);
                result = find.get(0);
                if (!find1.isEmpty()) {
                    find1.get(0).setCategory(result);
                    this.getHibernateTemplate().update(find1.get(0));
                }
            }
        }else{
            Category category = new Category();
            category.setName(name);
            this.getHibernateTemplate().save(category);
            hql = "from Category where name=?";
            find = this.getHibernateTemplate().find(hql,name);
            result = find.get(0);
            hql="from SchoolCategory s where s.category.id=? and s.school.id=?";
            List<SchoolCategory> find1 = this.getHibernateTemplate().find(hql,cid,sid);
            if (!find1.isEmpty()){
                find1.get(0).setCategory(find.get(0));
                this.getHibernateTemplate().update(find1.get(0));

            }
        }
    return result;
    }

    public void del(int cid,int sid){
        String hql="from SchoolCategory s where s.category.id=? and s.school.id=?";
        List<SchoolCategory> find1 = this.getHibernateTemplate().find(hql,cid,sid);
        if(!find1.isEmpty()){
            this.getHibernateTemplate().delete(find1.get(0));
        }
    }

     public Category save(String name, School school){
         Category result = null;
         String hql = "from Category where name=?";
         List<Category> find= this.getHibernateTemplate().find(hql,name);
         if(!find.isEmpty()){
             Category category = find.get(0);
             hql="from SchoolCategory s where s.category.id=? and s.school.id=?";
             List<SchoolCategory> find1 = this.getHibernateTemplate().find(hql,find.get(0).getId(),school.getId());
             if(!find1.isEmpty()){

             }else {
                 result = category;
                 find1 = this.getHibernateTemplate().find(hql, category.getId(), school.getId());
                 if (find1.isEmpty()) {
                     SchoolCategory sc = new SchoolCategory();
                     sc.setSchool(school);
                     sc.setCategory(category);
                     this.getHibernateTemplate().save(sc);
                 }
             }
         }else {
             Category category = new Category();
             category.setName(name);
             this.getHibernateTemplate().save(category);
             result = category;
             hql="from SchoolCategory s where s.category.id=? and s.school.id=?";
             List<SchoolCategory> find1 = this.getHibernateTemplate().find(hql,category.getId(),school.getId());
             if(find1.isEmpty()){
                 SchoolCategory sc = new SchoolCategory();
                 sc.setSchool(school);
                 sc.setCategory(category);
                 this.getHibernateTemplate().save(sc);
             }
         }
         return result;
     }
}
