/******************************
	����˵�����������ݿ���

	����޸�ʱ�䣺2005-09-18
	
	���ߣ������� ���ſƼ���ѧ
*******************************/

import java.sql.*;

public class Database {
	
	Connection conn; 
	ResultSet rs;
	Statement stmt;
	
	public boolean connection() {
		
		//�����������ͼ��������ݿ� ACCESS
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String strurl = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=imdb.mdb";
			conn = DriverManager.getConnection(strurl) ;
			stmt = conn.createStatement();
			return true;
		}
		
		//�쳣�׳�
		catch(Exception e) {
			return false;	
		}
		
	}
	
	//�������ݿ�ִ�з���ֵ RS;
	public ResultSet getResult(String strSQL) {
		
		try{
			rs = stmt.executeQuery(strSQL);
			return rs;
		}
		catch(SQLException sqle) {
			return null;
		}
	
	}
	
	//�������ݿ��޸ı�����
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
	
	//����ر����ݿ���
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
