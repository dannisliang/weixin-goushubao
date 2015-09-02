package dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Created by Lixiao on 9/1/2015.
 */
public class OrderDao extends HibernateDaoSupport {
    public static void main(String[] args) {
        String phone ="13122210065";
        String tel= phone;
        phone = tel.substring(0,3)+"****"+tel.substring(7);
        System.out.println(phone);
        System.out.println(tel);
    }
}
