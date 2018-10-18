package cn.cucsi.bsd.ucc.common.untils;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.cucsi.bsd.ucc.data.domain.*;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class ChatLogin {
	private static Logger logger = Logger.getLogger(ChatLogin.class);

	public JSONObject login(HttpServletRequest req, ObjectMapper mapper,RedisTemplate<String, String> redisTemplate) {

		RedisSerializer stringSerializer = new StringRedisSerializer();
		redisTemplate.setValueSerializer(stringSerializer);

		UccUsers uccUser = Auth.getLoginUser(req.getSession());
		LoginUser loginUser = new LoginUser();

		String userAgent = req.getHeader("user-agent");
		
		if (userAgent.equals("")) {
			return null;
		}
		
		HashMap<String, Object> info = new HashMap<String, Object>();
		loginUser.setUserId(uccUser.getUserId());
		loginUser.setUserName(uccUser.getUserName());
		loginUser.setMobile(uccUser.getMobile());
		info.put("user", loginUser);
		if (uccUser.getExt() != null && uccUser.getExt().size()>0) {
			String extNum = "";
			for (UserExt pbxExts : uccUser.getExt()) {
				extNum = pbxExts.getExtNum();
				break;
			}
			info.put("ext", extNum);
		}
		boolean admin = false;
		if(uccUser.getUccPermissions()!=null&&uccUser.getUccPermissions().size()>0){
			for (UccPermissionsAndUser uccPermissionsAndUser : uccUser.getUccPermissions()){
				if("工作台".equals(uccPermissionsAndUser.getName())){
					if(uccPermissionsAndUser.getChildren()!=null&&uccPermissionsAndUser.getChildren().size()>0){
						for (UccPermissionsAndUserSecound uccPermissionsAndUserSecound : uccPermissionsAndUser.getChildren()){
							if("质检员".equals(uccPermissionsAndUserSecound.getName())){
								admin = true;
							}
						}
					}
				}
			}
		}
		info.put("admin", admin);
		info.put("domainId",uccUser.getDomainId());

		//String remortIp = LoginController.getRemortIP(req);
		String uid = UUID.randomUUID().toString();
		String identity = Md5.Get(userAgent+":"+uid);
		try {
			logger.info("ChatLogin_" + identity);
            redisTemplate.opsForValue().set("ChatLogin_" + identity, mapper.writeValueAsString(info), 2, TimeUnit.MINUTES);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("status", "0");
		map.put("uid", uid);
		String re = "";
		JSONObject json = null;
		try {
			re =  mapper.writeValueAsString(map);
			json =new JSONObject(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		return json;
	}
}
