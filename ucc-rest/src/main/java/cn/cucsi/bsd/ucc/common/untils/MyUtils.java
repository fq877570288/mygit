package cn.cucsi.bsd.ucc.common.untils;

import org.apache.poi.ss.formula.functions.T;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 工具类--系统公用方法
 * add by wangxiaoyu
 * 2018-08-29
 */
public class MyUtils extends org.apache.commons.lang.StringUtils{

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    private static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 判断字符串是否为null或空串 DOCUMENT ME!
     * add by wangxiaoyu
     * 2018-08-29
     */
    public static boolean isBlank(final String str) {
        if (str == null) {
            return true;
        }
        if (str.trim().length() <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串数祖是否为null或空串 DOCUMENT ME!
     * add by wangxiaoyu
     * 2018-08-29
     */
    public static boolean isBlank(final String[] str) {
        return (str == null) || (str.length <= 0);
    }

    /**
     * 判断对象数组是否为空或null
     * add by wangxiaoyu
     * 2018-08-29
     */
    public static boolean isBlank(final Object[] objs) {
        return (objs == null) || (objs.length <= 0);
    }

    /**
     * 判断Collection对象是否为空或没有值
     * add by wangxiaoyu
     * 2018-08-29
     */
    @SuppressWarnings("unchecked")
    public static boolean isBlank(final Collection obj) {
        return (obj == null) || (obj.size() <= 0);
    }

    /**
     * 判断ｓｅｔ对象是否为空或没有值
     * add by wangxiaoyu
     * 2018-08-29
     */
    @SuppressWarnings("unchecked")
    public static boolean isBlank(final Set obj) {

        return (obj == null) || (obj.size() <= 0);
    }

    /**
     * 判断持久化对象是否为空
     * add by wangxiaoyu
     * 2018-08-29
     */
    public static boolean isBlank(final Serializable obj) {

        return obj == null;
    }

    /**
     * 判断Map对象是否为空
     * add by wangxiaoyu
     * 2018-08-29
     */
    @SuppressWarnings("unchecked")
    public static boolean isBlank(final Map obj) {

        return (obj == null) || (obj.size() <= 0);
    }

    /**
     * 判断对象是否为空
     * add by wangxiaoyu
     * 2018-08-29
     */
    @SuppressWarnings("unchecked")
    public static boolean isBlank(Object obj) {
        if (obj instanceof java.util.List) {
            List<Object> list = (List<Object>) obj;
            return list.isEmpty();
        } else if (obj instanceof java.util.Map) {
            return isBlank((Map) obj);
        } else {
            return obj == null;
        }
    }

    /**
     * Java中判断字符串是否全为数字的方法
     * add by wangxiaoyu
     * 2018-08-29
     */
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * 时间格式化
     */
    public static String formatDate(Date date) {

        if (date == null) {
            return "";
        }
        SimpleDateFormat f = new SimpleDateFormat(YMDHMS);
        return f.format(date);
    }

    /**
     * 获取当前日期 yyyy-MM-dd HH:mm:ss
     * add by wangxiaoyu
     * 2018-08-29
     */
    public static String getToday() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime = formatter.format(cal.getTime());
        return mDateTime;
    }

    /**
     * 获取当前日期 yyyyMMddHHmmss
     * add by wangxiaoyu
     * 2018-08-29
     */
    public static String getCurrent() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String mDateTime = formatter.format(cal.getTime());
        return mDateTime;
    }

