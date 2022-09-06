package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class AddToCartController
 */
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()) {
			ArrayList<Cart> cartList = new ArrayList<Cart>();
			
			int id = Integer.parseInt(request.getParameter("id"));
			Cart cm = new Cart();
			cm.setId(id);
			cm.setNumber(1);
			
			HttpSession session = request.getSession();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
			
			if(cart_list == null) {
				cartList.add(cm);
				session.setAttribute("cart-list", cartList);
				response.sendRedirect("home");
			} else {
				cartList = cart_list;				
				boolean exist = false;
				
				for(Cart c:cart_list) {
					//check if product existed in cart
					if(c.getId() == id) {
						exist = true;
						int number = c.getNumber();
						number++;
						c.setNumber(number);
						response.sendRedirect("home");
					}
				}
				
				if(exist == false) {
					cartList.add(cm);
					response.sendRedirect("home");
				}
			}			
			
		}
	}

}
