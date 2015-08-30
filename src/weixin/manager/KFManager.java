package weixin.manager;


import utils.CommonUtil;
import weixin.pojo.KF;
import weixin.pojo.Token;

public class KFManager {
		public static void main(String[] args) {
						// 第三方用户唯一凭证
						String appId = "wx8cb51e4a231bfd37";
						// 第三方用户唯一凭证密钥
						String appSecret = "8e26e669ec00064d21f1eadb3f71b797";

						// 调用接口获取凭证
						Token token = CommonUtil.getToken(appId, appSecret);
						
						KF kf = new KF();
						kf.setKf_account("nihao@gh_4091f3d17380");
						kf.setNickname("nihao");
						kf.setPassword("123456789g");
						
						boolean createKFAccount = CommonUtil.createKFAccount(kf, token.getAccessToken());
						
						if(createKFAccount){
							System.out.println("创建成功");
						}else{
							System.out.println("创建失败");
						}
		
			}
	}