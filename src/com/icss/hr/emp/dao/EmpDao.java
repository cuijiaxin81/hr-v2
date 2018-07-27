package com.icss.hr.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.filters.RestCsrfPreventionFilter;

import com.icss.hr.common.DbUtil;
import com.icss.hr.common.Pager;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.job.pojo.Job;

/**
 * 员工dao
 * 
 * @author Administrator
 *
 */
public class EmpDao {

	/**
	 * 插入数据
	 * 
	 * @param emp
	 * @throws SQLException
	 */
	public void insert(Emp emp) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "insert into emp values (emp_seq.nextval,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, emp.getEmpName());
		pstmt.setString(2, emp.getEmpLoginName());
		pstmt.setString(3, emp.getEmpPwd());
		pstmt.setString(4, emp.getEmpEmail());
		pstmt.setString(5, emp.getEmpPhone());
		pstmt.setDate(6, emp.getEmpHiredate());
		pstmt.setDouble(7, emp.getEmpSalary());
		pstmt.setInt(8, emp.getDept().getDeptId());
		pstmt.setInt(9, emp.getJob().getJobId());
		pstmt.setString(10, emp.getEmpPic());
		pstmt.setString(11, emp.getEmpInfo());

		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	/**
	 * 修改数据
	 * 
	 * @param emp
	 * @throws SQLException
	 */
	public void update(Emp emp) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "update emp set emp_phone=?,emp_hiredate=?,emp_salary=?,emp_dept_id=?,emp_job_id=?,emp_info=? "
				+ "where emp_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, emp.getEmpPhone());
		pstmt.setDate(2, emp.getEmpHiredate());
		pstmt.setDouble(3, emp.getEmpSalary());
		pstmt.setInt(4, emp.getDept().getDeptId());
		pstmt.setInt(5, emp.getJob().getJobId());
		pstmt.setString(6, emp.getEmpInfo());
		pstmt.setInt(7, emp.getEmpId());

		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	/**
	 * 删除数据
	 * 
	 * @param empId
	 * @throws SQLException
	 */
	public void delete(Integer empId) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "delete from emp where emp_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, empId);

		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	/**
	 * 查询单条数据
	 * 
	 * @throws SQLException
	 */
	public Emp queryById(Integer empId) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "select * from emp where emp_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, empId);

		ResultSet rs = pstmt.executeQuery();

		Emp emp = null;

		if (rs.next()) {

			Dept dept = new Dept();
			dept.setDeptId(rs.getInt(9));

			Job job = new Job();
			job.setJobId(rs.getInt(10));

			emp = new Emp(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getDate(7), rs.getDouble(8), dept, job, rs.getString(11), rs.getString(12));

		}

		rs.close();
		pstmt.close();
		conn.close();

		return emp;
	}

	/**
	 * 分页查询
	 * 
	 * @param pager
	 * @return
	 * @throws SQLException
	 */
	public List<Emp> queryByPage(Pager pager) throws SQLException {

		ArrayList<Emp> list = new ArrayList<>();

		Connection conn = DbUtil.getConnection();

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * ");
		sb.append("FROM   (SELECT rownum rnum,");
		sb.append("               e.emp_id,");
		sb.append("               e.emp_name,");
		sb.append("               e.emp_email,");
		sb.append("               e.emp_phone,");
		sb.append("               d.dept_name,");
		sb.append("               j.job_name");
		sb.append("        FROM   emp e ");
		sb.append("        LEFT   OUTER JOIN dept d ON e.emp_dept_id = d.dept_id ");
		sb.append("        LEFT   OUTER JOIN job j ON e.emp_job_id = j.job_id) ");
		sb.append("WHERE  rnum BETWEEN ? AND ?");

		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		pstmt.setInt(1, pager.getStart());
		pstmt.setInt(2, pager.getEnd());

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			Dept dept = new Dept();
			dept.setDeptName(rs.getString(6));

			Job job = new Job();
			job.setJobName(rs.getString(7));

			Emp emp = new Emp();
			emp.setEmpId(rs.getInt(2));
			emp.setEmpName(rs.getString(3));
			emp.setEmpEmail(rs.getString(4));
			emp.setEmpPhone(rs.getString(5));
			emp.setDept(dept);
			emp.setJob(job);

			list.add(emp);
		}

		rs.close();
		pstmt.close();
		conn.close();

		return list;
	}

	/**
	 * 返回总记录数
	 * 
	 * @throws SQLException
	 */
	public int getCount() throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "select count(*) from emp";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();
		rs.next();

		int count = rs.getInt(1);

		rs.close();
		pstmt.close();
		conn.close();

		return count;
	}

	/**
	 * 根据用户名查询返回员工数据
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Emp queryByName(String empLoginName) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "select emp_pwd from emp where emp_login_name=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, empLoginName);

		ResultSet rs = pstmt.executeQuery();

		Emp emp = null;

		if (rs.next()) {
			emp = new Emp();
			emp.setEmpPwd(rs.getString(1));
		}

		rs.close();
		pstmt.close();
		conn.close();

		return emp;
	}

	/**
	 * 根据用户名返回头像base64数据
	 * @throws SQLException 
	 */
	public String queryEmpPic(String empLoginName) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "select emp_pic from emp where emp_login_name=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, empLoginName);
		
		ResultSet rs = pstmt.executeQuery();
		
		String empPic = null;
		
		if (rs.next()) {
			empPic = rs.getString(1);
		}
		
		rs.close();
		pstmt.close();
		conn.close();

		return empPic;
	}
	
	/**
	 * 更新头像数据
	 * @param empLoginName
	 * @param empPic
	 * @throws SQLException 
	 */
	public void updateEmpPic(String empLoginName,String empPic) throws SQLException {
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "update emp set emp_pic=? where emp_login_name=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, empPic);
		pstmt.setString(2, empLoginName);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();		
	}
	
	/**
	 * 修改密码
	 * @throws SQLException 
	 */
	public void updateEmpPwd(String empLoginName,String empPwd) throws SQLException {
		
		Connection conn = DbUtil.getConnection();
		String sql = "update emp set emp_pwd=? where emp_login_name=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, empPwd);
		pstmt.setString(2, empLoginName);
		
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		
	}

}