package com.project.mall.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * 通用的JSON返回类型
 *
 * @ JsonInclude注解的作用是当某个键为空时不出现在最终的JSON字符串中
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResult {
    /**
     * 状态码，这个状态码应该和HTTP状态码保持一致
     */
    private int code;
    /**
     * 如果有错误时的错误信息
     */
    private String error;
    /**
     * 如果是查询操作或一些也需要携带返回数据的操作，可以添加在data中
     */
    private Object data;

    private List dataList;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    /**
     * 用于构建成功时的JSON对象
     *
     * @param code 状态码
     * @param data 要携带的数据
     * @return 成功时的JSON对象
     */
    public static JsonResult buildSuccess(int code, Object data) {
        JsonResult jsonResult  = new JsonResult();
        jsonResult.setCode(code);
        jsonResult.setData(data);
        return jsonResult;
    }


    /**
     * 用于构建失败时的JSON对象
     *
     * @param code   状态码
     * @param errMsg 错误信息
     * @return 失败时的JSON对象
     */
    public static JsonResult buildFailure(int code, String errMsg) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(code);
        jsonResult.setError(errMsg);
        return jsonResult;
    }
}
