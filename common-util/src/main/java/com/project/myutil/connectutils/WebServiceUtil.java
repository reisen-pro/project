package com.project.myutil.connectutils;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * 这个工具类基于cxf动态调用webservice接口。
 */
@Slf4j
public class WebServiceUtil {
    /**
     * @param url           webservice地址
     * @param operationName 调用的方法名
     * @param reqDTO        入参
     * @return object[]
     */
    public static Object[] invoke(String url, String operationName, Object reqDTO) {
        if (url != null && !url.trim().endsWith("wsdl")) {
            url = url + "?wsdl";
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
}
