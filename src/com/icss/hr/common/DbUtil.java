package com.icss.hr.common;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.org.apache.bcel.internal.generic.NEW;

import oracle.jdbc.driver.OracleDriver;

/**
 * ���ݿ����ӹ�����
 * 
 * @author Administrator
 *
 */
public class DbUtil {

	// ���������̶߳���
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

	// ����Դ����
	private static ComboPooledDataSource dataSource;

	static {
		try {
			dataSource = new ComboPooledDataSource();

			// ���Ӳ�������
			dataSource.setUser("myhr");
			dataSource.setPassword("myhr");
			dataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
			dataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");

			// ���ӳ�����
			dataSource.setInitialPoolSize(10); // ��ʼ������
			dataSource.setMaxPoolSize(15); // ���������
			dataSource.setMinPoolSize(10); // ��С������
			dataSource.setMaxIdleTime(60); // ������ʱ��60��û����ʹ�þ��Զ�����
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

	}

	/**
	 * ����һ�����ݿ����Ӷ���
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {

		// �жϱ����߳����Ƿ��Ѿ������ݿ�����
		Connection conn = threadLocal.get();

		// ���û�����ݿ����Ӷ��󣬻������Ӷ����Ѿ����رգ��򴴽������Ӷ��󷵻�
		if (conn == null || conn.isClosed()) {
			
			conn = dataSource.getConnection();

			// �������Ӷ��󵽱����߳���
			threadLocal.set(conn);
		}

		return conn;
	}

	/**
	 * �ر�����
	 */
	public static void close() {
		// �ӱ����߳���ȡ�����ݿ����Ӷ���
		Connection conn = threadLocal.get();

		// ������Ӷ�����ڣ��͹ر�����
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