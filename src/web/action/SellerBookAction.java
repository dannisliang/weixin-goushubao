package web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import dao.SalesBookDao;
import domain.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import service.BookService;
import service.CategoryService;
import service.SalesBookService;
import service.SchoolService;
import utils.CommonUtil;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 这个类主要做的操作是图书管理
 * Created by Lixiao on 8/31/2015.
 */
@Scope("prototype")
@ParentPackage("seller-default")
@Namespace(value="/seller")
@Results({
       /* @Result(name="adminLogin",location="index",type="redirectAction"),
        @Result(name="error",location="indexPage",type="redirectAction")
*/
})
public class SellerBookAction extends ActionSupport implements ModelDriven<SalesBook> {
    private SalesBook salesBook = new SalesBook();
    private SchoolService schoolService;
    private BookService bookService;
    private SalesBookService salesBookService;
    private String isbn;
    private String callback;
    private int categoryId;
    private CategoryService categoryService;


    /**
     * 获得Session中的折扣价
     */
    @Action(
            value = "getDiscount",
            results = {
                    @Result(name = "success",type = "json")
            }
    )
    public String getDiscount(){
        String discount = (String)ServletActionContext.getRequest().getSession().getAttribute("discount");
        if(discount==null){
            discount=0.45+"";
        }
        PrintWriter writer = getPrintWriter();
        if(callback==null){
            writer.write("\""+discount+"\"");
        }else {
            writeTouser(writer,"\""+discount+"\"",callback);
        }
        writer.flush();
        if(writer!=null) writer.close();
        return SUCCESS;
    }

    /**
     * 根据图书isbn得到标准图书
     * @return
     * @throws Exception
     */
    @Action(
            value = "findBookByIsbn",
            results = {
                    @Result(name = "success",type = "json"),
                    @Result(name="error",type = "json")
            }
    )
    public String  findBookByIsbn() throws Exception{

        Book book = bookService.getBookByIsbn(isbn);
        PrintWriter writer = this.getPrintWriter();
        if(book==null){
            if(callback==null){
                writer.write("\"notFund\"");
            }else{
                this.writeTouser(writer,"\"notFund\"",callback);
            }
            if(writer!=null) writer.close();
            return ERROR;
        }
        if(callback==null){
            writer.write(JSONObject.fromObject(book,getConfig()).toString());
        }else{
            this.writeTouser(writer,JSONObject.fromObject(book,getConfig()).toString(),callback);
        }
        if(writer!=null) writer.close();
        return SUCCESS;
    }






// private function()
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
            "cartitems","orderitems","salesBooks","subscribeItems","salesBooks","schoolCategories"
        });
        return config;
    }
    //properties

    public void setSchoolService(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public SalesBook getModel() {
        return salesBook;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    public void setSalesBookService(SalesBookService salesBookService) {
        this.salesBookService = salesBookService;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}
