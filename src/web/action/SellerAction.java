package web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.City;
import domain.School;
import domain.Seller;
import domain.ServiceArea;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import netscape.javascript.JSObject;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import service.CityService;
import service.SchoolService;
import service.SellerService;
import service.ServiceAreaService;
import utils.CommonUtil;
import utils.YunCheck;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;


/**
 * Created by Lixiao on 8/28/2015.
 */
@Scope("prototype")
@ParentPackage("seller-default")
@Namespace(value="/seller")
@Results({
       /* @Result(name="adminLogin",location="index",type="redirectAction"),
        @Result(name="error",location="indexPage",type="redirectAction")
*/
})
public class SellerAction extends ActionSupport implements ModelDriven<Seller> {

    private Seller seller  = new Seller();
    private SellerService sellerService;
    private String checkCode;
    private String callback;
    private int school;
    private int serviceArea;
    private int city;
    private CityService cityService;
    private ServiceAreaService serviceAreaService;
    private SchoolService schoolService;
    /**
     * 注册页面
     */
    @Action(
            value = "registPage",
            results = {
                    @Result(name = "success",location = "/index.jsp")
            }
    )
    public String registPage(){
        return SUCCESS;
    }
    /**
     *seller注册Action
    */
    @Action(
            value = "apply",
            results = {
                    @Result(name = "success",location = "/index.jsp")
            }
    )
    public String apply(){
        City findCity = cityService.getCityById(city);
        ServiceArea findServiceArea = serviceAreaService.getServiceAreaById(serviceArea);
        School findSchool = schoolService.getSchoolById(school);
        String mark = seller.getMark();
        mark = mark.replaceAll("<","&lt;").replaceAll(">","&gt;");
        seller.setMark(mark);
        findSchool.setSeller(seller);
        sellerService.save(seller);
        schoolService.update(findSchool);
        return SUCCESS;
    }
    /**
     * 判断手机号有没有注册过
     */
    @Action(
            value = "getTel",
            results = {
                    @Result(name="success",type = "json")
            }
    )
    public String getTel(){
        String isTure = sellerService.getTel(seller.getTel());
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(isTure);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
           if (writer!=null){
               writer.close();
           }
        }
        return SUCCESS;
    }
    /**
     * seller 进入登陆页面
     * @return
     */
    @Action(
            value = "loginPage",
            results = {
                    @Result(name = "success",location = "/WEB-INF/sellerPages/login.jsp")
            }
    )
    public String loginPage(){

        return SUCCESS;
    }

    /**
     * 登陆action
     */
    @Action(
            value = "login",
            results = {
                    @Result(name = "success",location = "/index.jsp"),
                    @Result(name = "input",location = "/WEB-INF/sellerPages/login.jsp"),
                    @Result(name = "loginFailed",location = "/WEB-INF/sellerPages/login.jsp")
            }
    )
    public String login(){
        Seller existSeller = sellerService.getSellerByUnamePwd(seller);
        if(existSeller == null){
            this.addActionMessage("账号或者密码错误");
            this.addActionError("账号或者密码错误");
            return "loginFailed";
        }
        ServletActionContext.getRequest().getSession().setAttribute("seller", existSeller);
        return SUCCESS;
    }

    /**
     * 核对验证码
     * @return
     */
    @Action(
            value = "checkCode",
            results = {
                    @Result(name="success",type = "json"),
                    @Result(name="error",type = "json")
            }
    )
    public String checkCode(){
        String code = (String)ServletActionContext.getRequest().getSession().getAttribute("code");
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            if(null!=code){
                if(code==checkCode){
                    if(callback==null){
                        writer.write("\""+"Y"+"\"");
                    }else{
                        String s = CommonUtil.getJsonP("\""+"Y"+"\"",callback);
                        writer.write(s);
                    }
                    return SUCCESS;
                }
            }
            if(callback==null){
                writer.write("\""+"N"+"\"");
            }else{
                String s = CommonUtil.getJsonP("\""+"N"+"\"",callback);
                writer.write(s);
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer!=null) writer.close();
        }

        return ERROR;
    }
    @Action(
            value="getCheckCode",
            results = {
                    @Result(name="success",type = "json"),
                    @Result(name="error",type= "json")
            }
    )
    public String getCheckCode(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        String gettel = sellerService.getTel(seller.getTel());
        if(gettel=="Y"){
            try {
                writer = response.getWriter();
                if(callback==null){
                    writer.write("\""+gettel+"\"");
                }else{
                    gettel = CommonUtil.getJsonP("\""+gettel+"\"",callback);
                    writer.write(gettel);
                }

                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(writer!=null)
                writer.close();
            }

            return ERROR;
        }else {
            String code = YunCheck.sendVoiceToPhone(seller.getTel());
            try {
                writer = response.getWriter();
                if(callback==null){
                    writer.write("\""+gettel+"\"");
                }else{
                    gettel = CommonUtil.getJsonP("\""+gettel+"\"",callback);
                    writer.write(gettel);
                }
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(writer!=null)writer.close();
            }
            ServletActionContext.getRequest().getSession().setAttribute("code", code);
            return SUCCESS;
        }
    }

    /**
     * 首页
     */
    public String indexPage(){

        return SUCCESS;
    }

    /**
     * 获得city
     */
    @Action(
            value = "getCities",
            results ={
                    @Result(name="success",type = "json")
            }
    )
    public String getCities(){
        Map<String,String > cities = cityService.getCities();
        String json = JSONArray.fromObject(cities).toString();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;

        try {
            writer = response.getWriter();
            if(callback==null){
                writer.write(json);
            }else{
                String s = CommonUtil.getJsonP(json,callback);
                writer.write(s);
            }
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(writer!=null) writer.close();
        }
        return SUCCESS;
    }

    /**
     * 根据city获得所有的ServiceAre
     * @return
     */
    @Action(
            value = "getServiceAreaByCity",
            results = {
                    @Result(name="success",type = "json")
            }
    )

    public String  getServiceAreaBycity(){
        Map<String ,String > find = serviceAreaService.getServiceAreaByCity(city);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;

            String json = JSONArray.fromObject(find).toString();
            try {
                writer = response.getWriter();
                if(callback==null){
                    writer.write(json);
                }else{
                    String s = CommonUtil.getJsonP(json,callback);
                    writer.write(s);
                }
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(writer!=null) writer.close();
            }

        return SUCCESS;
    }

    /**
     * 根据服务范围获取所有大学
     * @return
     */
    @Action(
            value = "getSchoolByServiceArea",
            results = {
                    @Result(name = "success",type = "json")
            }
    )
    public String getSchoolByServiceArea(){
        Map<String ,String > find = schoolService.getSchoolByServiceArea(serviceArea);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;

        String json = JSONArray.fromObject(find).toString();
        try {
            writer = response.getWriter();
            if(callback==null){
                writer.write(json);
            }else{
                String s = CommonUtil.getJsonP(json,callback);
                writer.write(s);
            }
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(writer!=null) writer.close();
        }
        return SUCCESS;
    }

    @Override
    public Seller getModel() {
        return seller;
    }
    //properties


    public void setSchool(int school) {
        this.school = school;
    }

    public void setServiceArea(int serviceArea) {
        this.serviceArea = serviceArea;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public void setServiceAreaService(ServiceAreaService serviceAreaService) {
        this.serviceAreaService = serviceAreaService;
    }

    public void setSchoolService(SchoolService schoolService) {
        this.schoolService = schoolService;
    }
}
