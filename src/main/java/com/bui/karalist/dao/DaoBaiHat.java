package com.bui.karalist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bui.karalist.model.BaiHat;



public class DaoBaiHat {
	
	public static Connection connect() {
		Connection conn = null;

		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager
					.getConnection("jdbc:mysql://localhost/karalist?"
							+ "user=root&password=&useUnicode=true&characterEncoding=UTF-8");
		} catch (Exception ex) {
			System.out.println("Failed to connect Database!");

		}
		return conn;
	}
	

	public static String addSlashes(String s) {
	    s = s.replaceAll("\\\\", "\\\\\\\\");
	    s = s.replaceAll("\\n", "\\\\n");
	    s = s.replaceAll("\\r", "\\\\r");
	    s = s.replaceAll("\\00", "\\\\0");
	    s = s.replaceAll("'", "\\\\'");
	    return s;
	}
	
// ======================================= Get BaiHat =================================================
	
	public static BaiHat findBaiHatById(int id, List<Integer> ls){
		
		Connection cn = connect();
		
		Statement stmt = null;
		ResultSet rs = null;

    	BaiHat bh = null;
    	

		try {
		    stmt = cn.createStatement();
		    String sql = ("SELECT * from bai_hat where Id=" + id);
		    if(!ls.isEmpty()){
			    if(ls.contains(0)){
			    	sql += "";	
			    }else{		    	
			    	int count = 0;
			    	sql += (" and vol in (");
			    	for (Integer in : ls) {
			    		if(count != 0){
			    			sql += ( ", " + in);
			    		}else{
			    			sql += (in);
			    		}
			    		count++;
					}
			    	sql += (")");
			    }
		    }else{
		    	sql = ("SELECT * FROM bai_hat WHERE 0");
		    }
//		    System.out.println("sql ID ne: " + sql);
		    rs = stmt.executeQuery(sql);
		    
		    if(rs.next()){		    
		    	int idbh = rs.getInt("id");
		    	String namebh = rs.getString("name");
		    	String author = rs.getString("author");
		    	int vol = rs.getInt("vol");
		    	String fistly = rs.getString("firstlyric");
		    	String full = rs.getString("fulllyric");
		    	
		    	bh = new BaiHat();
		    	bh.setId(idbh);
		    	bh.setName(namebh);
		    	bh.setAuthor(author);
		    	bh.setVol(vol);
		    	bh.setFirstLyric(fistly);
		    	bh.setFullLyric(full);
		    }

		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {

		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore

		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}

	    return bh;
	}
	
public static BaiHat findBaiHatById(int id){
		
		Connection cn = connect();
		
		Statement stmt = null;
		ResultSet rs = null;

    	BaiHat bh = null;
    	

		try {
		    stmt = cn.createStatement();
		    String sql = ("SELECT * from bai_hat where Id=" + id);		    
//		    System.out.println("sql ID ne: " + sql);
		    rs = stmt.executeQuery(sql);
		    
		    if(rs.next()){		    
		    	int idbh = rs.getInt("id");
		    	String namebh = rs.getString("name");
		    	String author = rs.getString("author");
		    	int vol = rs.getInt("vol");
		    	String fistly = rs.getString("firstlyric");
		    	String full = rs.getString("fulllyric");
		    	
		    	bh = new BaiHat();
		    	bh.setId(idbh);
		    	bh.setName(namebh);
		    	bh.setAuthor(author);
		    	bh.setVol(vol);
		    	bh.setFirstLyric(fistly);
		    	bh.setFullLyric(full);
		    }

		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {

		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore

		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}

	    return bh;
	}
	
	
public static List<BaiHat> findBaiHatByName(String n, List<Integer> ls){
		
		Connection cn = connect();
		
		Statement stmt = null;
		ResultSet rs = null;

		List<BaiHat> list = new ArrayList<BaiHat>();
    	

		try {
			String sql = ("SELECT * from bai_hat where name like '%" + n + "%' ");
		    stmt = cn.createStatement();
		    if(!ls.isEmpty()){
			    if(ls.contains(0)){
			    	sql += "";	
			    }else{		    	
			    	int count = 0;
			    	sql += (" and vol in (");
			    	for (Integer in : ls) {
			    		if(count != 0){
			    			sql += ( ", " + in);
			    		}else{
			    			sql += (in);
			    		}
			    		count++;
					}
			    	sql += (")");
			    }
		    }else{
		    	sql = ("SELECT * FROM bai_hat WHERE 0");
		    }
//		    System.out.println("sql name ne: " + sql);
		    rs = stmt.executeQuery(sql);
		    
		    while(rs.next()){
		    	int idbh = rs.getInt("id");
		    	String namebh = rs.getString("name");
		    	String author = rs.getString("author");
		    	int vol = rs.getInt("vol");
		    	String fistly = rs.getString("firstlyric");
		    	String full = rs.getString("fulllyric");
		    	
		    	BaiHat bh = new BaiHat();
		    	bh.setId(idbh);
		    	bh.setName(namebh);
		    	bh.setAuthor(author);
		    	bh.setVol(vol);
		    	bh.setFirstLyric(fistly);
		    	bh.setFullLyric(full);
		    	
		    	list.add(bh);
		    }
	    	

		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {

		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore

		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}

	    return list;
	}

public static List<BaiHat> findBaiHatByName(String n){
	
	Connection cn = connect();
	
	Statement stmt = null;
	ResultSet rs = null;

	List<BaiHat> list = new ArrayList<BaiHat>();
	

	try {
		String sql = ("SELECT * from bai_hat where name like '%" + n + "%' ");
	    stmt = cn.createStatement();
	    
//	    System.out.println("sql name ne: " + sql);
	    rs = stmt.executeQuery(sql);
	    
	    while(rs.next()){
	    	int idbh = rs.getInt("id");
	    	String namebh = rs.getString("name");
	    	String author = rs.getString("author");
	    	int vol = rs.getInt("vol");
	    	String fistly = rs.getString("firstlyric");
	    	String full = rs.getString("fulllyric");
	    	
	    	BaiHat bh = new BaiHat();
	    	bh.setId(idbh);
	    	bh.setName(namebh);
	    	bh.setAuthor(author);
	    	bh.setVol(vol);
	    	bh.setFirstLyric(fistly);
	    	bh.setFullLyric(full);
	    	
	    	list.add(bh);
	    }
    	

	}
	catch (SQLException ex){
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
	finally {

	    if (rs != null) {
	        try {
	            rs.close();
	        } catch (SQLException sqlEx) { } // ignore

	        rs = null;
	    }

	    if (stmt != null) {
	        try {
	            stmt.close();
	        } catch (SQLException sqlEx) { } // ignore

	        stmt = null;
	    }
	}

    return list;
}

public static List<BaiHat> findBaiHatByAuthor(String au, List<Integer> ls){
	
	Connection cn = connect();
	
	Statement stmt = null;
	ResultSet rs = null;

	List<BaiHat> list = new ArrayList<>();

	try {
	    stmt = cn.createStatement();
	    String sql = ("SELECT * from bai_hat where author like '%" + au + "%'");
	    if(!ls.isEmpty()){
		    if(ls.contains(0)){
		    	sql += "";	
		    }else{		    	
		    	int count = 0;
		    	sql += (" and vol in (");
		    	for (Integer in : ls) {
		    		if(count != 0){
		    			sql += ( ", " + in);
		    		}else{
		    			sql += (in);
		    		}
		    		count++;
				}
		    	sql += (")");
		    }
	    }else{
	    	sql = ("SELECT * FROM bai_hat WHERE 0");
	    }
//	    System.out.println("sql author ne: " + sql);
	    rs = stmt.executeQuery(sql);
	    
	    while(rs.next()){
	    	int idbh = rs.getInt("id");
	    	String namebh = rs.getString("name");
	    	String author = rs.getString("author");
	    	int vol = rs.getInt("vol");
	    	String fistly = rs.getString("firstlyric");
	    	String full = rs.getString("fulllyric");
	    	
	    	BaiHat bh = new BaiHat();
	    	bh.setId(idbh);
	    	bh.setName(namebh);
	    	bh.setAuthor(author);
	    	bh.setVol(vol);
	    	bh.setFirstLyric(fistly);
	    	bh.setFullLyric(full);
	    	
	    	list.add(bh);
	    }
    	

	}
	catch (SQLException ex){
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
	finally {

	    if (rs != null) {
	        try {
	            rs.close();
	        } catch (SQLException sqlEx) { } // ignore

	        rs = null;
	    }

	    if (stmt != null) {
	        try {
	            stmt.close();
	        } catch (SQLException sqlEx) { } // ignore

	        stmt = null;
	    }
	}

    return list;
}
	

public static List<BaiHat> findBaiHatByLyric(String ly, List<Integer> ls){
	
	Connection cn = connect();
	
	Statement stmt = null;
	ResultSet rs = null;

	List<BaiHat> list = new ArrayList<>();	

	try {
	    stmt = cn.createStatement();
	    String sql = ("SELECT * from bai_hat where firstlyric like '%" + ly + "%'");
	    if(!ls.isEmpty()){
		    if(ls.contains(0)){
		    	sql += "";	
		    }else{		    	
		    	int count = 0;
		    	sql += (" and vol in (");
		    	for (Integer in : ls) {
		    		if(count != 0){
		    			sql += ( ", " + in);
		    		}else{
		    			sql += (in);
		    		}
		    		count++;
				}
		    	sql += (")");
		    }
	    }else{
	    	sql = ("SELECT * FROM bai_hat WHERE 0");
	    }
//	    System.out.println("sql lyric ne: " + sql);
	    rs = stmt.executeQuery(sql);
	    
	    while(rs.next()){
	    	int idbh = rs.getInt("id");
	    	String namebh = rs.getString("name");
	    	String author = rs.getString("author");
	    	int vol = rs.getInt("vol");
	    	String fistly = rs.getString("firstlyric");
	    	String full = rs.getString("fulllyric");
	    	
	    	BaiHat bh = new BaiHat();
	    	bh.setId(idbh);
	    	bh.setName(namebh);
	    	bh.setAuthor(author);
	    	bh.setVol(vol);
	    	bh.setFirstLyric(fistly);
	    	bh.setFullLyric(full);
	    	
	    	list.add(bh);
	    }
    	

	}
	catch (SQLException ex){
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
	finally {

	    if (rs != null) {
	        try {
	            rs.close();
	        } catch (SQLException sqlEx) { } // ignore

	        rs = null;
	    }

	    if (stmt != null) {
	        try {
	            stmt.close();
	        } catch (SQLException sqlEx) { } // ignore

	        stmt = null;
	    }
	}

    return list;
}

//================== Add BaiHat =============================

public static void AddBaiHat(BaiHat bh){
Connection cn = connect();
	
	Statement stmt = null;
//	ResultSet rs = null;
	int res = 0;

	try {
		int id = bh.getId();
		String name = bh.getName();
		String au = bh.getAuthor();
		int vol = bh.getVol();
		String filyr = bh.getFirstLyric();
		String fulyr = bh.getFullLyric();
		
		stmt = cn.createStatement();		

		String sql = String.format("insert into bai_hat(id, name, author, vol, firstlyric, fulllyric) values("
				+ "%d, '%s', '%s' ,%d , '%s', '%s')", id, name, au, vol, filyr, fulyr);		
//		System.out.println("sql add ne: " + sql);
		res = stmt.executeUpdate(sql);
				
	}
	catch (SQLException ex){
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
	finally {

	    if (stmt != null) {
	        try {
	            stmt.close();
	        } catch (SQLException sqlEx) { } // ignore

	        stmt = null;
	    }
	}
}

//================== Add List BaiHat =============================

public static void AddListBaiHat(List<BaiHat> lsbh){
Connection cn = connect();
	
	Statement stmt = null;
	int res = 0;

	try {
		
		String sql = ("insert into bai_hat (id, name, author, vol, firstlyric, fulllyric) values ");
		
		
		int i = 0;
		for (BaiHat bh : lsbh) {
			int id = bh.getId();
			String name = bh.getName();
			String au = bh.getAuthor();
			int vol = bh.getVol();
			String filyr = bh.getFirstLyric();
			String fulyr = bh.getFullLyric();
			if(i!=0){
				sql += (", (" + id + ", '" + name + "', '" + au + "', " + vol + ", '" + filyr + "', '" + fulyr + "')");
			} else{
				sql += (" (" + id + ", '" + name + "', '" + au + "', " + vol + ", '" + filyr + "', '" + fulyr + "')");
			}
			i++;
		}	
		
		stmt = cn.createStatement();			
		System.out.println("sql add list ne: " + sql);
		res = stmt.executeUpdate(sql);
				
	}
	catch (SQLException ex){
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
	finally {

	    if (stmt != null) {
	        try {
	            stmt.close();
	        } catch (SQLException sqlEx) { } // ignore

	        stmt = null;
	    }
	}
}



//=========================== Delete BaiHat =================================
public static void DeleteBaiHat(int id){
Connection cn = connect();
	
	Statement stmt = null;
	int res = 0;

	try {
		
		stmt = cn.createStatement();		

		String sql = ("delete from bai_hat where id=" + id);		
		System.out.println("sql delete ne: " + sql);
		res = stmt.executeUpdate(sql);
				
	}
	catch (SQLException ex){
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
	finally {

	    if (stmt != null) {
	        try {
	            stmt.close();
	        } catch (SQLException sqlEx) { } // ignore

	        stmt = null;
	    }
	}
}

//=========================== Update BaiHat =================================
public static void UpdateBaiHat(BaiHat bh){
Connection cn = connect();
	
	Statement stmt = null;
	int res = 0;

	try {
		int id = bh.getId();
		String name = bh.getName();
		String au = bh.getAuthor();
		int vol = bh.getVol();
		String filyr = bh.getFirstLyric();
		String fulyr = bh.getFullLyric();
		
		stmt = cn.createStatement();		

		String sql = String.format("update bai_hat set name='%s', author='%s', vol='%d', firstlyric='%s', fulllyric='%s' where id='%d'", 
									name, au, vol, filyr, fulyr, id);		
//		System.out.println("sql update ne: " + sql);
		res = stmt.executeUpdate(sql);
				
	}
	catch (SQLException ex){
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
	finally {

	    if (stmt != null) {
	        try {
	            stmt.close();
	        } catch (SQLException sqlEx) { } // ignore

	        stmt = null;
	    }
	}
}



public static void leachBaiHat(){
	Connection cn = connect();
	
	Statement stmt = null;
	ResultSet rs = null;
//	List<BaiHat> list = new ArrayList<>();
	String sql_add = ("insert into bai_hat (id, name, author, vol, firstlyric, fulllyric) values ");
	
	try {
	    
	    
	    stmt = cn.createStatement();
	    String sql = ("SELECT * from song");
	    rs = stmt.executeQuery(sql);
	    
	    int i=0;
	    while(rs.next()){
	    	int idbh = rs.getInt("id");
	    	String namebh = rs.getString("title");
	    	String author = rs.getString("source");
//	    	int vol = rs.getInt("vol");
	    	String lang = rs.getString("lang");
	    	String fistly = rs.getString("lyric");
//	    	String full = rs.getString("fulllyric");
	    	
	    	if (lang.equals("vn")){
		    	if(i==0){
		    		sql_add += ("(" + idbh + ", '" + addSlashes(namebh) + "', '" + addSlashes(author) + "', " + 0 + ", '" + addSlashes(fistly) + "', " + "'full lyric...')");
		    	}
		    	else{
		    		sql_add += (", (" + idbh + ", '" + addSlashes(namebh) + "', '" + addSlashes(author) + "', " + 0 + ", '" + addSlashes(fistly) + "', " + "'full lyric...')");
		    	}
		    	i++;
	    	}
	    	
	    }
	    
	    int fff = stmt.executeUpdate(sql_add);
		System.out.println("so hang bi anh huong: " +fff);
    	

	}
	catch (SQLException ex){
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
	finally {

	    if (rs != null) {
	        try {
	            rs.close();
	        } catch (SQLException sqlEx) { } // ignore

	        rs = null;
	    }

	    if (stmt != null) {
	        try {
	            stmt.close();
	        } catch (SQLException sqlEx) { } // ignore

	        stmt = null;
	    }
	}

	
}

public static int[] findMinMaxVol(){
	
	Connection cn = connect();
	
	Statement stmt = null;
	ResultSet rs = null;
	int[] res = new int[2];
	
	try {	    
	    
	    stmt = cn.createStatement();
	    String sql = ("SELECT MIN(Vol) as MinVol, MAX(Vol) as MaxVol from bai_hat");
	    rs = stmt.executeQuery(sql);
	    
	    if(rs.next()){
	    	int min = rs.getInt("MinVol");
	    	int max = rs.getInt("MaxVol");
	    	res[0] = min;
	    	res[1] = max;	    	
	    	
	    }
	        	

	}
	catch (SQLException ex){
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
	finally {

	    if (rs != null) {
	        try {
	            rs.close();
	        } catch (SQLException sqlEx) { } // ignore

	        rs = null;
	    }

	    if (stmt != null) {
	        try {
	            stmt.close();
	        } catch (SQLException sqlEx) { } // ignore

	        stmt = null;
	    }
	}
	
	return res;
	
}

	
	/*public static void main(String[] args) {
	
		BaiHat kq = findBaiHatById(53317);
		System.out.println("Tim by ID: ");
		System.out.println(kq);
		
		System.out.println("Tim by name");
		List<BaiHat> listkq = findBaiHatByName("khoc trong mua");
		if(listkq.isEmpty())
			System.out.println("ko tim thay bai hat voi' ten do'! ");
		for (BaiHat bh : listkq) {
			System.out.println(bh);
			
		}
		
		BaiHat bh = new BaiHat(1111, "1", "Bùi Bích Phương", 50, "Anh mơ mỗi sáng thức giấc", "Just get out from my way!");
		BaiHat bh1 = new BaiHat(1112, "2", "Bùi Bích Phương", 50, "Anh mơ mỗi sáng thức giấc", "Just get out from my way!");
		BaiHat bh2 = new BaiHat(1113, "3", "Bùi Bích Phương", 50, "Anh mơ mỗi sáng thức giấc", "Just get out from my way!");
		List<BaiHat> lsbh = new ArrayList<>();
		lsbh.add(bh);
		lsbh.add(bh1);
		lsbh.add(bh2);
		
		AddListBaiHat(lsbh);
		
//		AddBaiHat(bh);
//		UpdateBaiHat(bh);
		
//		leachBaiHat();
		
	}*/




}
