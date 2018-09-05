package cn.cucsi.bsd.ucc.common.beans;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;

/**
 * 不分页返回结果
 * add by wangxiaoyu
 * 2018-08-29
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResultBean_New <T> implements Serializable {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    private String returnMsg = "success";
    private String returnCode = SUCCESS;

    @JsonUnwrapped
    private T data;

    public ResultBean_New() {
    }

    public ResultBean_New(String returnCode, String returnMsg) {
        this.returnMsg = returnMsg;
        this.returnCode = returnCode;

    }
    public ResultBean_New(T t){
        this.data = t;
    }

    public ResultBean_New(Throwable e) {
        super();
        this.returnMsg = e.toString();
        this.returnCode = FAIL;
    }

    public static String getSUCCESS() {
        return SUCCESS;
    }

    public static String getFAIL() {
        return FAIL;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
