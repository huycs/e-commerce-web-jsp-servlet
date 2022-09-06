package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Order;

public class OrderDao {	
	private String query;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public OrderDao() {
	}
	
	public boolean insertOrder(Connection conn, Order order) {
		
		boolean result = false;
		
		try {
			
			query = "INSERT INTO Orders(user_mail, order_status, order_date, order_discount_code, order_address)\r\n"
					+ "VALUES(?, 0, ?, ?, ?)";			
			ps = conn.prepareStatement(query);
			ps = conn.prepareStatement(query);
			ps.setString(1, order.getBuyerMail());
			ps.setString(2, order.getDate());
			ps.setString(3, "");
			ps.setString(4, order.getAddress());
			ps.executeUpdate();
			result = true;
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