    /**
     * 将yyyyMMddHHmmss格式的字符串转化为yyyy-MM-dd HH:mm:ss格式的时间字符串
     * add by wangxiaoyu
     * 2018-08-29
     */
    public static String numberStrToDateStr2(String numberStr){
        String dateStr = "";
        if(numberStr==null || "".equals(numberStr.trim())){
            dateStr = "";
        }else{
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = sdf.parse(numberStr);
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dateStr = sdf2.format(date);
            } catch (Exception e) {
                dateStr = "";
            }
        }
        return dateStr;
    }

    /**
     * 将yyyyMMdd格式的字符串转化为yyyy-MM-dd格式的时间字符串
     * add by wangxiaoyu
     * 2018-08-29
     */
    public static String numStrToDateStr(String numberStr){
        String dateStr = "";
        if(numberStr==null || "".equals(numberStr.trim())){
            dateStr = "";
        }else{
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Date date = sdf.parse(numberStr);
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = sdf2.format(date);
            } catch (Exception e) {
                dateStr = "";
            }
        }
        return dateStr;
    }

    /**
     * 获取两个字符串格式的时间之间相差的天数
     * add by wangxiaoyu
     * 2018-08-29
     */
    public static Long getDaysByMinusOther(String date1, String date2) throws ParseException {
        if(null!=date1&&!"".equals(date1)&&null!=date2&&!"".equals(date2)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date a = sdf.parse(date1);
            Date b = sdf.parse(date2);
            Long h =Math.abs(b.getTime()-a.getTime());
            Long days = h/(1000*60*60*24);
            return days;
        }else{
            return null;
        }
    }

    /**
     * 将秒数转为*天*小时*分*秒返回
     * add by wangxiaoyu
     * 2018-09-14
     */
    public static String formatDateTime(long mss) {
        String DateTimes = null;
        long days = mss / ( 60 * 60 * 24);
        long hours = (mss % ( 60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % ( 60 * 60)) /60;
        long seconds = mss % 60;
        if(days>0){
            DateTimes= days + "天" + hours + "小时" + minutes + "分钟"
                    + seconds + "秒";
        }else if(hours>0){
            DateTimes=hours + "小时" + minutes + "分钟"
                    + seconds + "秒";
        }else if(minutes>0){
            DateTimes=minutes + "分钟"
                    + seconds + "秒";
        }else{
            DateTimes=seconds + "秒";
        }

        return DateTimes;
    }

    /**
     * 将一个list均分成n个list,主要通过偏移量来实现的
     * add by wangxiaoyu
     * 2018-09-26
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<List<T>>();
        int remaider = source.size() % n;  //(先计算出余数)
        int number = source.size() / n;  //然后是商
        int offset = 0;//偏移量
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remaider > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }
    // zss  优化源码重复查数据库问题
    public String generateSQL(String[] times,String domain,String depts,String flag){
        String sql = "";
        String generateSQL = "";
        switch (flag){
            case "CompleteTask":
                sql = "select count(*)\n" +
                        "    from OC_TASK_RECORD d\n" +
                        "    inner join\n" +
                        "    OC_TASK_TRANSFER_RECORD r ON d.TASK_RECORD_ID = r.TASK_RECORD_ID and r.TRANSFER_STATUS = '4'\n" +
                        "    where\n" +
                        "    \n" +
                        "\t\t \tr.ROPERATE_DEPT_ID in ({1})\n" +
                        "\t\t \tand TRANSFER_TIME between {2} and {3}\n" +
                        "\t\t  and d.domain_id = {4}";
                break;
            case  "ECall":
                sql = " select count(*)\n" +
                        "    from OC_TASK_RECORD d\n" +
                        "    inner join\n" +
                        "    OC_TASK_TRANSFER_RECORD r ON d.TASK_RECORD_ID = r.TASK_RECORD_ID and r.TRANSFER_STATUS = '4'\n" +
                        "    inner join pbx_cdrs c on c.cdr_id = r.CDR_ID\n" +
                        "    where\n" +
                        "    \n" +
                        "\t\t \tr.ROPERATE_DEPT_ID in ({1})\n" +
                        "\t\t \tand c.call_time>=5 and\n" +
                        "         \tTRANSFER_TIME between {2} and {3}\n" +
                        "          and d.domain_id = {4}";
                break;
            case  "ACall":
                sql = " select count(*)\n" +
                        "    from OC_TASK_RECORD d\n" +
                        "    inner join\n" +
                        "    OC_TASK_TRANSFER_RECORD r ON d.TASK_RECORD_ID = r.TASK_RECORD_ID and r.TRANSFER_STATUS = '4'\n" +
                        "    inner join pbx_cdrs c on c.cdr_id = r.CDR_ID\n" +
                        "    where\n" +
                        "    \n" +
                        "\t\t \tr.ROPERATE_DEPT_ID in ({1})\n" +
                        "\t\t \tand TRANSFER_TIME between {2} and {3}\n" +
                        "          and d.domain_id = {4}";
                break;
        }
        for(int i=0;i<times.length;i++){
            if(i==0){//第一次
                generateSQL += sql.replaceAll("\\{1\\}",depts).replaceAll("\\{2\\}","'"+times[i]+" 00:00:00'").replaceAll("\\{3\\}","'"+times[i]+" 23:59:59'").replaceAll("\\{4\\}","'"+domain+"'");
            }
            if(i!=0){//不是第一次
                generateSQL +=  "UNION ALL("+sql.replaceAll("\\{1\\}",depts).replaceAll("\\{2\\}","'"+times[i]+" 00:00:00'").replaceAll("\\{3\\}","'"+times[i]+" 23:59:59'").replaceAll("\\{4\\}","'"+domain+"'")+")";
            }
        }
        return generateSQL;
    }
// 测试 js zss
   public static void main(String[] args) {
       ScriptEngineManager manager = new ScriptEngineManager();
       ScriptEngine engine = manager.getEngineByName( "JavaScript" );
       System.out.println( engine.getClass().getName() );
       try{
           System.out.println( "Result:" + engine.eval( "" +
                   "function f() { \"data\": [\n" +
                   "        {\n" +
                   "            \"subDepts\": null,\n" +
                   "            \"deptAdminName\": \"admin2\",\n" +
                   "            \"deptId\": \"4028e381665b7a8b01665bc851710001\",\n" +
                   "            \"createdBy\": null,\n" +
                   "            \"deptName\": \"二级02\",\n" +
                   "            \"deptPid\": \"0\",\n" +
                   "            \"deptLevel\": null,\n" +
                   "            \"deptCreateTime\": \"2018-10-10 10:22:26\",\n" +
                   "            \"deptDesc\": \"cccc\",\n" +
                   "            \"domainId\": \"2c9289d164f985b10164f998369a0001\",\n" +
                   "            \"deptAdmin\": \"ff808081665b88b601665bb83be90004\",\n" +
                   "            \"createdUserId\": null,\n" +
                   "            \"createdUserName\": null,\n" +
                   "            \"createdNickName\": null,\n" +
                   "            \"updatedUserId\": null,\n" +
                   "            \"updatedUserName\": null,\n" +
                   "            \"updatedNickName\": null,\n" +
                   "            \"createdTime\": null,\n" +
                   "            \"updatedTime\": null,\n" +
                   "            \"userDepts\": [\n" +
                   "                {\n" +
                   "                    \"userId\": \"ff8080816661212b016661f802e40008\",\n" +
                   "                    \"deptId\": \"4028e381665b7a8b01665bc851710001\"\n" +
                   "                },\n" +
                   "                {\n" +
                   "                    \"userId\": \"ff80808166659d890166677328960026\",\n" +
                   "                    \"deptId\": \"4028e381665b7a8b01665bc851710001\"\n" +
                   "                },\n" +
                   "                {\n" +
                   "                    \"userId\": \"ff80808166680b6c0166681f60170000\",\n" +
                   "                    \"deptId\": \"4028e381665b7a8b01665bc851710001\"\n" +
                   "                }\n" +
                   "            ],\n" +
                   "            \"depts\": [\n" +
                   "                {\n" +
                   "                    \"subDepts\": null,\n" +
                   "                    \"deptAdminName\": \"admin\",\n" +
                   "                    \"deptId\": \"4028e381665b7a8b01665bc89d880002\",\n" +
                   "                    \"createdBy\": null,\n" +
                   "                    \"deptName\": \"嘻嘻\",\n" +
                   "                    \"deptPid\": \"4028e381665b7a8b01665bc851710001\",\n" +
                   "                    \"deptLevel\": null,\n" +
                   "                    \"deptCreateTime\": \"2018-10-10 10:22:46\",\n" +
                   "                    \"deptDesc\": \"踩踩踩\",\n" +
                   "                    \"domainId\": \"2c9289d164f985b10164f998369a0001\",\n" +
                   "                    \"deptAdmin\": \"1\",\n" +
                   "                    \"createdUserId\": null,\n" +
                   "                    \"createdUserName\": null,\n" +
                   "                    \"createdNickName\": null,\n" +
                   "                    \"updatedUserId\": null,\n" +
                   "                    \"updatedUserName\": null,\n" +
                   "                    \"updatedNickName\": null,\n" +
                   "                    \"createdTime\": null,\n" +
                   "                    \"updatedTime\": null,\n" +
                   "                    \"userDepts\": [],\n" +
                   "                    \"depts\": null\n" +
                   "                }\n" +
                   "            ]\n" +
                   "        }]" +
                   "" +
                   "" +
                   "};"
           ) );
       }catch (Exception e){

       }


   }
}
