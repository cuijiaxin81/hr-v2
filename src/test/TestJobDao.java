package test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;


import com.icss.hr.job.dao.JobDao;
import com.icss.hr.job.pojo.Job;

/**
 * ≤‚ ‘÷∞ŒÒdao
 * @author Administrator
 *
 */
public class TestJobDao {
	
	private JobDao dao = new JobDao();
	
	@Test
	public void testInsert() throws SQLException{
		Job job = new Job("lijg",1234,2345);
		dao.insert(job);
	}
	
	@Test
	public void testUpdate() throws SQLException{
		Job job = new Job(5,"jiji",1234,2345);
		dao.update(job);
	}
	
	@Test
	public void testDelete() throws SQLException{
		
		dao.delete(21);
		
	}
	
	@Test
	public void testQueryById() throws SQLException{
		
		Job job = dao.queryById(11);
		System.out.println(job);
		
	}
	
	@Test
	public void testQuery() throws SQLException{
		
		List<Job> list = dao.query();

		for(Job job :list){
			System.out.println(job);
		}
	}

}