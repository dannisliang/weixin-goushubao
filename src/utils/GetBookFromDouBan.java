package utils;

import domain.Book;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Lixiao on 8/31/2015.
 */
public class GetBookFromDouBan {
    private static Logger log = Logger.getLogger(GetBookFromDouBan.class);

    public static Book getISBNInfo(String isbnCode) throws Exception {
        String url = "https://api.douban.com/v2/book/isbn/" + isbnCode
                + "?fields=title,author,publisher,images,price,isbn13";

        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        log.info("从豆瓣上找书，书的url："+url);
        HttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        BufferedReader br = new BufferedReader(new InputStreamReader(
                entity.getContent(), "utf-8"));
        StringBuffer buffer = new StringBuffer();
        String temp = null;
        while ((temp = br.readLine()) != null) {
            buffer.append(temp);
        }
        log.info(buffer);
        JSONObject jb = JSONObject.fromObject(buffer.toString());
        try {
            Book isbnBook = new Book();
            // 将请求到的JSON数据封装到对象中
            // 封装书名,书名必须要有
            String title = jb.getString("title");
            isbnBook.setTitle(title);

            // 封装isbn号码,isbn必须有
            String isbn = jb.getString("isbn13");
            isbnBook.setIsbn(isbn);
            // 价格必须有
            String pString = jb.getString("price");
            char[] b = pString.toCharArray();
            String result = "";
            for (int i = 0; i < b.length; i++) {
                if (("0123456789.").indexOf(b[i] + "") != -1) {
                    result += b[i];
                }
            }
            double price = Double.parseDouble(result);
            isbnBook.setPrice(price);

            System.out.println(title + "---" + isbn);
            // 封装作者,若有多个制作者，就拼成字符串
            JSONArray authors = jb.getJSONArray("author");

            if (authors == null|| authors.toString().equals("[]")) {
                isbnBook.setAuthor("");
            } else {
                String author = "";
                for (int i = 0; i < authors.size(); i++) {
                    author += (String) authors.get(i) + ",";
                }
                author = author.substring(0, author.length() - 1);
                isbnBook.setAuthor(author);
                log.info(author);
            }
            // 封装出版社
            String publisher = jb.getString("publisher");
            if (publisher == null) {
                isbnBook.setPublisher(null);
            } else {
                isbnBook.setPublisher(publisher);
                log.info(publisher);
            }
            JSONObject  imagesUrl = jb.getJSONObject("images");
            String smallImage = imagesUrl.getString("small");
            if(smallImage!=null||smallImage!="[]"){
                // 书本封面图片命名为isbn+扩展名
                String subName = smallImage.substring(smallImage.lastIndexOf("."));
                String imageName = isbn + subName;
                String savePath = ServletActionContext.getRequest().getRealPath("/bookImage");
                // 图片存储在服务器
                String bookImage = "/bookImage/"+imageName;
                isbnBook.setImage(bookImage);
                FileOperateUtil.getRemoteFile(smallImage, savePath + "/"
                        + imageName);
            }
            smallImage = imagesUrl.getString("large");
            if(smallImage!=null||smallImage!="[]"){
                // 书本封面图片命名为isbn+扩展名
                String subName = smallImage.substring(smallImage.lastIndexOf("."));
                String imageName = isbn+"1" + subName;
                String savePath = ServletActionContext.getRequest().getRealPath("/bookImage");
                // 图片存储在服务器
                String bookImage = "/bookImage/"+imageName;
                isbnBook.setBigImage(bookImage);
                FileOperateUtil.getRemoteFile(smallImage, savePath + "/"
                        + imageName);
            }
            return isbnBook;
        } catch (Exception e) {
            // 如果没有书名和isbn号码，则认为没找到，返回空
            System.out.println(e.getMessage());
            log.info("something is wrrong	：");
            return null;
        }
    }

    public static void main(String[] args) throws Exception{
        Book book = getISBNInfo("9787560534051");
       if (book!=null){
           System.out.println(book.getTitle());
       }
    }

}
