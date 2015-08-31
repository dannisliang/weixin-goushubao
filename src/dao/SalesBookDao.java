package dao;

import domain.SalesBook;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by Lixiao on 8/31/2015.
 */
public class SalesBookDao extends HibernateDaoSupport {
    public List<SalesBook> getAllSBooks(int schoolId){
        String hql = "from SalesBook s where s.seller.id=?";
        List<SalesBook> find = this.getHibernateTemplate().find(hql,schoolId);
        return  find;
    }
}
