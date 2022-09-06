package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class NumberIncDecController
 */
public class NumberIncDecController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html; charset=UTF-8");
		try(PrintWriter out = response.getWriter()) {
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			
			if(action != null) {
				if(action.equals("inc")) {
					for(Cart c:cart_list) {
						if(c.getId() == id) {
							int number = c.getNumber();
							number++;
							c.setNumber(number);
							response.sendRedirect("inforcart");
						}
					}
				}
				
				if(action.equals("dec")) {
					for(Cart c:cart_list) {
						if(c.getId() == id && c.getNumber() > 1) {
							int number = c.getNumber();
							number--;
							c.setNumber(number);
							break;
						}
					}
					response.sendRedirect("inforcart");
				}
			} else {
				response.sendRedirect("inforcart");
			}
		}
	}

}
