package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.DataBaseConnection;
import model.Sellermanmodel;

public class Sellermandao {
	public static void insertdata(Sellermanmodel sm) {
		try {
			Connection connection = DataBaseConnection.connect();
			String sql = "insert into sellermantbl (name,email,contact,address,password) values (?,?,?,?,?)";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, sm.getName());
			pStatement.setString(2, sm.getEmail());
			pStatement.setLong(3, sm.getContact());
			pStatement.setString(4, sm.getAddress());
			pStatement.setString(5, sm.getPassword());
			pStatement.executeUpdate();
			System.out.println("SellerMan inserted..");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// to login
	public static Sellermanmodel login(Sellermanmodel smodel) {
		Sellermanmodel sm = null;
		try {
			Connection connection = DataBaseConnection.connect();
			String sql = "select * from sellermantbl where email = ? and password = ?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, smodel.getEmail());
			pStatement.setString(2, smodel.getPassword());
			ResultSet rSet = pStatement.executeQuery();
			if (rSet.next()) {
				sm = new Sellermanmodel();
				sm.setSellerid(rSet.getInt("sid"));
				sm.setName(rSet.getString("name"));
				sm.setContact(rSet.getLong("contact"));
				sm.setAddress(rSet.getString("address"));
				sm.setPassword(rSet.getString("password"));
				sm.setEmail(rSet.getString("email"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sm;
	}

	public static void updateprofile(Sellermanmodel m) {
		
		try {
			Connection connection = DataBaseConnection.connect();
			String sql = "update sellermantbl set name =? ,email=? , contact=? , address=? where sid = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, m.getName());
			pst.setString(2, m.getEmail());
			pst.setLong(3, m.getContact());
			pst.setString(4, m.getAddress());
			pst.setInt(5, m.getSellerid());
			pst.executeUpdate();
			System.out.println("Data updated");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//to check old password exists
	public static boolean chkoldpassword(String email, String np) {
		boolean flag = false;
		try {
			Connection connection = DataBaseConnection.connect();
			String sql = "select * from sellermantbl where email = ? and password = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, np);
			ResultSet rSet = pst.executeQuery();
			if (rSet.next()) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}
	
	//to update password 
	public static void updatepassword(String email, String np) {
		try {
			Connection conn = DataBaseConnection.connect();
			String sql = "update sellermantbl set password = ? where email = ?";
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

}
