package test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.icss.hr.common.Pager;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.dao.EmpDao;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.job.pojo.Job;

/**
 * 测试员工dao
 * @author Administrator
 *
 */
public class TestEmpDao {
	
	private EmpDao dao = new EmpDao();
	
	@Test
	public void testInsert() throws SQLException {
		
		Dept dept = new Dept();
		dept.setDeptId(10);
		
		Job job = new Job();
		job.setJobId(1);
		
		Emp emp = new Emp("张三","zhangsan","123456","zhangsan@163.com","13012345678"
				,Date.valueOf("2005-01-01"),6000.0,dept,job,null,"无");	
		
		dao.insert(emp);		
		
	}
	
	@Test
	public void testInsert2() throws SQLException {
		
		Dept dept = new Dept();
		dept.setDeptId(30);
		
		Job job = new Job();
		job.setJobId(4);
		
		for (int i = 1;i <= 30;i ++) {
			Emp emp = new Emp("杰克" + i,"jack" + i,"123456","jack@163.com","13012345678"
					,Date.valueOf("2007-01-01"),7000.0,dept,job,null,"无");	
			
			dao.insert(emp);
		}
		
	}
	
	@Test
	public void testUpdate() throws SQLException {
		
		Dept dept = new Dept();
		dept.setDeptId(50);
		
		Job job = new Job();
		job.setJobId(2);
		
		Emp emp = new Emp(51,"张三","zhangsan","999999","zhangsan33333@163.com","13033333333"
				,Date.valueOf("2011-09-09"),7500.0,dept,job,null,"删除web前端和大数据库分析");	
		
		dao.update(emp);
	}
	
	@Test
	public void testDelete() throws SQLException {
		
		dao.delete(28);
	}
	
	@Test
	public void testQueryById() throws SQLException {
		
		Emp emp = dao.queryById(1);
		
		System.out.println(emp);
	}
	
	@Test
	public void testGetCount() throws SQLException {
		
		int count = dao.getCount();
		System.out.println(count);
	}
	
	@Test
	public void testQueryByPage() throws SQLException {
		
		Pager pager = new Pager(dao.getCount(), 7, 10);
		
		List<Emp> list = dao.queryByPage(pager);
		
		for (Emp emp : list) {
			System.out.println(emp);
		}
		
	}
	
	@Test
	public void testQueryByName() throws SQLException {
		
		Emp emp = dao.queryByName("jack1");
		System.out.println(emp);
		
	}
	
	@Test
	public void testQueryEmpPic() throws SQLException {
		
		String empPic = dao.queryEmpPic("zhangsan");
		System.out.println(empPic);
		
	}	
	
	@Test
	public void testUpdateEmpPic() throws SQLException {
		
		dao.updateEmpPic("jack2", null);
		
	}	
	
	@Test
	public void testUpdateEmpPwd() throws SQLException {
		
		dao.updateEmpPwd("jack3", "666999");
		
	}
	

}