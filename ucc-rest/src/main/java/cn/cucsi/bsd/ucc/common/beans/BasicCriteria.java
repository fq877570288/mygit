package cn.cucsi.bsd.ucc.common.beans;
import io.swagger.annotations.ApiModel;

@ApiModel
public class BasicCriteria {
    private Integer page = 0;
    private Integer size = 15;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
