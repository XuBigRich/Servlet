package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConFactory {
	//获取连接
	public static Connection getCon(){
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url="jdbc:sqlserver://localhost:1433;databaseName=test";
			con=DriverManager.getConnection(url,"sa","123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	//关闭连接
	public static void close(Connection con){
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
