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
        // ΢�ż���ǩ��
        String signature = request.getParameter("signature");

        // ʱ���
        String timestamp = request.getParameter("timestamp");
        // �����
        String nonce = request.getParameter("nonce");
        // ����ַ���
        String echostr = request.getParameter("echostr");

        logger.info("sinnamture:"+signature+"----nonce:"+nonce);
        PrintWriter out = response.getWriter();
        boolean checkedToken = SignUtil.checkSignature(signature, timestamp, nonce);
        logger.info("checkedToken:"+checkedToken);
        if (checkedToken) {
            String method = ServletActionContext.getRequest().getMethod();
            if (method.equals("POST")) {
                // ���ú��ķ�������մ�������
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
        // ����������洢��HashMap��
        Map<String, String> map = new HashMap<String, String>();

        // ��request��ȡ��������
        InputStream inputStream = request.getInputStream();
        // ��ȡ������
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // �õ�xml��Ԫ��
        Element root = document.getRootElement();
        // �õ���Ԫ�ص������ӽڵ�
        List<Element> elementList = root.elements();

        // ���������ӽڵ�
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        // �ͷ���Դ
        inputStream.close();
        inputStream = null;

        return map;
    }


}
