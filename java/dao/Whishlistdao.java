package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.DataBaseConnection;
import model.whishlistmodel;

public class Whishlistdao {
	
	public static void insertdata(whishlistmodel wm) {
		try {
			Connection conn = DataBaseConnection.connect();
			String sql = "insert into whishlisttbl (pid,custid) values (?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, wm.getPid());
			pst.setInt(2, wm.getCusid());
			pst.executeUpdate();
			System.out.println("added");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static List<whishlistmodel> getdatabycusid(int cusid){
		List<whishlistmodel> list = new ArrayList<whishlistmodel>();
		try {
			Connection conn=DataBaseConnection.connect();
			String sql = "select * from whishlisttbl where custid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, cusid);
			ResultSet rSet = pst.executeQuery();
			while(rSet.next()) {
				whishlistmodel wm = new whishlistmodel();
				wm.setCusid(rSet.getInt("custid"));
				wm.setPid(rSet.getInt("pid"));
				wm.setWid(rSet.getInt("wid"));
				list.add(wm);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	//delete from whishlist
	public static void delfromwhishlist(int wid) {
		try {
			Connection con = DataBaseConnection.connect();
			String sql = "delete from whishlisttbl where wid = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, wid);
			pst.executeUpdate();
			System.out.println("Deleted..");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//fetch by id
	public static whishlistmodel getdatabyid() {
		whishlistmodel wm = new whishlistmodel();
		try {
			Connection conn= DataBaseConnection.connect();
			String sqlString ="select * from whishlisttbl where wid = ?";
			PreparedStatement pStatement = conn.prepareStatement(sqlString);
			pStatement.setInt(1, wm.getWid());
			ResultSet rSet = pStatement.executeQuery();
			if(rSet.next()) {
				wm.setCusid(rSet.getInt("custid"));
				wm.setPid(rSet.getInt("pid"));
				wm.setWid(rSet.getInt("wid"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return wm;
	}
	
	//chk product by id
	public static boolean getdata(int wid,int custid) {
		boolean flag = true;
		try {
			Connection con = DataBaseConnection.connect();
			String sql = "select * from whishlisttbl where wid = ? and custid = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, wid);
			pst.setInt(2, custid);
			ResultSet rset = pst.executeQuery();
			if(rset.next()) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

}
