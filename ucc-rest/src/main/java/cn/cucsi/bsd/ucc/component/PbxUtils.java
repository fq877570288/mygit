package cn.cucsi.bsd.ucc.component;

/**
 * Created by jjjjj on 2018-01-02.
 */
public class PbxUtils {
    public static final String hangupCause2cn(String hangupCause) {
        String hangupCauseStr = "";
        if (hangupCause != null) {
            switch (hangupCause) {
                case "null": {
                    hangupCauseStr =  "外呼-未进行呼叫";
                    break;
                }
                case "UNSPECIFIED": {
                    hangupCauseStr =  "不明错误";
                    break;
                }
                case "CALL_AWARDED_DELIVERED": {
                    hangupCauseStr =  "呼叫前转";
                    break;
                }
                case "NORMAL_CLEARING": {
                    hangupCauseStr =  "正常挂断";
                    break;
                }
                case "USER_BUSY": {
                    hangupCauseStr =  "用户忙";
                    break;
                }
                case "NUMBER_CHANGED": {
                    hangupCauseStr =  "号码改变";
                    break;
                }
                case "REDIRECTION_TO_NEW_DESTINATION": {
                    hangupCauseStr =  "地址重定向";
                    break;
                }
                case "NO_USER_RESPONSE": {
                    hangupCauseStr =  "用户未响应";
                    break;
                }
                case "FACILITY_REJECTED": {
                    hangupCauseStr =  "服务器拒绝";
                    break;
                }
                case "RESPONSE_TO_STATUS_ENQUIRY": {
                    hangupCauseStr =  "RESPONSE_TO_STATUS_ENQUIRY";
                    break;
                }
                case "NORMAL_UNSPECIFIED": {
                    hangupCauseStr =  "未知的正常事件报告";
                    break;
                }
                case "NORMAL_CIRCUIT_CONGESTION": {
                    hangupCauseStr =  "线路拥堵";
                    break;
                }
                case "NETWORK_OUT_OF_ORDER": {
                    hangupCauseStr =  "网络错误";
                    break;
                }
                case "NORMAL_TEMPORARY_FAILURE": {
                    hangupCauseStr =  "临时性错误";
                    break;
                }
                case "FACILITY_NOT_SUBSCRIBED": {
                    hangupCauseStr =  "服务未授权";
                    break;
                }
                case "OUTGOING_CALL_BARRED": {
                    hangupCauseStr =  "禁止呼出";
                }
                case "INCOMING_CALL_BARRED": {
                    hangupCauseStr =  "禁止呼入";
                    break;
                }
                case "BEARERCAPABILITY_NOTAUTH": {
                    hangupCauseStr =  "用户功能受限";
                    break;
                }
                case "BEARERCAPABILITY_NOTAVAIL": {
                    hangupCauseStr =  "用户功能不可用";
                    break;
                }
                case "INVALID_MSG_UNSPECIFIED": {
                    hangupCauseStr =  "未知的错误消息";
                    break;
                }
                case "MANDATORY_IE_MISSING": {
                    hangupCauseStr =  "强制挂断";
                    break;
                }
                case "MESSAGE_TYPE_NONEXIST": {
                    hangupCauseStr =  "消息类型不符";
                    break;
                }
                case "WRONG_MESSAGE": {
                    hangupCauseStr =  "错误消息";
                    break;
                }
                case "WRONG_CALL_STATE": {
                    hangupCauseStr =  "错误呼叫状态";
                    break;
                }
                case "RECOVERY_ON_TIMER_EXPIRE": {
                    hangupCauseStr =  "超时";
                    break;
                }
                case "MANDATORY_IE_LENGTH_ERROR": {
                    hangupCauseStr =  "参数不存在或号码长度不符";
                    break;
                }
                case "USER_NOT_REGISTERED": {
                    hangupCauseStr =  "未注册";
                    break;
                }
                case "PROGRESS_TIMEOUT": {
                    hangupCauseStr =  "进程超时";
                    break;
                }
                case "GATEWAY_DOWN": {
                    hangupCauseStr =  "网关宕机";
                    break;
                }
                case "CALL_REJECTED": {
                    hangupCauseStr =  "呼叫拒绝";
                    break;
                }
                case "NO_ROUTE_TRANSIT_NET": {
                    hangupCauseStr =  "路由不可达";
                    break;
                }
                case "UNALLOCATED_NUMBER": {
                    hangupCauseStr =  "号码无效";
                    break;
                }
                case "NO_ROUTE_DESTINATION": {
                    hangupCauseStr =  "未送达落地";
                    break;
                }
                case "CHANNEL_UNACCEPTABLE": {
                    hangupCauseStr =  "信道拒绝";
                    break;
                }
                case "NO_ANSWER": {
                    hangupCauseStr =  "未应答";
                    break;
                }
                case "SUBSCRIBER_ABSENT": {
                    hangupCauseStr =  "用户不在线";
                    break;
                }
                case "EXCHANGE_ROUTING_ERROR": {
                    hangupCauseStr =  "交换路由错误";
                    break;
                }
                case "DESTINATION_OUT_OF_ORDER": {
                    hangupCauseStr =  "落地失效故障";
                    break;
                }
                case "INVALID_NUMBER_FORMAT": {
                    hangupCauseStr =  "号码格式无效";
                    break;
                }
                case "SWITCH_CONGESTION": {
                    hangupCauseStr =  "交换机堵塞";
                    break;
                }
                case "ACCESS_INFO_DISCARDED": {
                    hangupCauseStr =  "访问信息丢失";
                    break;
                }
                case "REQUESTED_CHAN_UNAVAIL": {
                    hangupCauseStr =  "电路接口无效";
                    break;
                }
                case "SERVICE_UNAVAILABLE": {
                    hangupCauseStr =  "服务不可用";
                    break;
                }
                case "BEARERCAPABILITY_NOTIMPL": {
                    hangupCauseStr =  "用户超越权限";
                    break;
                }
                case "CHAN_NOT_IMPLEMENTED": {
                    hangupCauseStr =  "通道未建立";
                    break;
                }
                case "FACILITY_NOT_IMPLEMENTED": {
                    hangupCauseStr =  "设备请求不支持";
                    break;
                }
                case "SERVICE_NOT_IMPLEMENTED": {
                    hangupCauseStr =  "服务请求不支持";
                    break;
                }
                case "INVALID_CALL_REFERENCE": {
                    hangupCauseStr =  "无效的呼叫";
                    break;
                }
                case "INCOMPATIBLE_DESTINATION": {
                    hangupCauseStr =  "落地不兼容";
                    break;
                }
                case "IE_NONEXIST": {
                    hangupCauseStr =  "信息参数不符";
                    break;
                }
                case "INVALID_IE_CONTENTS": {
                    hangupCauseStr =  "信息元错误";
                    break;
                }
                case "PROTOCOL_ERROR": {
                    hangupCauseStr =  "协议错误";
                    break;
                }
                case "INTERWORKING": {
                    hangupCauseStr =  "调用服务结束";
                    break;
                }
                case "ORIGINATOR_CANCEL": {
                    hangupCauseStr =  "主叫挂断";
                    break;
                }
                case "CRASH": {
                    hangupCauseStr =  "崩溃";
                    break;
                }
                case "SYSTEM_SHUTDOWN": {
                    hangupCauseStr =  "系统关闭";
                    break;
                }
                case "LOSE_RACE": {
                    hangupCauseStr =  "信道丢失";
                    break;
                }
                case "MANAGER_REQUEST": {
                    hangupCauseStr =  "API命令挂起";
                    break;
                }
                case "BLIND_TRANSFER": {
                    hangupCauseStr =  "盲转";
                    break;
                }
                case "ATTENDED_TRANSFER": {
                    hangupCauseStr =  "被叫前转";
                    break;
                }
                default : {
                    hangupCauseStr =  hangupCause;
                }
            }
        }
        return hangupCauseStr;
    }
}
