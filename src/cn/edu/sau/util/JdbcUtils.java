package cn.edu.sau.util;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class JdbcUtils {
	
	// 数据库基本信息
	private static String url = "jdbc:mysql://localhost:3306/joker?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true";
	private static String user = "root";
	private static String  password = "1234";
	private static String  dv = "com.mysql.jdbc.Driver";
	// 静态方法
	static {
		try {
			Class.forName(dv);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 连接数据库
	public static Connection getCon() throws SQLException{
		 Connection conn = null;
		 conn = (Connection) DriverManager.getConnection(url, user, password);
		 return  conn;
	}
	
	// 关闭数据库
	public static void close(Statement statement,Connection conn){
		   if(statement !=null){
			   try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		   if(conn !=null){
			   try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
	}
	
	// 静态的关闭方法
	public static void close(PreparedStatement preparedStatement,Connection conn,ResultSet resultSet){
			   if(preparedStatement !=null){
				   try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
			   if(conn !=null){
				   try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
			   
			   if(resultSet!=null){
				   try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}