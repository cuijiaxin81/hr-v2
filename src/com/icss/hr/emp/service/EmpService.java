package com.icss.hr.emp.service;

import java.sql.SQLException;
import java.util.List;
import com.icss.hr.common.Pager;
import com.icss.hr.emp.dao.EmpDao;
import com.icss.hr.emp.pojo.Emp;

/**
 * 员工业务
 * @author Administrator
 *
 */
public class EmpService {
	
	private EmpDao dao = new EmpDao();
	
	/**
	 * 登陆验证
	 * @param empLoginName 用户名
	 * @param empPwd 密码
	 * @return 登陆验证结果：1 用户名不存在   2 密码错误  3 登陆成功
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
	 * 检查登陆名是否存在 
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
	 * 增加新员工
	 * @param emp
	 * @throws SQLException 
	 */
	public void addEmp(Emp emp) throws SQLException {
		dao.insert(emp);
	}
	
	/**
	 * 通过id查询单个员工数据
	 * @param empId
	 * @return
	 * @throws SQLException
	 */
	public Emp queryEmpById(Integer empId) throws SQLException {
		
		return dao.queryById(empId);
	}
	
	/**
	 * 返回员工总记录数
	 * @return
	 * @throws SQLException
	 */
	public int getEmpCount() throws SQLException {
		
		return dao.getCount();
	}
	
	/**
	 * 分页查询员工数据
	 * @param pager
	 * @return
	 * @throws SQLException
	 */
	public List<Emp> queryEmpByPage(Pager pager) throws SQLException {
		
		return dao.queryByPage(pager);
	}

	/**
	 * 修改员工
	 * @param emp
	 * @throws SQLException 
	 */
	public void updateEmp(Emp emp) throws SQLException {
		
		dao.update(emp);
	}
	
	/**
	 * 删除员工
	 * @param empId
	 * @throws SQLException
	 */
	public void deleteEmp(Integer empId) throws SQLException {
		
		dao.delete(empId);
	}
	
	/**
	 * 查询返回员工头像数据
	 * @param empLoginName
	 * @return
	 * @throws SQLException
	 */
	public String queryEmpPic(String empLoginName) throws SQLException {
		
		return dao.queryEmpPic(empLoginName);
	}
	
	/**
	 * 更新员工头像
	 * @param empLoginName
	 * @param empPic
	 * @throws SQLException
	 */
	public void updateEmpPic(String empLoginName,String empPic) throws SQLException {
		
		dao.updateEmpPic(empLoginName, empPic);
	}
	
	/**
	 * 修改员工密码
	 * @param empLoginName
	 * @param empPwd
	 * @throws SQLException
	 */
	public void updateEmpPwd(String empLoginName,String empPwd) throws SQLException {
		
		dao.updateEmpPwd(empLoginName, empPwd);
	}
	
	/**
	 * 查询当前密码
	 * @param empLoginName
	 * @return
	 * @throws SQLException
	 */
	public String queryEmpPwd(String empLoginName) throws SQLException {
		
		return dao.queryByName(empLoginName).getEmpPwd();
	}
	
}