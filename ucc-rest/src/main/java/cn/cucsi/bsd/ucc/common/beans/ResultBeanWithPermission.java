package cn.cucsi.bsd.ucc.common.beans;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jjjjj on 2018-01-09.
 */
public class ResultBeanWithPermission<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int NO_LOGIN = -1;
    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
    public static final int NO_PERMISSION = 2;
    @JsonView(JSONView.Summary.class)
    private String msg = "success";
    @JsonView(JSONView.Summary.class)
    private int code = SUCCESS;
    @JsonView(JSONView.Summary.class)
    private T data;
    private List<String> roleNames;

    public ResultBeanWithPermission() {
        super();
    }

    public ResultBeanWithPermission(T data) {
        super();
        this.data = data;
    }

    public ResultBeanWithPermission(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }
}
