package test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.icss.hr.analyze.dao.AnaDao;
import com.icss.hr.analyze.dto.DeptEmpCount;
import com.icss.hr.analyze.dto.DeptSal;

/**
 * ≤‚ ‘ ˝æ›∑÷Œˆ
 * @author Administrator
 *
 */
public class TestAnaDao {
	
	private AnaDao dao = new AnaDao();
	
	@Test
	public void testQueryEmpCount() throws SQLException {
		
		List<DeptEmpCount> list = dao.queryEmpCount();
		
		for (DeptEmpCount item : list) {
			System.out.println(item);
		}
		
	}
	
	@Test
	public void testQueryJobAvgSal() throws SQLException {
		
		List<Map<String, Object>> list = dao.queryJobAvgSal();
		
		System.out.println(list);
		
	}
	
	@Test
	public void testQueryDeptSal() throws SQLException {
		
		List<DeptSal> list = dao.queryDeptSal();
		
		System.out.println(list);
		
	}

}