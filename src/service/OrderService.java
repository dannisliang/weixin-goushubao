package service;

import dao.OrderDao;
import domain.Orders;
import utils.PageBean;


/**
 * Created by Lixiao on 9/1/2015.
 */
public class OrderService {
    private OrderDao orderDao;

    /**
     * 改变订单的状态
     */
    public boolean changeOrderState(Orders order){
        return orderDao.changeOrderState(order);
    }
    /**
     * 根据订单状态获得订单列表
     * @param
     */
    public PageBean<Orders> getOrdersByState(int page,int state,int sid){
        PageBean<Orders> pageBean = new PageBean<Orders>();
        int limit = 10;
        pageBean.setLimit(limit);
        int totalCount = orderDao.getStateCount(state,sid);
        pageBean.setTotalCount(totalCount);
        int totalPage = 0;
        if(totalCount%limit==0){
            totalPage = totalCount/limit;
        }else{
            totalPage = totalCount/limit +1;
        }
        if(page<=1){
            page = 1;
        }
        if(page>=totalPage){
            page=totalPage;
        }
        pageBean.setPage(page);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(orderDao.getOrdersByState(limit,(page-1)*limit,state,sid));

        return pageBean;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
