package cn.cucsi.bsd.ucc.common.untils;

import org.apache.commons.lang.StringUtils;

public class ServiceHelper {
	
	public static String desensitizeFixedPhone(String num) {  
        if (StringUtils.isBlank(num)) return null;
        num = num.trim();
        return StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*");  
    }  
	
	//脱敏移动电话
	public static String desensitizeMobilePhone(String num) {  
        if (StringUtils.isBlank(num)) return null;
        num = num.trim();
        if(num.length()==11){
        	return StringUtils.left(num, 3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*"), "***"));  
        }else{
        	return desensitizeFixedPhone(num);
        }
    }  
	
	//脱敏姓名
	public static String desensitizeRealName(String fullName) {  
        if (StringUtils.isBlank(fullName)) return null;
        fullName = fullName.trim();
        String name = "";
        if(StringUtils.length(fullName)<=3){
        	name = StringUtils.left(fullName, 1);
        }else{
        	name = StringUtils.left(fullName, 2);
        }
        
        return StringUtils.rightPad(name, StringUtils.length(fullName), "*");  
    }  
	
	//脱敏身份证 自定义字段1
	public static String desensitizeIdCard(String id) {  
        if (StringUtils.isBlank(id)) return null;

        id = id.trim();
        
        String num = StringUtils.right(id, 4);  
        return StringUtils.leftPad(num, StringUtils.length(id), "*");  
    }  
	
	/*public static void main(String[] args){
		System.out.println(desensitizeFixedPhone("8888"));
		System.out.println(desensitizeMobilePhone("188888"));
		System.out.println(desensitizeRealName("大熊"));
		System.out.println(desensitizeIdCard("230121119813214"));
	}*/
}
