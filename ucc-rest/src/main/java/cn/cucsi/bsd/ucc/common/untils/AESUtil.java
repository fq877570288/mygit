package cn.cucsi.bsd.ucc.common.untils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class AESUtil {

	private static Logger logger = Logger.getLogger(AESUtil.class);
	private static final String[] DANGERS_CHAR = {"/","\\\\+","="};
	private static final String[] SAFE_CHAR = {"_a","_b","_c"};
	
	// 加密    
    public static String encrypt(String sSrc, String sKey) {    
    	try{
	        if (sKey == null) {    
	            System.out.print("Key为空null");    
	            return null;    
	        }    
	        // 判断Key是否为16位    
	        if (sKey.length() != 16) {    
	            System.out.print("Key长度不是16位");    
	            return null;    
	        }    
	        byte[] raw = sKey.getBytes();    
	        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");    
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"    
	        IvParameterSpec iv = new IvParameterSpec("0102030405070809".getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度    
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);    
	        byte[] encrypted = cipher.doFinal(sSrc.getBytes());    
	        
	        String dstr = Base64.encodeBase64String(encrypted);//此处使用BAES64做转码功能，同时能起到2次加密的作用。    
	        
	        for(int i=0;i<DANGERS_CHAR.length;i++){
	        	dstr = dstr.replaceAll("\\\\"+DANGERS_CHAR[i], SAFE_CHAR[i]);
	        }
	        
	        return dstr;
    	}catch(Throwable e){
    		e.printStackTrace();
			logger.error(e.getMessage(), e);
    	}
    	
    	return null;
    }    
    
    // 解密    
    public static String decrypt(String sSrc, String sKey) {    
        try {    
        	
            // 判断Key是否正确    
            if (sKey == null) {    
                System.out.print("Key为空null");    
                return null;    
            }    
            // 判断Key是否为16位    
            if (sKey.length() != 16) {    
                System.out.print("Key长度不是16位");    
                return null;    
            }    
            byte[] raw = sKey.getBytes("ASCII");    
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");    
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");    
            IvParameterSpec iv = new IvParameterSpec("0102030405070809"    
                    .getBytes());    
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);    
            

        	for(int i=0;i<SAFE_CHAR.length;i++){
        		sSrc = sSrc.replaceAll("\\\\"+SAFE_CHAR[i], DANGERS_CHAR[i]);
	        }
            
            byte[] encrypted1 = Base64.decodeBase64(sSrc);//先用bAES64解密    
            try {    
                byte[] original = cipher.doFinal(encrypted1);    
                String originalString = new String(original);    
                return originalString;    
            } catch (Exception e) {    
    			logger.error(e.getMessage(), e);
                return null;    
            }    
        } catch (Exception e) {    
			logger.error(e.getMessage(), e);
            return null;    
        }    
    }    

	public static void main(String agrs[]) {
		String content = "test";
		String password = "century_eggs-012";
		// 加密
		String encryptStr = encrypt(content, password);
		System.out.println("加密前：" + content);
		System.out.println("加密后：" + encryptStr);
		// 解密
		System.out.println("解密后：" + decrypt(encryptStr, password));
	}
}
