package weixin.pojo;
/**
 * 临时二维码pojo
 * @author Lixiao
 *
 */

public class QRScene {
	//获取二维码的ticket
	private String ticket;
	//二维码的有效时间，单位为秒，最长不超过1800秒
	private int expireSeconds;
	
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public int getExpireSeconds() {
		return expireSeconds;
	}
	public void setExpireSeconds(int expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
	
	

}
