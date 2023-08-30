package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.DataBaseConnection;
import model.ProductModel;

public class Productdao {
	public static void insertuser(ProductModel pm) {
		try {
			Connection connection = DataBaseConnection.connect();
			String sql = "insert into producttbl (sid,pimage,pname,pcategory,pdesc,pprice) values (?,?,?,?,?,?)";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, pm.getSid());
			pStatement.setString(2, pm.getPimage());
			pStatement.setString(3, pm.getPname());
			pStatement.setString(4, pm.getPcategory());
			pStatement.setString(5, pm.getPdesc());
			pStatement.setInt(6, pm.getPprice());
			pStatement.executeUpdate();
			System.out.println("Product is Added..");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//all list of product on customer homepage
	public static List<ProductModel> allproducts(){
		List<ProductModel> list = new ArrayList<>();
		try {
			Connection connection = DataBaseConnection.connect();
			String sqlString = "select * from producttbl";
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				ProductModel pm = new ProductModel();
				pm.setPid(rSet.getInt("pid"));
				pm.setSid(rSet.getInt("sid"));
				pm.setPname(rSet.getString("pname"));
				pm.setPdesc(rSet.getString("pdesc"));
				pm.setPcategory(rSet.getString("pcategory"));
				pm.setPimage(rSet.getString("pimage"));
				pm.setPprice(rSet.getInt("pprice"));
				list.add(pm);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	//product by id 
	public static ProductModel getProductById(int pid) {
		ProductModel pm = null;
		try {
			Connection connection = DataBaseConnection.connect();
			String sql = "select * from producttbl where pid = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, pid);
			ResultSet rSet = pst.executeQuery();
			if(rSet.next()) {
				pm = new ProductModel();
				pm.setPid(rSet.getInt("pid"));
				pm.setPname(rSet.getString("pname"));
				pm.setPimage(rSet.getString("pimage"));
				pm.setPcategory(rSet.getString("pcategory"));
				pm.setPdesc(rSet.getString("pdesc"));
				pm.setPprice(rSet.getInt("pprice"));
				pm.setSid(rSet.getInt("sid"));
			}
 		} catch (Exception e) {
			// TODO: handle exception
		}
		return pm;
	}

}
