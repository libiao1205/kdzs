package com.sundeinfo.core.utility;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String sendSms(String text, String mobile){
        Map<String,String> params = new HashMap<String, String>();
        params.put("apikey","fdd10b786cd841a8b27f36b8f2d80457");
        params.put("text",text);
        params.put("mobile",mobile);
        return post("https://sms.yunpian.com/v2/sms/single_send.json", params);
    }

    private static String post(String url, Map<String, String> params) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            if(params != null){
                List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                for(Map.Entry<String,String> param: params.entrySet()){
                    NameValuePair pair = new BasicNameValuePair(param.getKey(),param.getValue());
                    pairList.add(pair);
                }
                httpPost.setEntity(new UrlEncodedFormEntity(pairList,"UTF-8"));
            }
            response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if(entity != null){
                responseText = EntityUtils.toString(entity,"UTF-8");
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                response.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return responseText;
    }

    public static String saveFile(MultipartFile file,String path){
        try {
            String originalFilename = file.getOriginalFilename();
            //获取文件名后缀 
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);

            DateFormat bf = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date();
            String format = bf.format(date);

            String filePath = path + "/" + format;
            File dirFile = new File(filePath);
            if(!dirFile.exists()){
                dirFile.mkdirs();
            }
            DateFormat bf2 = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date2 = new Date();
            String format2 = bf2.format(date2);
            String newFileName  = format2 + "."+ suffix;
            File f = new File(dirFile.getAbsoluteFile() + "//" + newFileName);
            file.transferTo(f);
            f.createNewFile();
            return format + "//" + newFileName;
        }catch(Exception e) {
            return null;
        }
    }


    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            return isMatch;
        }
    }
}
