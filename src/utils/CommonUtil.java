package utils;


import domain.Book;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weixin.pojo.KF;
import weixin.pojo.OAuthToken;
import weixin.pojo.QRScene;
import weixin.pojo.Token;


import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.math.BigInteger;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;


public class CommonUtil {
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    // 凭证获取（GET）
    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public final static String  requestMethodGet = "GET";
    public final static String  requestMethodPost = "POST";

    /**
     * MD5加密
     */
    public static String getSecretByMd5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }


    /**
     * JSONP
     */
    public static String getJsonP(String jsonArray,String callback){
        String result = callback+"(";
        result += jsonArray+")";
        return result;
    }
    /**
     * uuid 获得唯一id
     */
    public static String UUID16(){

        return UUID.randomUUID().toString().replaceAll("-","");
    }
    /**
     * 上架的书籍相似度查询结果排序
     */
    public static List<Book> getArrayList(List<Book> bookList,String match){
        List<Book> sortList =  bookList;
        for(int i=0;i<sortList.size();i++){
            float levenshtein = levenshtein(sortList.get(i).getTitle(), match);
         //   sortList.get(i).setSimliar(levenshtein);
        }
        //Collections.sort(sortList);
        return sortList;
    }

    //相似度查询
    public static float levenshtein(String str1,String str2) {
        //计算两个字符串的长度。
        int len1 = str1.length();
        int len2 = str2.length();
        //建立上面说的数组，比字符长度大一个空间
        int[][] dif = new int[len1 + 1][len2 + 1];
        //赋初值，步骤B。
        for (int a = 0; a <= len1; a++) {
            dif[a][0] = a;
        }
        for (int a = 0; a <= len2; a++) {
            dif[0][a] = a;
        }
        //计算两个字符是否一样，计算左上的值
        int temp;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                //取三个值中最小的
                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,
                        dif[i - 1][j] + 1);
            }
        }
        float similarity =1 - (float) dif[len1][len2] / Math.max(str1.length(), str2.length());
        return similarity;
    }
    //得到最小值
    private static int min(int... is) {
        int min = Integer.MAX_VALUE;
        for (int i : is) {
            if (min > i) {
                min = i;
            }
        }
        return min;
    }


    /**
     * 获得当前时间
     * @return
     */
    public static String getDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(date);
        return time;
    }
    /**
     * 发送https请求
     *
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);

            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("连接超时：{}", ce);
        } catch (Exception e) {
            log.error("https请求异常：{}", e);
        }
        return jsonObject;
    }

    /**
     * 获取接口访问凭证
     *
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static Token getToken(String appid, String appsecret) {
        Token token = null;
        String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        // 发起GET请求获取凭证
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                token = new Token();
                token.setAccessToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                token = null;
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return token;
    }

    //创建临时二维码
    public static QRScene createQRScene(String acessToken,int expireSeconds,int sceneId){
        QRScene qRScene = null;
        //拼接微信请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
        requestUrl = requestUrl.replaceAll("TOKEN", acessToken);
        String jsonMsg = "{\"expire_seconds\": %d, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
        jsonMsg	= String.format(jsonMsg, expireSeconds,sceneId);
        JSONObject jsonObject = httpsRequest(requestUrl, requestMethodPost, jsonMsg);
        if(null !=jsonObject){
            try{
                qRScene= new QRScene();
                qRScene.setTicket(jsonObject.getString("ticket"));
                qRScene.setExpireSeconds(jsonObject.getInt("expire_seconds"));
                log.info("ticket: "+qRScene.getTicket()+"\n expire_seconds:"+qRScene.getExpireSeconds()+"\n url:"+jsonObject.getString("url"));
            }catch (Exception e) {
                log.info("创建二维码失败："+jsonObject.getString("errcode")+jsonObject.getString("errmsg"));
            }
        }
        return qRScene;
    }
    //OAuth2.0网页授权
    public static String  getOpenId(String appId,String secret,String code){
        OAuthToken oAuthToken = null;
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replaceAll("APPID", appId).replace("SECRET", secret).replace("CODE", code);
        log.info("=================requestUrl:"+requestUrl+"=================");

        JSONObject jsonObject = httpsRequest(requestUrl, requestMethodGet, null);
        String openId = jsonObject.getString("openid");

        return openId;


    }
    //OAuth2.0网页授权获得用户基本信息
    public static OAuthToken getUserInfo(String accessToken,String openId){
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        String url = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        JSONObject jsonObject = httpsRequest(url, requestMethodGet, null);
        OAuthToken authToken = new OAuthToken();
        authToken.setOpenId(openId);
        authToken.setHeadUrl(jsonObject.getString("headimgurl"));
        authToken.setNickName(jsonObject.getString("nickname"));
        authToken.setSex(jsonObject.getInt("sex"));
        authToken.setAddr(jsonObject.getString("province")+jsonObject.getString("city"));
        return authToken;
    }

    //获得redirect_url
    public static String getRedirectUrlEncode(String redirectUrl) throws UnsupportedEncodingException{
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8cb51e4a231bfd37&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
        String URL=redirectUrl;

        String ENCODING = "utf-8";
        log.info("url:"+url);
        String strURL1= URLEncoder.encode(URL, ENCODING).replace("*","*").replace("~", "~").replace("+"," ");
        url = url.replace("REDIRECT_URI", strURL1);

        return url;

    }
    /**
     * 创建客服接口
     * @param kf
     * @param accessToken
     * @return
     */
    public static boolean createKFAccount(KF kf,String accessToken){
        String url = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", accessToken);
        String kefeDetail = JSONObject.fromObject(kf).toString();
        JSONObject jsonObject = httpsRequest(url, "POST", kefeDetail);
        log.info(url);
        log.info(kefeDetail);
        boolean result =false;
        if (null != jsonObject) {
            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            if (0 == errorCode) {
                result = true;
            } else {
                result = false;
                log.error("创建客服失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return result;
    }
    //获得调用jssdk的Ticket
    public static String getJssdkTicket(String accessToken){
        String ticket = "";
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = httpsRequest(requestUrl, requestMethodGet, null);
        if(null!=jsonObject){
            try {
                ticket=jsonObject.getString("ticket");
            } catch (JSONException e) {
                ticket = null;
            }
        }
        return ticket;
    }


    //获得特定长度的字符串
    private String createNonceStr(int length)
    {
        Random r = new Random();
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String str = "";
        for (int i = 0; i < length; i++)
        {
            str += chars.substring(r.nextInt(58), 1);
        }
        return str;
    }

}