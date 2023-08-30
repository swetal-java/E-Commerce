package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.DataBaseConnection;
import model.CartModel;

public class Cartdao {
	
	public static void insertincart(CartModel cd) {
		try {
			Connection connection = DataBaseConnection.connect();
			String sql = "insert into carttbl (pid,customerid,price,quantity,name,total,paymentstatus) values (?,?,?,?,?,?,?)";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, cd.getPid());
			pStatement.setInt(2, cd.getCustid());
			pStatement.setInt(3, cd.getPrice());
			pStatement.setInt(4, cd.getQty());
			pStatement.setString(5, cd.getPname());
			pStatement.setInt(6, cd.getTotal());
			pStatement.setString(7, cd.getPaymentstatus());
			pStatement.executeUpdate();
			System.out.println("product is inserted in cart..");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//getproduct in cart by customerid
	public static List<CartModel> getProductByCid(int cid) {
		List<CartModel> list = new ArrayList<>();
		try {
			Connection connection = DataBaseConnection.connect();
			String sql = "select * from carttbl where customerid = ?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, cid);
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				CartModel cmodel  = new CartModel();
				cmodel.setCid(rSet.getInt("cid"));
				cmodel.setCustid(rSet.getInt("customerid"));
				cmodel.setPaymentstatus(rSet.getString("paymentstatus"));
				cmodel.setPid(rSet.getInt("pid"));
				cmodel.setPname(rSet.getString("name"));
				cmodel.setPrice(rSet.getInt("price"));
				cmodel.setQty(rSet.getInt("quantity"));
				cmodel.setTotal(rSet.getInt("total"));
				list.add(cmodel);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	//fetch cart id
	public static CartModel getcartid() {
		CartModel cModel = new CartModel();
		try {
			Connection conn = DataBaseConnection.connect();
			String sql = "select * from carttbl where cid = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, cModel.getCid());
			ResultSet rSet = pst.executeQuery();
			if(rSet.next()) {
				cModel.setCid(rSet.getInt("cid"));
				cModel.setCustid(rSet.getInt("customerid"));
				cModel.setPid(rSet.getInt("pid"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cModel;
	}
	
	//chk product exists in cart
	public static boolean chkcart(int cusid,int cartid) {
		boolean flag = false;
		try {
			Connection conn = DataBaseConnection.connect();
			String sql = "select * from carttbl where customerid=? and cid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, cusid);
			pst.setInt(2, cartid);
			ResultSet rSet = pst.executeQuery();
			if(rSet.next()) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}
	
	//delete cart product
	public static void deletefromcart(int cartid) {
		try {
		Connection con = DataBaseConnection.connect();
		String sql = "delete from carttbl where cid = ?";
		PreparedStatement pst= con.prepareStatement(sql);
		pst.setInt(1, cartid);
		pst.executeUpdate();
		System.out.println("Product delted from cart");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	

}
