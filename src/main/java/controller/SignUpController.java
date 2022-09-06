package controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

import java.io.IOException;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dao.ListProductDAO;

/**
 * Servlet implementation class SignUpController
 */
public class SignUpController extends HttpServlet {
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		String rePass = request.getParameter("repass");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		if(!pass.equals(rePass)) {
			request.setAttribute("error", "Password and Repeat Password need to be the same!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			try {
				conn = ds.getConnection();
				ListProductDAO dao = new ListProductDAO();
				Account account = dao.checkAccountExist(conn, mail);
				if(account == null) {
					dao.signUp(conn, mail, pass, name, address, phone);
					request.setAttribute("error", "Sign up succeeded!");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {
					request.setAttribute("error", "Email has already been taken!");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
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
