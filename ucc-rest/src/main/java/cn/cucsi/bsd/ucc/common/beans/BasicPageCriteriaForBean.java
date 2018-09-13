package cn.cucsi.bsd.ucc.common.beans;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * 分页基础类，包含排序和分页属性
 * add by wangxiaoyu
 * 2018-08-29
 */
public class BasicPageCriteriaForBean {

    private Integer pageSize = 10;
    private Integer pageNum = 1;
    private String orderBy;    //排序字段
    private String orderType;  //排序方式

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
