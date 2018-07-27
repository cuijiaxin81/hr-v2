package com.icss.hr.emp.service;

import java.sql.SQLException;
import java.util.List;
import com.icss.hr.common.Pager;
import com.icss.hr.emp.dao.EmpDao;
import com.icss.hr.emp.pojo.Emp;

/**
 * Ա��ҵ��
 * @author Administrator
 *
 */
public class EmpService {
	
	private EmpDao dao = new EmpDao();
	
	/**
	 * ��½��֤
	 * @param empLoginName �û���
	 * @param empPwd ����
	 * @return ��½��֤�����1 �û���������   2 �������  3 ��½�ɹ�
	 * @throws SQLException 
	 */
	public int checkLogin(String empLoginName,String empPwd) throws SQLException{
		
		Emp emp = dao.queryByName(empLoginName);
		
		if (emp == null) {
			return 1;
		} else if ( !empPwd.equals(emp.getEmpPwd()) ) {
			return 2;
		}
				
		return 3;
	}
	
	/**
	 * ����½���Ƿ���� 
	 * @param empLoginName
	 * @return
	 * @throws SQLException 
	 */
	public boolean checkLoginName(String empLoginName) throws SQLException {
		
		Emp emp = dao.queryByName(empLoginName);
		
		if (emp == null)
			return false;
		
		return true;
	}
	
	/**
	 * ������Ա��
	 * @param emp
	 * @throws SQLException 
	 */
	public void addEmp(Emp emp) throws SQLException {
		dao.insert(emp);
	}
	
	/**
	 * ͨ��id��ѯ����Ա������
	 * @param empId
	 * @return
	 * @throws SQLException
	 */
	public Emp queryEmpById(Integer empId) throws SQLException {
		
		return dao.queryById(empId);
	}
	
	/**
	 * ����Ա���ܼ�¼��
	 * @return
	 * @throws SQLException
	 */
	public int getEmpCount() throws SQLException {
		
		return dao.getCount();
	}
	
	/**
	 * ��ҳ��ѯԱ������
	 * @param pager
	 * @return
	 * @throws SQLException
	 */
	public List<Emp> queryEmpByPage(Pager pager) throws SQLException {
		
		return dao.queryByPage(pager);
	}

	/**
	 * �޸�Ա��
	 * @param emp
	 * @throws SQLException 
	 */
	public void updateEmp(Emp emp) throws SQLException {
		
		dao.update(emp);
	}
	
	/**
	 * ɾ��Ա��
	 * @param empId
	 * @throws SQLException
	 */
	public void deleteEmp(Integer empId) throws SQLException {
		
		dao.delete(empId);
	}
	
	/**
	 * ��ѯ����Ա��ͷ������
	 * @param empLoginName
	 * @return
	 * @throws SQLException
	 */
	public String queryEmpPic(String empLoginName) throws SQLException {
		
		return dao.queryEmpPic(empLoginName);
	}
	
	/**
	 * ����Ա��ͷ��
	 * @param empLoginName
	 * @param empPic
	 * @throws SQLException
	 */
	public void updateEmpPic(String empLoginName,String empPic) throws SQLException {
		
		dao.updateEmpPic(empLoginName, empPic);
	}
	
	/**
	 * �޸�Ա������
	 * @param empLoginName
	 * @param empPwd
	 * @throws SQLException
	 */
	public void updateEmpPwd(String empLoginName,String empPwd) throws SQLException {
		
		dao.updateEmpPwd(empLoginName, empPwd);
	}
	
	/**
	 * ��ѯ��ǰ����
	 * @param empLoginName
	 * @return
	 * @throws SQLException
	 */
	public String queryEmpPwd(String empLoginName) throws SQLException {
		
		return dao.queryByName(empLoginName).getEmpPwd();
	}
	
}