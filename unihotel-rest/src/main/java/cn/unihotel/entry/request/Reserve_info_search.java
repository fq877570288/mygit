package cn.unihotel.entry.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * create by 付琦 2019-4-20
 * 预定系统查询条件实体
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Reserve_info_search {
    private String reserveId;//id
    private String idCardNum;//身份证号
    private String reservePerson;//预定人姓名

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String paTimeFrom;//入住开始时间

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String paTimeTo;//入住结束时间

    private int pageNum;//当前页码
    private int pageSize;//每页条数

    public Reserve_info_search() {
    }

    public String getReserveId() {
        return reserveId;
    }

    public void setReserveId(String reserveId) {
        this.reserveId = reserveId;
    }

    public String getReservePerson() {
        return reservePerson;
    }

    public void setReservePerson(String reservePerson) {
        this.reservePerson = reservePerson;
    }

    public String getPaTimeFrom() {
        return paTimeFrom;
    }

    public void setPaTimeFrom(String paTimeFrom) {
        this.paTimeFrom = paTimeFrom;
    }

    public String getPaTimeTo() {
        return paTimeTo;
    }

    public void setPaTimeTo(String paTimeTo) {
        this.paTimeTo = paTimeTo;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }
}
