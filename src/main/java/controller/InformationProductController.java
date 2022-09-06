package controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dao.ListProductDAO;

/**
 * Servlet implementation class InformationProductController
 */
public class InformationProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DataSource ds;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	public void init(ServletConfig config) throws ServletException {
		try {
			InitialContext initialContext = new InitialContext();

			Context env = (Context) initialContext.lookup("java:comp/env");

			ds = (DataSource) env.lookup("jdbc/ShoppingDB");

		} catch (NamingException e) {
			throw new ServletException();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		String id = request.getParameter("pid");
		
		try {
			conn = ds.getConnection();

			ListProductDAO dao = new ListProductDAO();
			
			Product p = dao.getProductByID(conn, id);
			List<Category> listOfCategory = dao.getAllCategory(conn);
			Product lastProduct = dao.getLastProduct(conn);

			request.setAttribute("pinfor", p);
			request.setAttribute("categories", listOfCategory);
			request.setAttribute("lastProduct", lastProduct);
			request.getRequestDispatcher("inforProduct.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			throw new ServletException();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
