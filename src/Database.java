/******************************
	程序说明：定义数据库类

	最后修改时间：2005-09-18
	
	作者：梁延研 澳门科技大学
*******************************/

import java.sql.*;

public class Database {
	
	Connection conn; 
	ResultSet rs;
	Statement stmt;
	
	public boolean connection() {
		
		//定义连接类型及连接数据库 ACCESS
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String strurl = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=imdb.mdb";
			conn = DriverManager.getConnection(strurl) ;
			stmt = conn.createStatement();
			return true;
		}
		
		//异常抛出
		catch(Exception e) {
			return false;	
		}
		
	}
	
	//定义数据库执行返回值 RS;
	public ResultSet getResult(String strSQL) {
		
		try{
			rs = stmt.executeQuery(strSQL);
			return rs;
		}
		catch(SQLException sqle) {
			return null;
		}
	
	}
	
	//定义数据库修改保存类
	public boolean updateSql(String strSQL) {
		
		try{
			stmt.execute(strSQL);
			conn.commit();
			return true;
		}
		catch(SQLException sqle) {
			return false;
		}
	
	}
	
	//定义关闭数据库类
	public boolean closeConnection() {
		
		try {
			conn.close();
			return true;
		}
		catch(SQLException sqle){
			return false;	
		}
	}
	
}
