/*
 * DBUtil.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * jdbc工具类数据库接口
 * @author LT
 * @version 1.0, 2015年9月10日
 */
public class DBUtil {
	public static Connection getConnect() throws Exception{
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/test",//可能会有乱码
					"jdbc:mysql://localhost:3306/test?Unicode=true&characterEncoding=GBK",
					"root", "root");
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return con;
	}
	public static void close(Connection con){
		if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
