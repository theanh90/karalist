package com.bui.karalist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bui.karalist.model.BaiHat;
import com.bui.karalist.model.Role;
import com.bui.karalist.model.User;

public class DaoUser {
	
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
	
	public static User getUser(String name, String pass){
		Connection cn = connect();
		
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rsRoles = null;

    	User user = null;
    	

		try {
		    stmt = cn.createStatement();
		    String sql = ("SELECT * from user where username='" + name + "' and password='" + pass + "'");
//		    System.out.println("sql ID ne: " + sql);
		    rs = stmt.executeQuery(sql);
		    
		    if(rs.next()){		    
		    	int id = rs.getInt("id");
		    	String uname = rs.getString("username");
		    	String dname = rs.getString("displayname");
		    	Date date = rs.getDate("createddate");
		    	
		    	user = new User();
		    	user.setId(id);
		    	user.setUserName(uname);
		    	user.setDisplayName(dname);
		    	user.setCreateDate(date);
		    	
		    	String sql_getRow = ("select * from permiss where iduser=" + id);
		    	rsRoles = stmt.executeQuery(sql_getRow);
		    	List<Role> roles = new ArrayList<>();
		    	while(rsRoles.next()){
		    		int idRole = rsRoles.getInt("IdRole");
			    	roles.add(Role.getRoleFromId(idRole));
		    	}

		    	user.setRoles(roles);
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

	    return user;
	
	}
	
	/*public static void main(String[] args) {
		User us1 = new User();
		us1 = getUser("Admin", "e99a18c428cb38d5f260853678922e03");
		System.out.println(us1);
	}*/
	
	
	

}
