package com.icss.hr.analyze.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.icss.hr.analyze.dto.DeptEmpCount;
import com.icss.hr.analyze.dto.DeptSal;
import com.icss.hr.common.DbUtil;

/**
 * 数据分析dao
 * 
 * @author Administrator
 *
 */
public class AnaDao {

	/**
	 * 查询每个部门的员工人数
	 * 采用DTO形式封装查询结果
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<DeptEmpCount> queryEmpCount() throws SQLException {

		Connection conn = DbUtil.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("select d.dept_name dept_name,count(e.emp_id) emp_count ");
		sql.append("from  dept d ");
		sql.append("left outer join emp e on d.dept_id=e.emp_dept_id ");
		sql.append("group by d.dept_name");

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		ArrayList<DeptEmpCount> list = new ArrayList<>();

		while (rs.next()) {
			DeptEmpCount deptEmpCount = new DeptEmpCount(rs.getString(1), rs.getInt(2));
			list.add(deptEmpCount);
		}

		rs.close();
		pstmt.close();
		conn.close();

		return list;
	}

	/**
	 * 查询每种职务的平均工资
	 * 采用List包含Map集合封装查询结果
	 * 
	 * @throws SQLException
	 */
	public List<Map<String, Object>> queryJobAvgSal() throws SQLException {

		ArrayList<Map<String, Object>> list = new ArrayList<>();

		Connection conn = DbUtil.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("select j.job_name job_name,trunc(nvl(avg(e.emp_salary),0)) avg_sal ");
		sql.append("from  job j ");
		sql.append("left outer join emp e on j.job_id=e.emp_job_id ");
		sql.append("group by j.job_name");

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("jobName", rs.getString(1));
			map.put("avgSal", rs.getInt(2));
			
			list.add(map);			
		}
		
		rs.close();
		pstmt.close();
		conn.close();

		return list;
	}
	
	/**
	 * 查询每个部门的最高和最低工资
	 * @throws SQLException 
	 */
	public  List<DeptSal> queryDeptSal() throws SQLException {
		
		List<DeptSal> list = new ArrayList<>();
		
		Connection conn = DbUtil.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT  d.dept_name dept_name,MAX(e.emp_salary) max_sal,MIN(e.emp_salary) min_sal");
		sql.append("  FROM dept d");
		sql.append("  LEFT OUTER JOIN emp e ON d.dept_id = e.emp_dept_id");
		sql.append("  GROUP BY d.dept_name");
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			DeptSal deptSal = new DeptSal(rs.getString(1), rs.getInt(2), rs.getInt(3));
			
			list.add(deptSal);
		}
		
		rs.close();
		pstmt.close();
		
		return list;
	}

}