package weixin.manager;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CommonUtil;
import utils.MenuUtil;
import weixin.pojo.*;

import java.io.UnsupportedEncodingException;


public class MenuManager {
    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    /**
     * 定义菜单结构
     *
     * @return
     * @throws UnsupportedEncodingException
     */
    private static Menu getMenu() throws UnsupportedEncodingException {

        String URL="http://www.ychbooks.com/openId.action";

        String shopUrl  = CommonUtil.getRedirectUrlEncode(URL);
        log.info("url:"+shopUrl);
        ViewButton btn1 = new ViewButton();
        btn1.setName("线上商城");
        btn1.setType("view");
        btn1.setUrl(shopUrl);

        String userCenterUrl = shopUrl;
        ViewButton btn21 = new ViewButton();
        btn21.setName("个人中心");
        btn21.setType("view");
        btn21.setUrl(userCenterUrl);

        String sellBookUrl = shopUrl;
        ViewButton btn22 = new ViewButton();
        btn22.setName("卖书通道");
        btn22.setType("view");
        btn22.setUrl(sellBookUrl);

        ClickButton btn31 = new ClickButton();
        btn31.setName("公司简介");
        btn31.setType("click");
        btn31.setKey("ofUs");

        ClickButton btn32 = new ClickButton();
        btn32.setName("服务电话");
        btn32.setType("click");
        btn32.setKey("phoneService");




        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("会员中心");
        mainBtn2.setSub_button(new Button[] { btn21, btn22});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("关于");
        mainBtn3.setSub_button(new Button[] { btn31,btn32});

        Menu menu = new Menu();
        menu.setButton(new Button[] { btn1, mainBtn2, mainBtn3 });

        return menu;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        // 第三方用户唯一凭证
        String appId = "wx8cb51e4a231bfd37";
        // 第三方用户唯一凭证密钥
        String appSecret = "8e26e669ec00064d21f1eadb3f71b797";

        // 调用接口获取凭证
        Token token = CommonUtil.getToken(appId, appSecret);

        if (null != token) {
            // 创建菜单
            boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());
            System.out.println(getMenu().toString());
            String menu = MenuUtil.getMenu(token.getAccessToken());
            System.out.println(menu);
            // 判断菜单创建结果
            if (result)
                System.out.println("成功");
            else {
                log.info("菜单创建失败！");
                System.out.println("创建失败");
            }
        }
    }
}
