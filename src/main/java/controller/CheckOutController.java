package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.Cart;
import model.Order;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dao.OrderDao;

/**
 * Servlet implementation class CheckOutController
 */
public class CheckOutController extends HttpServlet {
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
		try(PrintWriter out = response.getWriter()) {
			String message = "Order succeeded!";
			Connection conn = ds.getConnection();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			Account account = (Account) request.getSession().getAttribute("acc");
			
			if(cart_list != null && account != null) {
				
				for(Cart c:cart_list) {
					Order order = new Order();
					order.setBuyerMail(account.getMail());
					order.setId(c.getId());
					order.setNumber(c.getNumber());
					order.setDate(formatter.format(date));
					order.setAddress(account.getAddress());
					
					OrderDao dao = new OrderDao();
					boolean result = dao.insertOrder(conn, order);
					if(!result) {
						message = "Order failed!"; 
						break;
					}
				}
				
				cart_list.clear();
				
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("orders.jsp");
				rd.forward(request, response);
				
			} else if(account == null) {
				response.sendRedirect("login.jsp");
			} else {
				response.sendRedirect("cart.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
