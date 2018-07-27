package com.icss.hr.dept.service;

import java.sql.SQLException;
import java.util.List;

import com.icss.hr.dept.dao.DeptDao;
import com.icss.hr.dept.pojo.Dept;

/**
 * ����ҵ��
 * @author Administrator
 *
 */
public class DeptService {
	
	private DeptDao dao = new DeptDao();
	
	/**
	 * ���Ӳ���
	 * @throws SQLException 
	 */
	public void addDept(Dept dept) throws SQLException {
		dao.insert(dept);		
	}
	
	/**
	 * �޸Ĳ���
	 * @param dept
	 * @throws SQLException
	 */
	public void updateDept(Dept dept) throws SQLException {
		dao.update(dept);
	}
	
	/**
	 * ɾ������
	 * @param deptId
	 * @throws SQLException
	 */
	public void deleteDept(Integer deptId) throws SQLException {
		dao.delete(deptId);
	}
	
	/**
	 * ���ص�����������
	 * @param deptId
	 * @return
	 * @throws SQLException
	 */
	public Dept queryDeptById(Integer deptId) throws SQLException {
		
		return dao.queryById(deptId);
	}
	
	/**
	 * ���ز�����������
	 * @return
	 * @throws SQLException
	 */
	public List<Dept> queryDept() throws SQLException {
		
		return dao.query();
	}

}