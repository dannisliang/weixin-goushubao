package web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.Category;
import domain.School;
import domain.Seller;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import service.CategoryService;
import service.SchoolService;
import utils.CommonUtil;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
public class SellerCategoryAction extends ActionSupport implements ModelDriven<Category>{
    private CategoryService categoryService;
    private SchoolService schoolService;
    private Category category =new Category();
    private String callback;
    private String discount;
    private String categoryId;

    /**
     * 类别添加
     * @param
     */

    @Action(
            value = "addCategory",
            results = {
                    @Result(name="success",type = "json")
            }
    )
    public String addCategory(){
        Seller seller = (Seller)ServletActionContext.getRequest().getSession().getAttribute("seller");
        School school = schoolService.getSchoolBySeller(seller.getId());
        categoryService.save(category.getName(),school);
        PrintWriter writer = getPrintWriter();
        if(callback==null){
            writer.write("\""+"success"+"\"");
        }else {
            writeTouser(writer,"\""+"success"+"\"",callback);
        }
        writer.flush();
        if(writer!=null) writer.close();
        return SUCCESS;
    }

    @Action(
            value = "delCategory",
            results = {
                    @Result(name = "success",type = "json")
            }
    )
    public String delCategory(){
        Seller seller = (Seller)ServletActionContext.getRequest().getSession().getAttribute("seller");
        School school = schoolService.getSchoolBySeller(seller.getId());
        categoryService.del(category.getId(), school.getId());
        PrintWriter writer = getPrintWriter();
        if(callback==null){
            writer.write("\""+"success"+"\"");
        }else {
            writeTouser(writer,"\""+"success"+"\"",callback);
        }
        writer.flush();
        if(writer!=null) writer.close();
        return SUCCESS;
    }

    @Action(
            value = "updateCategory",
            results = {
                    @Result(name="success",type = "json")
            }
    )
    public String updateCategoty(){
        Seller seller = (Seller)ServletActionContext.getRequest().getSession().getAttribute("seller");
        School school = schoolService.getSchoolBySeller(seller.getId());
        Category result = categoryService.update(category.getId(), category.getName(), school.getId());
        String json = JSONObject.fromObject(result,getConfig()).toString();
        PrintWriter writer = getPrintWriter();
        if(callback==null){
            writer.write("\""+json+"\"");
        }else {
            writeTouser(writer,"\""+json+"\"",callback);
        }
        writer.flush();
        if(writer!=null) writer.close();
        return SUCCESS;
    }

    /**
     * 获得当前学校的学院类别
     * @return
     */
    @Action(
            value = "getCategories",
            results = {
                    @Result(name = "success",type = "json")
            }
    )
    public String  getCategories(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        String dc = (String)session.getAttribute("discount");
        String category = (String)session.getAttribute("category");
        if(discount!=null){
            if(dc!=discount){
                session.setAttribute("discount",discount);
            }
        }
       if(categoryId!=null){
           if(category!=categoryId){
               session.setAttribute("category",categoryId);
           }
       }
        Seller seller = (Seller)ServletActionContext.getRequest().getSession().getAttribute("seller");
        School school = schoolService.getSchoolBySeller(seller.getId());
        List<Category> categories=categoryService.getCategories(school.getId());
        PrintWriter writer = this.getPrintWriter();
        String json = JSONArray.fromObject(categories, getConfig()).toString();
        if(callback==null){
            writer.write(json);
        }else {
            writeTouser(writer, json, callback);
        }
        writer.flush();
        if (writer!=null)writer.close();
        return SUCCESS;
    }
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
                "cartitems", "orderitems", "salesBooks", "subscribeItems", "salesBooks", "schoolCategories"
        });
        return config;
    }

    //properties

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setSchoolService(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @Override
    public Category getModel() {
        return category;
    }
}
