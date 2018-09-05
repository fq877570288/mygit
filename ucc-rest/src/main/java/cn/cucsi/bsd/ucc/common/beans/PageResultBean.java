package cn.cucsi.bsd.ucc.common.beans;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Page;

public class PageResultBean<U> extends ResultBean<U> {
    @JsonView(JSONView.Summary.class)
    private int size = 20;
    @JsonView(JSONView.Summary.class)
    private long totalElements;
    @JsonView(JSONView.Summary.class)
    private int totalPages;
    @JsonView(JSONView.Summary.class)
    private int number;

    public PageResultBean(){}

    public PageResultBean(Page page){
        this.setNumber(page.getNumber());
        this.setSize(page.getSize());
        this.setTotalElements(page.getTotalElements());
        this.setTotalPages(page.getTotalPages());
        this.setData((U) page.getContent());
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
