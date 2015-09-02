package web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.Orders;
import org.apache.struts2.convention.annotation.Action;

/**
 * Created by Lixiao on 9/1/2015.
 */
public class SellerOrderAction extends ActionSupport implements ModelDriven<Orders>{
    private Orders order = new Orders();


    @Override
    public Orders getModel() {
        return order;
    }
}
