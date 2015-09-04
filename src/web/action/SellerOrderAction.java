package web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.Orders;
import domain.Seller;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import service.OrderService;
import utils.CommonUtil;
import utils.PageBean;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Lixiao on 9/1/2015.
 */
@Scope("prototype")
@ParentPackage("seller-default")
@Namespace(value="/seller")
@Results({
       /* @Result(name="adminLogin",location="index",type="redirectAction"),
        @Result(name="error",location="indexPage",type="redirectAction")
*/
})
public class SellerOrderAction extends ActionSupport implements ModelDriven<Orders>{
    private Orders order = new Orders();
    private OrderService orderService;
    private int page;
    private String callback;

    @Action(
            value = "getOrdersByState",
            results = {
                    @Result(name = "success",type = "json")
            }
    )
    public String getOrdersByState(){
        Seller seller = (Seller)ServletActionContext.getRequest().getSession().getAttribute("seller");
        PageBean<Orders> pageBean = orderService.getOrdersByState(page, order.getState(), seller.getId());

        String json = JSONObject.fromObject(pageBean,getConfig()).toString();
        PrintWriter writer = getPrintWriter();
        if(callback==null){
            writer.write(json);
        }else {
            writeTouser(writer,json,callback);
        }
        writer.flush();
        if(writer!=null) writer.close();
        return SUCCESS;
    }

    /**
     * 改变订单状态
     */
    @Action(
            value = "changeOrderState",
            results = {
                    @Result(name="success",type = "json")
            }
    )
    public String changeOrderState(){
        boolean result = orderService.changeOrderState(order);
        PrintWriter writer = getPrintWriter();
        System.out.println(order.getState());
        System.out.println(order.getId());
        if(result){
            if(callback==null){
                writer.write("\"success\"");
            }else{
                writeTouser(writer,"\"success\"",callback);
            }
        }else{
            if(callback==null){
                writer.write("\"error\"");
            }else{
                writeTouser(writer,"\"error\"",callback);
            }
        }
        if(writer!=null){
            writer.flush();
            writer.close();
        }

        return SUCCESS;
    }
    //functions
    private void writeTouser(PrintWriter writer,String json,String callback){
        if(callback==null){
            writer.write(json);
        }else{
            String s = CommonUtil.getJsonP(json, callback);
            writer.write(s);
        }
        writer.flush();
    }
    private PrintWriter getPrintWriter(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer= null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer;
    }

    private JsonConfig getConfig(){
        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        config.setExcludes(new String[]{
                "cartitems", "salesBooks", "subscribeItems",  "schoolCategories","user","seller","orders","orderses","orderitemes"
        });
        return config;
    }
    //properties
    @Override
    public Orders getModel() {
        return order;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}
