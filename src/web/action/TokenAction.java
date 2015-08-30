package web.action;
import java.io.InputStream;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;
import service.CoreService;
import utils.SignUtil;

@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
public class TokenAction extends ActionSupport {
    private static Logger logger = Logger.getLogger(TokenAction.class);

    @Override
    @Action(
            value="token"
    )
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 微信加密签名
        String signature = request.getParameter("signature");

        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        logger.info("sinnamture:"+signature+"----nonce:"+nonce);
        PrintWriter out = response.getWriter();
        boolean checkedToken = SignUtil.checkSignature(signature, timestamp, nonce);
        logger.info("checkedToken:"+checkedToken);
        if (checkedToken) {
            String method = ServletActionContext.getRequest().getMethod();
            if (method.equals("POST")) {
                // 调用核心服务类接收处理请求
                logger.info("method:"+method);
                String respXml = CoreService.processRequest(request);

                logger.info("respXml:"+respXml);
                out.print(respXml);
            } else {
                out.print(echostr);
            }


        }
        out.close();
        out=null;


        return NONE;
    }
    public  Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        // 释放资源
        inputStream.close();
        inputStream = null;

        return map;
    }


}
