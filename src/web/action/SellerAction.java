package web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.School;
import domain.Seller;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import service.CityService;
import service.SchoolService;
import service.SellerService;
import service.ServiceAreaService;
import utils.CommonUtil;
import utils.OperateImage;
import utils.YunCheck;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    private File uploadHI;
    private String uploadHIFileName;
    private String uploadHIFileContentType;
    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * 用户基本信息操作
     */
    @Action(
            value = "getSeller",
            results = {
                    @Result(name = "success",type = "json")
            }
    )
    public String getSeller(){
        Seller find = (Seller)ServletActionContext.getRequest().getSession().getAttribute("seller");
        find = sellerService.getSellerByTel(find.getTel());
        String tel = find.getTel();
        String phone = tel.substring(0,3)+"****"+tel.substring(7);
        find.setTel(phone);
        String username = find.getUsername();
        if(username!=null) find.setUsername(username.substring(0,1)+"*");
        String json = JSONObject.fromObject(find,getConfig()).toString();
        PrintWriter writer = getPrintWriter();
        if(callback==null){
            writer.write(json);
        }else {
            writeTouser(writer,json,callback);
        }
        if(writer!=null) writer.close();
        return SUCCESS;
    }




    /**
     * 上传headImage
     */
    @Action(
            value = "uploadHeadImage",
            results = {
                    @Result(name="success",type = "json"),
                    @Result(name="error",type = "json"),
                    @Result(name = "imageError",type = "json")
            }
    )
    public String uploadHeadImage(){
        String[] str = { ".jpg", ".jpeg", ".bmp", ".gif" };
        PrintWriter writer = getPrintWriter();
        // 获取用户登录名
        Seller currentSeller = (Seller)ServletActionContext.getRequest().getSession().getAttribute("seller");
        // 限定文件大小是4MB
        if (uploadHI == null || uploadHI.length() > 4194304) {
            //文件过大
            if(callback==null) {
                writer.write("\""+"0"+"\"");
            }else{
                writeTouser(writer,"\""+"0"+"\"",callback);
            }
            writer.flush();
            if(writer!=null) writer.close();
            return "error";
        }
        for (String s : str) {
            if (uploadHIFileName.endsWith(s)) {
                String realPath = ServletActionContext.getServletContext().getRealPath("/headImage");// 在tomcat中保存图片的实际路径  ==  "webRoot/uploadpic/"
                String name = CommonUtil.UUID16()+".jpg";
                File saveFile = new File(new File(realPath), name); // 在该实际路径下实例化一个文件
                // 判断父目录是否存在
                if (!saveFile.getParentFile().exists()) {
                    saveFile.getParentFile().mkdirs();
                }
                try {
                    // 执行文件上传
                    // FileUtils 类名 org.apache.commons.io.FileUtils;
                    // 是commons-io包中的，commons-fileupload 必须依赖
                    // commons-io包实现文件上次，实际上就是将一个文件转换成流文件进行读写
                    FileUtils.copyFile(uploadHI, saveFile);
                    OperateImage image = new OperateImage();
                    image.setX(x);image.setY(y);image.setHeight(height);image.setWidth(width);image.setSrcpath(realPath + "/" + name);
                    image.setSubpath(realPath + "/" + name);
                    image.cut();
                    if(callback==null) {
                        writer.write("\""+realPath+"/"+name+"\"");
                    }else{
                        writeTouser(writer,"\""+realPath+"/"+name+"\"",callback);
                    }
                    writer.flush();
                    if(writer!=null) writer.close();
                    return SUCCESS;
                } catch (IOException e) {
                    if(callback==null) {
                        writer.write("\""+"1"+"\"");
                    }else{
                        writeTouser(writer,"\""+"1"+"\"",callback);
                    }
                    writer.flush();
                    if(writer!=null) writer.close();
                    return "imageError";
                }
            }
        }
        if(callback==null) {
            writer.write("\""+"2"+"\"");
        }else{
            writeTouser(writer,"\""+"2"+"\"",callback);
        }
        writer.flush();
        if(writer!=null) writer.close();
        return ERROR;
    }

    /**
     * 保存修改过的信息
     */
    @Action(
            value = "success",
            results = {
                    @Result(name="success",type = "json")
            }
    )
    public String sellerUpdate(){
        sellerService.update(seller);
        PrintWriter writer = getPrintWriter();
        if(callback==null) {
            writer.write("\"success\"");
        }else{
            writeTouser(writer,"\"success\"",callback);
        }
        writer.flush();
        if(writer!=null) writer.close();
        return SUCCESS;
    }
    /**
     * 修改密码
     */
    @Action(
            value = "updatePassword",
            results = {
                    @Result(name="success",type = "json")
            }
    )
    public String updatePassword(){
        boolean result = sellerService.updatePassword(seller);
        PrintWriter writer = getPrintWriter();
        if(result){
            if(callback==null) {
                writer.write("\"error\"");
            }else{
                writeTouser(writer,"\"error\"",callback);
            }
            writer.flush();
            if(writer!=null) writer.close();
        }
        if(callback==null) {
            writer.write("\"success\"");
        }else{
            writeTouser(writer,"\"success\"",callback);
        }
        writer.flush();
        if(writer!=null) writer.close();

        return SUCCESS;
    }

    /**
     * 获取手机验证码
     */
    @Action(
            value = "getTelCode",
            results = {
                    @Result(name = "success",type = "json")
            }
    )
    public String getTelCode(){
        PrintWriter writer = getPrintWriter();
        String code = YunCheck.sendVoiceToPhone(seller.getTel());
        ServletActionContext.getRequest().getSession().setAttribute("code",code);
            if(callback==null){
                writer.write("\""+"success"+"\"");
            }else{
               writeTouser(writer,"\""+"success"+"\"",callback);
            }
            writer.flush();

        if(writer!=null)writer.close();
        return SUCCESS;
    }
    /**
     *
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
                    @Result(name = "success",type="json"),
                    @Result(name="input",type="json")
            }
    )
    public String apply(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        System.out.println(school);
        if(school==0||seller.getName()==null||seller.getUsername()==null||seller.getAddr()==null){
            try {
                writer = response.getWriter();
                if(callback==null) {
                    writer.write("\"error\"");
                }else{
                   String json= CommonUtil.getJsonP("\"error\"",callback);
                    writer.write(json);
                }
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (writer!=null){
                    writer.close();
                }
            }
            return INPUT;
        }
        Seller findSeller = sellerService.getSellerByTel(seller.getTel());
        if(findSeller!=null){
            try {
                writer = response.getWriter();
                if(callback==null) {
                    writer.write("\"error0\"");
                }else{
                    String json= CommonUtil.getJsonP("\"error0\"",callback);
                    writer.write(json);
                }
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (writer!=null){
                    writer.close();
                }
            }
            return INPUT;
        }
        School findSchool = schoolService.getSchoolById(school);
        String mark = seller.getMark();
        if(mark!=null) {
            mark = mark.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
            seller.setMark(mark);
        }
        String secret = CommonUtil.getSecretByMd5(seller.getTel());
        seller.setPassword(secret);
        sellerService.save(seller);
        Seller find = sellerService.getSellerByTel(seller.getTel());
        System.out.println(find.getId());
        findSchool.setSeller(find);
        schoolService.update(findSchool);
            try {
                writer = response.getWriter();
                if(callback==null) {
                    writer.write("\"success\"");
                }else{
                    String json= CommonUtil.getJsonP("\"success\"",callback);
                    writer.write(json);
                }
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
                    @Result(name = "success",type="json"),
                    @Result(name = "input",type="json"),
                    @Result(name = "loginFailed",type="json")
            }
    )
    public String login(){
        PrintWriter writer = this.getPrintWriter();
        if(seller.getTel()==null||seller.getPassword()==null){
            this.writeTouser(writer,"\"fieldError\"",callback);
            if(writer!=null){
                writer.close();
              }
                return INPUT;
            }
        //正则判断Username
        String match = "^[A-Za-z0-9_]{5,14}[A-Za-z0-9_]$";
        Pattern pattern = Pattern.compile(match);
        Matcher matcher = pattern.matcher(seller.getTel());
        Matcher matcher1 = pattern.matcher(seller.getPassword());
        if(!matcher.find()&&!matcher1.find()){
            this.writeTouser(writer,"\"fieldError\"",callback);
            if(writer!=null){
                writer.close();
            }
            return INPUT;
        }
        Seller existSeller = sellerService.getSellerByUnamePwd(seller);
        if(existSeller == null){
            this.writeTouser(writer,"\"error\"",callback);
            if(writer!=null){
                writer.close();
            }
            return "loginFailed";
        }
        //如果用户的准太等于零，那么表示这个商家还没有被认证，不能登录
        if(existSeller.getState()==0){
            this.writeTouser(writer, "\"stateError\"", callback);
            if(writer!=null){
                writer.close();
            }
        }
        ServletActionContext.getRequest().getSession().setAttribute("seller", existSeller);
        this.writeTouser(writer, "\"success\"", callback);
        if(writer!=null){
            writer.close();
        }
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
        String match = "^[0-9]{5}[0-9]$";
        Pattern pattern = Pattern.compile(match);
        Matcher matcher = pattern.matcher(checkCode);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            if(null!=code&& matcher.find()){
                if(Integer.parseInt(code)==Integer.parseInt(checkCode)){
                    if(callback==null){
                        writer.write("\""+"Y"+"\"");
                    }else{
                        String s = CommonUtil.getJsonP("\""+"Y"+"\"",callback);
                        writer.write(s);
                        if(writer!=null){
                            writer.flush();
                            writer.close();
                        }
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

    public String  getServiceAreaByCity(){
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

    private JsonConfig getConfig(){
        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        config.setExcludes(new String[]{
                "cartitems", "orderitems", "salesBooks", "subscribeItems", "salesBooks", "schoolCategories"
                ,"feedbacks","orderses","schools","password"
        });
        return config;
    }
    private void writeTouser(PrintWriter writer,String json,String callback){
        if(callback==null){
            writer.write(json);
        }else{
            String s = CommonUtil.getJsonP(json,callback);
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


    public void setUploadHI(File uploadHI) {
        this.uploadHI = uploadHI;
    }


    public void setUploadHIFileName(String uploadHIFileName) {
        this.uploadHIFileName = uploadHIFileName;
    }


    public void setUploadHIFileContentType(String uploadHIFileContentType) {
        this.uploadHIFileContentType = uploadHIFileContentType;
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

    public void setHeight(int height) {
        this.height = height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setSchoolService(SchoolService schoolService) {
        this.schoolService = schoolService;
    }
}
