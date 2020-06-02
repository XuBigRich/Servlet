<<<<<<< HEAD
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
=======
package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConFactory {
	//鑾峰彇杩炴帴
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
	//鍏抽棴杩炴帴
	public static void close(Connection con){
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
>>>>>>> f755fd64db48b127135e88fd8ca6f213464fbd42
