package org.pankaj.shwetawebapp.servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.pankaj.shwetawebapp.User_Acct;
import org.pankaj.shwetawebapp.utils.MyUtils;

@WebServlet(urlPatterns= {"/userInfo"})

public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID=1L;
	
	public UserInfoServlet() {
		super();// TODO Auto-generated constructor stub
	}

	
@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
				HttpSession session = request.getSession();
				
				User_Acct loginedUser = MyUtils.getLoginedUser(session);
				
				if(loginedUser==null) {
					response.sendRedirect(request.getContextPath()+"/login");
					return;
					}
				request.setAttribute("user", loginedUser);
				
				
RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");

		dispatcher.forward(request, response);

			}
@Override
protected void doPost(HttpServletRequest request,HttpServletResponse response)
throws ServletException,IOException{
	doGet(request,response);
}
}
