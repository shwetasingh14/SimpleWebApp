package org.pankaj.shwetawebapp.utils;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.pankaj.shwetawebapp.User_Acct;

public class MyUtils {

	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
	public static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
	
	
	public static void StoreConnevtion(ServletRequest request,Connection conn) {
		request.setAttribute(ATT_NAME_CONNECTION, conn);
		
	}
	public static Connection getStoredConnection(ServletRequest request) {
		Connection conn =(Connection)request.getAttribute(ATT_NAME_CONNECTION);
		return conn;
				
	}
	public static void storeLoginedUser(HttpSession session,User_Acct loginedUSer) {
		session.setAttribute("loginedUser", loginedUSer);
	}
	public static User_Acct getLoginedUser(HttpSession session) {
		User_Acct loginedUser = (User_Acct)session.getAttribute("loginedUser");
		return loginedUser;
		
	}
	public static void storeUserCookie(HttpServletResponse response,User_Acct user) {
		System.out.println("Store user cookie");
		Cookie cookieUserName =new Cookie(ATT_NAME_USER_NAME,user.getUserName());
		cookieUserName.setMaxAge(24*60*60);
		response.addCookie(cookieUserName);
	}
	public static String getUserNameInCookie(HttpServletRequest request) {
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for(Cookie cookie :cookies) {
				if(ATT_NAME_USER_NAME.equals(cookie.getName())) {
					
				}
			}
		}
			return null;
			
	}
	public static void deleteUserCookie(HttpServletResponse response) {
		Cookie cookieUserName=new Cookie(ATT_NAME_USER_NAME,null);
		cookieUserName.setMaxAge(0);
		response.addCookie(cookieUserName);
		
	}
}
