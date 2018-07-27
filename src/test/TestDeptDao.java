package test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import com.icss.hr.dept.dao.DeptDao;
import com.icss.hr.dept.pojo.Dept;

/**
 * 测试部门DAO
 * @author Administrator
 *
 */
public class TestDeptDao {
	
	private DeptDao dao = new DeptDao();
	
	@Test
	public void testInsert() throws SQLException {
		
		Dept dept = new Dept("aaa部","bbb区");
		dao.insert(dept);		
	}
	
	@Test
	public void testUpdate() throws SQLException {
		
		Dept dept = new Dept(20,"安全部","南京");
		dao.update(dept);		
	}
	
	@Test
	public void testDelete() throws SQLException {
		
		dao.delete(20);
	}
	
	@Test
	public void testQueryById() throws SQLException {
		
		Dept dept = dao.queryById(40);
		System.out.println(dept);
	}
	
	@Test
	public void testQuery() throws SQLException {
		
		List<Dept> list = dao.query();
		
		for (Dept dept : list) {
			System.out.println(dept);
		}
		
	}

}