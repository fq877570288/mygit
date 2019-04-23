package cn.unihotel.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * create by 付琦 2019-04-20
 * 预定系统实体
 */

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReserveInfo implements Serializable {
    /**
     * 预定信息号
     */
    private String reserveId;
    /**
     * 身份证号
     */
    private String idCardNum;
    /**
     * 预定人
     */
    private String reservePerson;
    /**
     * 电话号码
     */
    private String phoneNum;
    /**
     * 预定时间
     */
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd")
    private String reserveTime;
    /**
     * 房间号
     */
    private String roomNo;
    /**
     * 房间名称
     */
    private String roomtypeName;
    /**
     * 房间类型
     */
    private Integer roomtypeId;
    /**
     * 预定方式
     */
    private Integer reserveWay;

    //private static final long serialVersionUID = 1L;

    public ReserveInfo() {
    }

    public String getReserveId() {
        return reserveId;
    }

    public void setReserveId(String reserveId) {
        this.reserveId = reserveId;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public String getReservePerson() {
        return reservePerson;
    }

    public void setReservePerson(String reservePerson) {
        this.reservePerson = reservePerson;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(String reserveTime) {
        this.reserveTime = reserveTime;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomtypeName() {
        return roomtypeName;
    }

    public void setRoomtypeName(String roomtypeName) {
        this.roomtypeName = roomtypeName;
    }

    public Integer getRoomtypeId() {
        return roomtypeId;
    }

    public void setRoomtypeId(Integer roomtypeId) {
        this.roomtypeId = roomtypeId;
    }

    public Integer getReserveWay() {
        return reserveWay;
    }

    public void setReserveWay(Integer reserveWay) {
        this.reserveWay = reserveWay;
    }

    @Override
    public String toString() {
        return "ReserveInfo{" +
                "reserveId='" + reserveId + '\'' +
                ", idCardNum='" + idCardNum + '\'' +
                ", reservePerson='" + reservePerson + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", reserveTime='" + reserveTime + '\'' +
                ", roomNo='" + roomNo + '\'' +
                ", roomtypeName='" + roomtypeName + '\'' +
                ", roomtypeId=" + roomtypeId +
                ", reserveWay=" + reserveWay +
                '}';
    }
}