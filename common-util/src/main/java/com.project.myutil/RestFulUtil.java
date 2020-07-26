package com.project.myutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * rest请求工具类
 */
public class RestFulUtil {

    // 方法请求方式
    private static final String REQUEST_POST = "POST";
    private static final String REQUEST_GET = "GET";
    // private static final String REQUEST_DELETE = "DELETE";
    // private static final String REQUEST_PUT = "PUT";
    // private static final String REQUEST_PATCH = "PATCH";
    // private static final String REQUEST_OPTIONS = "OPTIONS";
    // 发送报文编码
    private static final String CHARSET_UTF_8 = "UTF-8";
    private static final String CHARSET_GBK = "GBK";
    private static final String HEADER_JSON = "application/json";

    /**
     * @param restUrl     请求地址
     * @param requestType 请求方式
     */
    private RestFulUtil(String restUrl, String requestType) {
        this.mRestUrl = restUrl;
        this.mRequestType = requestType;
    }

    public RestFulUtil() {
    }

    // 方法请求地址
    private String mRestUrl = null;
    // 请求方式
    private String mRequestType = null;
    // 请求编码
    private String mRequestCharSet = null;


    private void setRestUrl(String restUrl) {
        this.mRestUrl = restUrl;
    }

    public String getRestUrl() {
        return this.mRestUrl;
    }

    private void setRequestType(String requestType) {
        this.mRequestType = requestType;
    }

    public String getRequestType() {
        return this.mRequestType;
    }

    public void setRequestCharSet(String requestCharSet) {
        this.mRequestCharSet = requestCharSet;
    }

    public String getRequestCharSet() {
        return this.mRequestCharSet;
    }


    private HttpURLConnection connection = null;
    //OutputStream dataout = null;
    private BufferedReader reader = null;

    private Object doRest() {
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(mRestUrl);
            connection = (HttpURLConnection) url.openConnection();// 根据URL生成HttpURLConnection
            connection.setDoOutput(true);// 设置是否向connection输出，如果是post请求，参数要放在http正文内，因此需要设为true,默认情况下是false
            connection.setDoInput(true); // 设置是否从connection读入，默认情况下是true;
            connection.setConnectTimeout(30000);//链接超时时间 单位：毫秒
            connection.setReadTimeout(30000);//读取超时时间
            connection.setRequestMethod(mRequestType);// 设置请求方式为post,默认GET请求

            connection.setRequestProperty("charset", CHARSET_UTF_8);
            connection.setRequestProperty("Content-Type", HEADER_JSON);
            connection.connect();// 建立TCP连接,如果getOutputStream会隐含的进行connect,此处可以不要
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), CHARSET_UTF_8));// 发送http请求
                // 循环读取流
                // 发送参数
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result.append(line).append(System.getProperty("line.separator"));//获取系统默认的换行符进行拼接
                }
                System.out.println(result.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.disconnect();
        }
        return result;
    }

    public static void main(String[] args) {
        RestFulUtil util = new RestFulUtil("http://localhost:7001/findAll", RestFulUtil.REQUEST_GET);
        util.doRest();

        util.setRestUrl("http://localhost:7001/find/1");
        util.setRequestType(RestFulUtil.REQUEST_POST);
        util.doRest();
    }
}
