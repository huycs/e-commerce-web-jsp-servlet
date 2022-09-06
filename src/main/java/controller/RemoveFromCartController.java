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
 * Servlet implementation class RemoveFromCartController
 */
public class RemoveFromCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()) {
			String id = request.getParameter("id");	
			
			
			if(id != null) {
				ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
				if(cart_list != null) {
					for(Cart c:cart_list) {
						//check if product existed in cart
						if(c.getId() == Integer.parseInt(id)) {
							cart_list.remove(c);
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
