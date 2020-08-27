package com.project.myutil.connect;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import java.util.Timer;

/**
 * 这个工具类基于cxf动态调用webservice接口。
 *
 * @author Reisen
 */
@Slf4j
public class WebServiceUtil {

    /**
     * 后缀
     */
    public static final String WSDL = "wsdl";

    /**
     * @param url           webservice地址
     * @param operationName 调用的方法名
     * @param reqDTO        入参
     * @return object[]
     */
    public static Object[] invoke(String url, String operationName, Object reqDTO) {


        if (url != null && !url.trim().endsWith(WSDL)) {
            url = url + "?" + WSDL;
        }
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(url);
        Object[] objects = new Object[1];
        try {
            log.info("调用了服务:{},发送内容为:{}", url, reqDTO);
            objects = client.invoke(operationName, reqDTO);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("调用过程中发生异常");
        }
        return objects;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }
}
