package utils;

import com.cloopen.rest.sdk.CCPRestSDK;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 * Created by Lixiao on 8/29/2015.
 */
public class YunCheck {
    public static String randomCode(){
        String result="";
        Random random = new Random();
        for(int i=0;i<6;i++){
           int tem = random.nextInt(10);
            result+=tem;
        }
        return result;
    }

    public static String sendVoiceToPhone(String tel) {
        HashMap<String, Object> result = null;
        String code="";
        Random random = new Random();
        for(int i=0;i<6;i++){
            int tem = random.nextInt(10);
            code+=tem;
        }
        CCPRestSDK restAPI = new CCPRestSDK();
        restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
        restAPI.setAccount("aaf98f894cfa16bc014d039ced990783", "52591dc2aedd49a8b74027f7cafcd6e6");// 初始化主帐号和主帐号TOKEN
        restAPI.setAppId("aaf98f894cfa16bc014d039ec4940785");// 初始化应用ID
        result = restAPI.voiceVerify(code, tel,"13122210063","3","","","");

        System.out.println("SDKTestVoiceVerify result=" + result);
        if("000000".equals(result.get("statusCode"))){
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
        }
        return code;
    }

    public static void main(String[] args) {
       String code = randomCode();
        System.out.println(sendVoiceToPhone("18817953402"));
    }
}
