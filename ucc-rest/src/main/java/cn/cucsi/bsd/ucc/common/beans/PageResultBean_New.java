package cn.cucsi.bsd.ucc.common.beans;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.pagehelper.Page;

import java.io.Serializable;

/**
 * 分页返回结果(mybatis分页用)
 * add by wangxiaoyu
 * 2018-08-29
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PageResultBean_New<T>  implements Serializable {

    private int pageSize; //每页显示几条记录
    private long totalElements; //总记录数
    private int totalPages; //总页数
    private int pageNum; //页号
    private int count; //当前有几条记录

    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    private String returnMsg = "success";
    private String returnCode = SUCCESS;

    @JsonUnwrapped
    private T list;

    public PageResultBean_New(){
    }
    public PageResultBean_New(T t){
        this.list = t;
    }

    public PageResultBean_New(Page page){
        this.setPageNum(page.getPageNum());
        this.setPageSize(page.getPageSize());
        this.setTotalElements(page.getTotal());
        this.setTotalPages(page.getPages());
        this.setCount(page.size());
        this.setList((T) page.getResult());

    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }

    public static String getSUCCESS() {
        return SUCCESS;
    }

    public static String getFAIL() {
        return FAIL;
    }

}
