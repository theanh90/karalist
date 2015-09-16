package com.bui.karalist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bui.karalist.model.BaiHat;

public class DaoYeuThich {
	
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
	
public static BaiHat findYeuThichById(int id){
		
		Connection cn = connect();
		
		Statement stmt = null;
		ResultSet rs = null;

    	BaiHat bh = null;
    	

		try {
		    stmt = cn.createStatement();
		    String sql = ("SELECT * from yeu_thich where Id=" + id);		    
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
	
	// ===================== insert BaiHat to yeu_thich ===========================================
	public static void AddToBookmark(int msbh){
		
		Connection cn = connect();	
		Statement stmt = null;
		ResultSet rs = null;
//		List<BaiHat> list = new ArrayList<>();
		String sql_add = ("insert into yeu_thich (id, name, author, vol, firstlyric, fulllyric) values ");
		
		try {	    
		    stmt = cn.createStatement();
		    String sql = ("SELECT * from bai_hat where Id = " + msbh);
		    rs = stmt.executeQuery(sql);
		    
		    if(rs.next()){
		    	int idbh = rs.getInt("id");
		    	String namebh = rs.getString("name");
		    	String author = rs.getString("author");
		    	int vol = rs.getInt("vol");
		    	String fistly = rs.getString("firstlyric");
		    	String full = rs.getString("fulllyric");
		    	
		    	sql_add += ("(" + idbh + ", '" + addSlashes(namebh) + "', '" + addSlashes(author) + "', " + vol + ", '" + addSlashes(fistly) + "', " + "'" + addSlashes(full) + "')");
			    
		    }
		    
		    int count = stmt.executeUpdate(sql_add);
//			System.out.println("so hang bi anh huong: " + count);
	    	

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
	
	public static List<BaiHat> findYeuThichByName(String n){
		
		Connection cn = connect();
		
		Statement stmt = null;
		ResultSet rs = null;

		List<BaiHat> list = new ArrayList<BaiHat>();
		

		try {
			String sql = ("SELECT * from yeu_thich where name like '%" + n + "%' ");
		    stmt = cn.createStatement();
		    
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
	
	public static void deleteYeuThich(int id){
		Connection cn = connect();
			
			Statement stmt = null;
			int res = 0;

			try {
				
				stmt = cn.createStatement();		

				String sql = ("delete from yeu_thich where id=" + id);		
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

}
