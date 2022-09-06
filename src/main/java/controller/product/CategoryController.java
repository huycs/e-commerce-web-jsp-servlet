package controller.product;

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
 * Servlet implementation class CategoryController
 */
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String cateBrand = request.getParameter("cbrand");
		
		ListProductDAO dao = new ListProductDAO();
		List<Product> list = dao.getProductByCategory(conn, cateBrand);
		List<Category> listOfCategory = dao.getAllCategory(conn);
		Product lastProduct = dao.getLastProduct(conn);
		
		request.setAttribute("products", list);
		request.setAttribute("categories", listOfCategory);
		request.setAttribute("lastProduct", lastProduct);
		request.setAttribute("cfilter", cateBrand);
		request.getRequestDispatcher("home.jsp").forward(request, response);
		
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
