package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.DataBaseConnection;
import model.CustomerModel;

public class Customerdao {

	public static void insertcustomer(CustomerModel cm) {
		try {
			Connection conn = DataBaseConnection.connect();
			String sql = "insert into customertbl (cid,name,email,contact,address,password) values (?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, cm.getId());
			pst.setString(2, cm.getName());
			pst.setString(3, cm.getEmail());
			pst.setLong(4, cm.getContact());
			pst.setString(5, cm.getAddress());
			pst.setString(6, cm.getPassword());
			pst.executeUpdate();
			System.out.println("Customer Registered..");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// to perform login
	public static CustomerModel logincustomer(CustomerModel cModel) {
		CustomerModel c = null;
		try {
			Connection conn = DataBaseConnection.connect();
			String sql = "select * from customertbl where email = ? and password = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, cModel.getEmail());
			pst.setString(2, cModel.getPassword());
			ResultSet rSet = pst.executeQuery();
			if (rSet.next()) {
				c = new CustomerModel();
				c.setId(rSet.getInt("cid"));
				c.setName(rSet.getString("name"));
				c.setEmail(rSet.getString("email"));
				c.setAddress(rSet.getString("address"));
				c.setContact(rSet.getLong("contact"));
				c.setPassword(rSet.getString("password"));
			}
			System.out.println("Login Succesfully..");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return c;
	}

	// to update user profile
	public static void updateprofile(CustomerModel cmodel) {
		try {
			Connection conn = DataBaseConnection.connect();
			String sql = "update customertbl set name = ? , contact = ?, email = ?,address=? where cid = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, cmodel.getName());
			pst.setLong(2, cmodel.getContact());
			pst.setString(3, cmodel.getEmail());
			pst.setString(4, cmodel.getAddress());
			pst.setInt(5, cmodel.getId());
			pst.executeUpdate();
			System.out.println("Profile id updated..");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//to chk old-password
		public static boolean chkoldpassword(String email , String np) {
			boolean flag = false;
			try {
				Connection conn = DataBaseConnection.connect();
				String sql = "select * from customertbl where email = ? and password = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, email);
				pst.setString(2, np);
				ResultSet resultSet = pst.executeQuery();
				if(resultSet.next()) {
					flag = true;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return flag;
		}
		
		//to update password
		public static void updatepassword(String email , String np) {
			try {
				Connection conn = DataBaseConnection.connect();
				String sql = "update customertbl set password = ? where email = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, np);
				pst.setString(2, email);
				pst.executeUpdate();
				System.out.println("Password is updated..");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		//to check email
		public static boolean checkemail(String email) {
			boolean flag = false;
			try {
				Connection conn = DataBaseConnection.connect();
				String sql = "select * from customertbl where email = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, email);
				ResultSet rSet = pst.executeQuery();
				if(rSet.next()) {
					flag =  true;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return flag;
		}
		
		//to update password on email
		public static void updateemailpassword(String email , String np) {
			try {
				Connection connection = DataBaseConnection.connect();
				String sql = "update customertbl set password = ? where email = ?";
				PreparedStatement pStatement = connection.prepareStatement(sql);
				pStatement.setString(1, np);
				pStatement.setString(2, email);
			    pStatement.executeUpdate();
			    System.out.println("Password is Updated..");
				
			} catch (Exception e) {
				// TODO: handle exception
				
				e.printStackTrace();
			}
			
		}
}
