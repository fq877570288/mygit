package cn.unihotel.entry.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Date;

/**
 * create by 付琦 2019-4-20
 * 客史列表查询条件实体
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MemberRecord_info_search {
    private String idCardNum;
    private String memberName;
    private String createdTimeFrom;
    private String createdTimeTo;
    private int pageNum;
    private int pageSize;

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCreatedTimeFrom() {
        return createdTimeFrom;
    }

    public void setCreatedTimeFrom(String createdTimeFrom) {
        this.createdTimeFrom = createdTimeFrom;
    }

    public String getCreatedTimeTo() {
        return createdTimeTo;
    }

    public void setCreatedTimeTo(String createdTimeTo) {
        this.createdTimeTo = createdTimeTo;
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

    @Override
    public String toString() {
        return "RemberRecordTO{" +
                "idCardNum='" + idCardNum + '\'' +
                ", memberName='" + memberName + '\'' +
                ", createdTimeFrom=" + createdTimeFrom +
                ", createdTimeTo=" + createdTimeTo +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
