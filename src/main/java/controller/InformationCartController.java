package controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import model.Cart;
import model.Category;
import model.Product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dao.ListProductDAO;

/**
 * Servlet implementation class InformationCartController
 */
public class InformationCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DataSource ds;
	
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
		Connection conn = null;
		HttpSession session = request.getSession();
		ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list"); 
		
		try {
			conn = ds.getConnection();			

			ListProductDAO dao = new ListProductDAO();
			
			List<Cart> cartProduct  = dao.getCartProducts(conn, cart_list);
			float total = dao.totalPrice(conn, cart_list);
			request.setAttribute("cart_list", cartProduct);
			request.setAttribute("total", total);
			request.getRequestDispatcher("cart.jsp").forward(request, response);
			
			try {
				conn.close();
			} catch (SQLException e) {
				throw new ServletException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		
	}

	

}
