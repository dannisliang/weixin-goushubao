package service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import utils.MessageUtil;
import weixin.resp.pojo.TextMessage;


public class CoreService {
	private static Logger log =Logger.getLogger(CoreService.class);
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = "";
		// 默认返回的文本消息内容
		String respContent = " ";
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			//消息内容
			

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content").trim();
				log.info("Content:"+content);
				boolean isHelp =(content.equals("?")||content.equals("帮助")||content.equals("help"));
				log.info("isHelp:"+isHelp);
				if(isHelp){
					respContent = "这里应该有帮助信息的！";
					textMessage.setContent(respContent);
					respXml = MessageUtil.messageToXml(textMessage);
				}
				
				
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				
			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 关注
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "这里应该有图文信息的！";
					textMessage.setContent(respContent);
					respXml = MessageUtil.messageToXml(textMessage);
				}
				// 取消关注
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
				}
				// 扫描带参数二维码
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// TODO 处理扫描带参数二维码事件
				}
				// 上报地理位置
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// TODO 处理上报地理位置事件
				}
				// 自定义菜单
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					//获得时间的Key值
					String keyValue = requestMap.get("EventKey");
					if(keyValue.equals("ofUs")){
						respContent = "这里应该有我们公司的简介！";
						textMessage.setContent(respContent);
						respXml = MessageUtil.messageToXml(textMessage);
						
					}else if(keyValue.equals("phoneService")){
						respContent = "联系电话：13122210065";
						textMessage.setContent(respContent);
						respXml = MessageUtil.messageToXml(textMessage);
					}else{
						//do nothing!
					}
					
				}
			}
			
			log.info("respXML："+respXml);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
	
}

