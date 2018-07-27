package com.icss.hr.common;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.org.apache.bcel.internal.generic.NEW;

import oracle.jdbc.driver.OracleDriver;

/**
 * 数据库连接工具类
 * 
 * @author Administrator
 *
 */
public class DbUtil {

	// 创建本地线程对象
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

	// 数据源对象
	private static ComboPooledDataSource dataSource;

	static {
		try {
			dataSource = new ComboPooledDataSource();

			// 连接参数设置
			dataSource.setUser("myhr");
			dataSource.setPassword("myhr");
			dataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
			dataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");

			// 连接池设置
			dataSource.setInitialPoolSize(10); // 初始连接数
			dataSource.setMaxPoolSize(15); // 最大连接数
			dataSource.setMinPoolSize(10); // 最小连接数
			dataSource.setMaxIdleTime(60); // 最大空闲时间60秒没有人使用就自动销毁
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 返回一个数据库连接对象
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {

		// 判断本地线程中是否已经有数据库连接
		Connection conn = threadLocal.get();

		// 如果没有数据库连接对象，或者连接对象已经被关闭，则创建新连接对象返回
		if (conn == null || conn.isClosed()) {
			
			conn = dataSource.getConnection();

			// 设置连接对象到本地线程中
			threadLocal.set(conn);
		}

		return conn;
	}

	/**
	 * 关闭连接
	 */
	public static void close() {
		// 从本地线程中取出数据库连接对象
		Connection conn = threadLocal.get();

		// 如果连接对象存在，就关闭连接
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				threadLocal.remove();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}