package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Cart;
import model.Category;
import model.Product;

public class ListProductDAO {	
	 
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public List<Product> getAllProduct(Connection conn) {
		List<Product> list = new ArrayList<>();
		String query= "SELECT * FROM Products";
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Product(
						rs.getInt(1), 
						rs.getString(2),
						rs.getString(3),
						rs.getFloat(4), 
						rs.getString(5),
						rs.getString(6),
						rs.getString(7)));
			}
		} catch (Exception e) {
		}
		
		return list;
	}
	
	public List<Category> getAllCategory(Connection conn) {
		List<Category> list = new ArrayList<>();
		String query= "SELECT DISTINCT product_brand FROM Products";
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Category(rs.getString(1)));
			}
		} catch (Exception e) {
		}
		
		return list;
	}
	
	public List<Product> getProductByCategory(Connection conn, String cbrand) {
		List<Product> list = new ArrayList<>();
		String query= "SELECT * FROM Products WHERE product_brand = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1,cbrand);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Product(
						rs.getInt(1), 
						rs.getString(2),
						rs.getString(3),
						rs.getFloat(4), 
						rs.getString(5),
						rs.getString(6),
						rs.getString(7)));
			}
		} catch (Exception e) {
		}
		
		return list;
	}
	
	public Product getLastProduct(Connection conn) {
		
		String query= "SELECT TOP 1 * FROM Products ORDER BY product_id DESC";
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				return new Product(
						rs.getInt(1), 
						rs.getString(2),
						rs.getString(3),
						rs.getFloat(4), 
						rs.getString(5),
						rs.getString(6),
						rs.getString(7));
			}
			
		} catch (Exception e) {
		}
		
		return null;
	}
	
	public Product getProductByID(Connection conn, String id) {
		String query= "SELECT * FROM Products WHERE product_id = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				return new Product(
						rs.getInt(1), 
						rs.getString(2),
						rs.getString(3),
						rs.getFloat(4), 
						rs.getString(5),
						rs.getString(6),
						rs.getString(7));
			}
		} catch (Exception e) {
		}
		
		return null;
	}
	
	public List<Product> searchByName(Connection conn, String keyWord) {
		List<Product> list = new ArrayList<>();
		String query= "SELECT * FROM Products WHERE product_name LIKE ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + keyWord + "%");
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Product(
						rs.getInt(1), 
						rs.getString(2),
						rs.getString(3),
						rs.getFloat(4), 
						rs.getString(5),
						rs.getString(6),
						rs.getString(7)));
			}
		} catch (Exception e) {
		}
		
		return list;
	}
	
	public Account login(Connection conn, String mail, String password) {
		String query = "SELECT * FROM Account WHERE user_mail = ? AND password = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, mail);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()) {
				return new Account(
						rs.getString(1), 
						rs.getString(2),
						rs.getInt(3),
						rs.getString(4), 
						rs.getString(5),
						rs.getString(6));
			}
		} catch (Exception e) {
		}
		return null;
	}
	
	public Account checkAccountExist(Connection conn, String mail) {
		String query = "SELECT * FROM Account WHERE user_mail = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, mail);
			rs = ps.executeQuery();
			while(rs.next()) {
				return new Account(
						rs.getString(1), 
						rs.getString(2),
						rs.getInt(3),
						rs.getString(4), 
						rs.getString(5),
						rs.getString(6));
			}
		} catch (Exception e) {
		}
		return null;
	}
	
	public void signUp(Connection conn, String mail, String password, String name, String address, String phone) {
		String query = "INSERT INTO Account(user_mail, password, account_role, user_name, user_address, user_phone) VALUES(?, ?, 0, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, mail);
			ps.setString(2, password);
			ps.setString(3, name);
			ps.setString(4, address);
			ps.setString(5, phone);
			ps.executeUpdate();	
			ps.close();
		} catch (Exception e) {
		}
	}
	
	public List<Cart> getCartProducts(Connection conn, ArrayList<Cart> cartList) {
		List<Cart> list = new ArrayList<>();
		String query= "SELECT * FROM Products WHERE product_id = ?";
		try {
			//check if cart is null
			if(cartList.size() > 0) {
				for(Cart item:cartList) {
					ps = conn.prepareStatement(query);
					ps.setInt(1, item.getId());
					rs = ps.executeQuery();
					while(rs.next()) {
						Cart row = new Cart();
						row.setId(rs.getInt(1));
						row.setName(rs.getString(2));
						row.setDescription(rs.getString(3));		 
						row.setPrice(rs.getFloat(4));
						row.setBrand(rs.getString(7));
						row.setNumber(item.getNumber());
						list.add(row);
					}
				}
			}	
		} catch (Exception e) {
		}
		
		return list;
	}
	
	public float totalPrice(Connection conn, ArrayList<Cart> cartList) {
		float sum = 0;
		
		
		try {
			if(cartList.size() > 0) {
				for(Cart item:cartList) {
					String query= "SELECT product_price FROM Products WHERE product_id = ?";
					ps = conn.prepareStatement(query);
					ps.setInt(1, item.getId());
					rs = ps.executeQuery();
					
					while(rs.next()) {
						sum += rs.getFloat(1)*item.getNumber();
					}
				}
							
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sum;
	}
}
