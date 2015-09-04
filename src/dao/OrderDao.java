package dao;

import domain.Orders;
import domain.Seller;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import utils.PageHibernateCallback;

import java.util.List;

/**
 * Created by Lixiao on 9/1/2015.
 */
public class OrderDao extends HibernateDaoSupport {

    /**
     * 改变order的状态
     */
    public boolean changeOrderState(Orders order){
        boolean result = false;
        Orders find = this.getHibernateTemplate().get(Orders.class,order.getId());
        System.out.println(find.getState());
                if(find.getState()==0) {
                    if (order.getState() == 1) {
                        find.setState(1);
                        this.getHibernateTemplate().update(find);
                        result = true;
                    }
                }
                if (find.getState()==1){
                    if(order.getState()==2||order.getState()==3){
                        find.setState(order.getState());
                        this.getHibernateTemplate().update(find);
                        result =true;
                    }
                }

        return result;
    }
    /**
     * 获取这个状态的总数
     * @param state
     * @param sid
     * @return
     */
    public int getStateCount(int state,int sid){
        String hql = "select count(*) from Orders o where o.state=? and o.seller.id=?";
        List<Long> find = this.getHibernateTemplate().find(hql,state,sid);
        return find.isEmpty()? 0:find.get(0).intValue();
    }

    /**
     * 根据订单状态获得订单列表
     */
    public List<Orders> getOrdersByState(int limit,int startIndex,int state,int seller){
        String hql = "from Orders o where o.state=? and o.seller.id=?";
        List<Orders> find = this.getHibernateTemplate().execute(new PageHibernateCallback<Orders>(hql, new Object[]{state, seller}, startIndex, limit));
        return find;
    }

    /**
     * 改变订单的状态
     */
    public boolean updateState(Seller seller){
        boolean result = true;

        return result;
    }




    public static void main(String[] args) {
        String phone ="13122210065";
        String tel= phone;
        phone = tel.substring(0,3)+"****"+tel.substring(7);
        System.out.println(phone);
        System.out.println(tel);
    }
}
