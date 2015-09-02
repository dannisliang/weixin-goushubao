package dao;

import domain.Seller;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by Lixiao on 8/28/2015.
 */
public class SellerDao extends HibernateDaoSupport {

    /**
     * 更改用户的密码
     */
    public boolean updatePassword(Seller seller){
        boolean result = false;
        Seller find = this.getHibernateTemplate().get(Seller.class,seller.getId());
        if(find!=null){
            if(seller.getPassword()!=null){
                find.setPassword(seller.getPassword());
                result = true;
            }
        }
        return result;
    }

    /**
     * 更新用户信息
     */
    public void update(Seller seller){
        Seller find = this.getHibernateTemplate().get(Seller.class,seller.getId());
        if(find!=null){
            if(seller.getUsername()!=null){
                find.setUsername(seller.getUsername());
            }
            if(seller.getName()!=null){
                find.setName(seller.getName());
            }
            if(seller.getAddr()!=null){
                find.setName(seller.getName());
            }
            this.getHibernateTemplate().update(find);
        }
    }

    /**
     * 得到seller
     * @param seller
     * @return
     */
    public Seller getSellerByUnamePwd(Seller seller){
        String hql = "from Seller where tel=? and password=?";
        List<Seller> find = this.getHibernateTemplate().find(hql,seller.getTel(),seller.getPassword());
        return find.isEmpty()? null:find.get(0);
    }

    /**
     * 保存seller
     * @param seller
     */
    public void save(Seller seller){
        this.getHibernateTemplate().save(seller);
    }

    public Seller getSellerByTel(String tel){
        String hql = "from Seller where tel=?";
        List<Seller> find = this.getHibernateTemplate().find(hql,tel);
        return find.isEmpty()? null:find.get(0);
    }
    /**
     * 验证数据库有没有手机号
     */
    public String getTel(String tel){
        String result = "N";
        String hql = "from Seller where tel=?";
        List<Seller> find = this.getHibernateTemplate().find(hql, tel);
        if(!find.isEmpty()){
            result = "Y";
        }
        return result;
    }
}
