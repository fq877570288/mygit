package cn.cucsi.bsd.ucc.common.untils;

import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

public class Auth {
	
	private Auth(){};
	
	/**
	 * 获取登录的用户
	 */
	public static synchronized final UccUsers getLoginUser(HttpSession session) throws DotQinNoLoginException{
		
		if (session.getAttribute("LoginUser") != null) {
			UccUsers loginUser = (UccUsers) session.getAttribute("LoginUser");
			if (loginUser != null) {
				return loginUser;
			}
		}
		throw new DotQinNoLoginException("DotQinException -> NoLogin!");
	}
	
	/**
	 * 用户权限检查
	 */
	public static synchronized final boolean UserFlagCanDo(HttpSession session, int flag) {
//		if (session.getAttribute("isOrgAdmin") != null) {
//			boolean orgAdmin = (boolean) session.getAttribute("isOrgAdmin");
//			if (orgAdmin) {
//				return true;
//			}
//		}
//		return true;
		if (session.getAttribute("LoginUserFlags") != null) {
			@SuppressWarnings("unchecked")
			ArrayList<Integer> UserFlags = (ArrayList<Integer>) session.getAttribute("LoginUserFlags");
			return UserFlags.contains(flag);
		}
		return false;
	}
	
	/**
	 * 用户权限检查 包含一组
	 */
	public static synchronized final boolean UserFlagHasAll(HttpSession session, int... flags) {
		if (session.getAttribute("isOrgAdmin") != null) {
			boolean orgAdmin = (boolean) session.getAttribute("isOrgAdmin");
			if (orgAdmin) {
				return true;
			}
		}
		if (session.getAttribute("LoginUserFlags") != null && flags.length > 0) {
			@SuppressWarnings("unchecked")
			ArrayList<Integer> UserFlags = (ArrayList<Integer>) session.getAttribute("LoginUserFlags");
			for (int i=0; i!=flags.length; i++) {
				if (!UserFlags.contains(flags[i])) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 用户权限检查 包含一组中的一个
	 */
	public static synchronized final boolean UserFlagHasOne(HttpSession session, int... flags) {
		if (session.getAttribute("isOrgAdmin") != null) {
			boolean orgAdmin = (boolean) session.getAttribute("isOrgAdmin");
			if (orgAdmin) {
				return true;
			}
		}
		if (session.getAttribute("LoginUserFlags") != null && flags.length > 0) {
			@SuppressWarnings("unchecked")
			ArrayList<Integer> UserFlags = (ArrayList<Integer>) session.getAttribute("LoginUserFlags");
			for (int i=0; i!=flags.length; i++) {
				if (UserFlags.contains(flags[i])) {
					return true;
				}
			}
		}
		return false;
	}
}
