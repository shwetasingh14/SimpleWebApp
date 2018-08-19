package org.pankaj.shwetawebapp.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.pankaj.shwetawebapp.User_Acct;
import org.pankaj.shwetawebapp.utils.DBUtils;
import org.pankaj.shwetawebapp.utils.MyUtils;

@WebFilter(filterName = "cookieFilter",urlPatterns = {"/*"})

public class CookieFilter implements Filter {

	public CookieFilter() {
		// TODO Auto-generated constructor stub
	}
public void init(FilterConfig fConfig)throws ServletException{
	
}
@Override
public void destroy() {
	
}
@Override
public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)
		throws IOException, ServletException {
	HttpServletRequest req=(HttpServletRequest)request;
	HttpSession session = req.getSession();
	
	User_Acct userlnSession = MyUtils.getLoginedUser(session);
	
	if(userlnSession!=null) {
		session.setAttribute("COOKIE_CHECKED","CHECKED");
		chain.doFilter(request, response);
		return;
		
	}
	Connection conn = MyUtils.getStoredConnection(request);
	
	String checked = (String)session.getAttribute("COOKIE_CHECKED");
	if(checked==null && conn!=null) {
		String userName = MyUtils.getUserNameInCookie(req);
		try {
			User_Acct user=DBUtils.findUser(conn, userName);
			MyUtils.storeLoginedUser(session, user);
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		session.setAttribute("COOKIE_CHECKED","CHECKED");
		
		}
		chain.doFilter(request, response);
		
	}

}
