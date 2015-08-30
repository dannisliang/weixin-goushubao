package web.action;



import javax.servlet.http.HttpSession;

        import domain.User;
        import domain.WeixinUser;
        import org.apache.struts2.ServletActionContext;
        import org.apache.struts2.convention.annotation.Action;
        import org.apache.struts2.convention.annotation.Namespace;
        import org.apache.struts2.convention.annotation.ParentPackage;
        import org.apache.struts2.convention.annotation.Result;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.context.annotation.Scope;


        import com.opensymphony.xwork2.ActionSupport;
        import service.UserService;
        import service.WeixinUserService;
        import utils.CommonUtil;
        import weixin.pojo.OAuthToken;

@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
public class GetOpenidAction extends ActionSupport {
    private static Logger log = LoggerFactory.getLogger(GetOpenidAction.class);
    private WeixinUserService weixinUserService;
    private UserService userService;
    private String code;

    @Override
    @Action(
            value = "openId",
            results ={
                    @Result(name="success",location="/userpage/index.jsp"),
                    @Result(name="findSchool",location="/userpage/chooseScho.jsp")
            }
    )
    public String execute() throws Exception {
        // �������û�Ψһƾ֤
        String appId = "wx8cb51e4a231bfd37";
        // �������û�Ψһƾ֤��Կ
        String appSecret = "8e26e669ec00064d21f1eadb3f71b797";

        HttpSession session = ServletActionContext.getRequest().getSession();
        //�ж�session����û��openid
        String openId = (String)session.getAttribute("openId");
        User user= null;
        if(openId==null){
            openId = CommonUtil.getOpenId(appId, appSecret, code);
            session.setAttribute("openId", openId);
        }
        boolean findWeixinUser = weixinUserService.findWeixinUser(openId);
        //�жϴ��û��ǲ����״ε�½
        if (!findWeixinUser) {
            OAuthToken userInfo = CommonUtil.getUserInfo(CommonUtil.getToken(appId, appSecret).getAccessToken(), openId);
            if(userInfo !=null){
                user = new User();
                WeixinUser weixinUser = new WeixinUser();
                user.setName(userInfo.getNickName());
                user.setAddr(userInfo.getAddr());
                user.setSex(userInfo.getSex());
                user.setHeadImage(userInfo.getHeadUrl());
                user.setState(0);
                user.setRegistDate(CommonUtil.getDate());
                weixinUser.setOpenid(openId);
                weixinUser.setUser(user);
                userService.save(user);
                weixinUserService.save(weixinUser);
                session.setAttribute("user", user);
            }
        }else{
            user = (User)session.getAttribute("user");
            if(user==null){
                user = weixinUserService.getUserByOpenId(openId);
                session.setAttribute("user", user);
            }

        }

        if(user.getSchool()==null){
            return "findSchool";
        }


        return SUCCESS;
    }


    /**
     * property(set ,get)
     * @return
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public void setWeixinUserService(WeixinUserService weixinUserService) {
        this.weixinUserService = weixinUserService;
    }


    public void setUserService(UserService userService) {
        this.userService = userService;
    }



}
