package cn.cucsi.bsd.ucc.common.untils;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.cucsi.bsd.ucc.data.domain.PbxExts;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class ChatLogin {
	private static Logger logger = Logger.getLogger(ChatLogin.class);
	private RedisTemplate<String, String> template;
	
	public String login(HttpServletRequest req, ObjectMapper mapper) {
		
		UccUsers loginUser = Auth.getLoginUser(req.getSession());
		
		String userAgent = req.getHeader("user-agent");
		
		if (userAgent.equals("")) {
			return null;
		}
		
		HashMap<String, Object> info = new HashMap<String, Object>();
		info.put("user", loginUser);
		if (loginUser.getExt() != null && loginUser.getExt().size()>0) {
			String extNum = "";
			for (PbxExts pbxExts : loginUser.getExt()) {
				extNum = pbxExts.getExtNum();
				break;
			}
			info.put("ext", extNum);
		}
		if (Auth.UserFlagCanDo(req.getSession(), 870)) {
			info.put("admin", true);
		}
		
		//String remortIp = LoginController.getRemortIP(req);
		String uid = UUID.randomUUID().toString();
		String identity = Md5.Get(userAgent+":"+uid);
		try {
			template.opsForValue().set("ChatLogin_" + identity, mapper.writeValueAsString(info), 2, TimeUnit.MINUTES);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("status", "0");
		map.put("uid", uid);
		String re = "";
		try {
			re =  mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		return re;
	}
	
}
