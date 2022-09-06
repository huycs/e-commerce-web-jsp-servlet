package controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dao.ListProductDAO;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
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
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		
		Connection conn = null;
		String usermail = request.getParameter("mail");
		String password = request.getParameter("pass");
		
		try {
			conn = ds.getConnection();
			ListProductDAO dao = new ListProductDAO();
			Account account = dao.login(conn, usermail, password);
			if(account == null) {

				request.setAttribute("error", "Username or password is incorrect");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("acc", account);
				response.sendRedirect("home");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
