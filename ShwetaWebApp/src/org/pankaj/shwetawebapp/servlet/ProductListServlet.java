package org.pankaj.shwetawebapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pankaj.shwetawebapp.Product;
import org.pankaj.shwetawebapp.utils.DBUtils;
import org.pankaj.shwetawebapp.utils.MyUtils;

@WebServlet(urlPatterns= {"/productList"})

public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	public ProductListServlet() {
		super();// TODO Auto-generated constructor stub
	}
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		Connection conn = MyUtils.getStoredConnection(request);
		
		String errorString = null;
		List<Product>list  = null;
		try {
			list = DBUtils.queryProduct(conn);
		}catch(SQLException e) {
			e.printStackTrace();
			errorString=e.getMessage();
			}
request.setAttribute("errorString", errorString);
request.setAttribute("productList", list);

RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/productListView.jsp");

dispatcher.forward(request, response);

	}
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		doGet(request,response);
		
	}
	
}
