package cn.unihotel.entry.bean;

/**
 * @author zhang anbing
 * @date 2018/8/21
 */
public class GeneralResponse<T> {
    /**
     * 0表示正常，1表示异常
     */
    private String code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 返回给前端的数据
     */
    private T datas;

    public GeneralResponse(String code, String message, T datas) {
        this.code = code;
        this.message = message;
        this.datas = datas;
    }

    public static GeneralResponse success(String message) {
        return new GeneralResponse("0", message, null);
    }

    public static GeneralResponse fail(String message) {
        return new GeneralResponse("1", message, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }
}