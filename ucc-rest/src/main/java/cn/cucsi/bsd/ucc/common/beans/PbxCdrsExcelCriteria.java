/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.cucsi.bsd.ucc.common.beans;

import cn.cucsi.bsd.ucc.common.untils.AttributeName;
import java.util.Date;

/**
 *
 * @author Brigth
 */
public class PbxCdrsExcelCriteria {
    private Integer id;
    @AttributeName("主叫")
    private String firstCaller;

    @AttributeName("被叫")
    private String firstCallee;

    @AttributeName("总时长（秒）")
    private Integer totalTime;

    @AttributeName("接通时长")
    private Integer callTime;

    @AttributeName("开始时间")
    private Date createdTime;

    @AttributeName("应答时间")
    private Date answeredTime;

    @AttributeName("挂机时间")
    private Date hangupTime;

    @AttributeName("挂机原因")
    private String hangupCause;

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setHangupTime(Date hangupTime) {
        this.hangupTime = hangupTime;
    }

    public void setHangupCause(String hangupCause) {
        this.hangupCause = hangupCause;
    }

    public void setFirstCaller(String firstCaller) {
        this.firstCaller = firstCaller;
    }

    public void setFirstCallee(String firstCallee) {
        this.firstCallee = firstCallee;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setCallTime(Integer callTime) {
        this.callTime = callTime;
    }

    public void setAnsweredTime(Date answeredTime) {
        this.answeredTime = answeredTime;
    }

    public Integer getId() {
        return id;
    }

    public Date getHangupTime() {
        return hangupTime;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public String getHangupCause() {
        return hangupCause;
    }

    public String getFirstCaller() {
        return firstCaller;
    }

    public String getFirstCallee() {
        return firstCallee;
    }

    public Date getAnsweredTime() {
        return answeredTime;
    }

    public Integer getCallTime() {
        return callTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }
    

        

}
