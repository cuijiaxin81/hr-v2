package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.icss.hr.common.Pager;
import com.icss.hr.pic.dao.PicDao;
import com.icss.hr.pic.pojo.Pic;

/**
 * ≤‚ ‘Õºø‚Dao
 * @author Administrator
 *
 */
public class TestPicDao {
	
	private PicDao dao = new PicDao();
	
	@Test
	public void testInsert() throws Exception {
		
		File file = new File("e:\\1.jpg");
		
		FileInputStream fis = new FileInputStream(file);
		
		Pic pic = new Pic("1.jpg", "Œ‚“‡∑≤µƒ’’∆¨", file.length(), "zhangsan", fis);
		
		dao.insert(pic);
		
	}
	
	@Test
	public void testQueryByPage() throws SQLException {
		
		Pager pager = new Pager(dao.getCount(),7,3);
		
		List<Pic> list = dao.queryByPage(pager);
		
		for (Pic pic : list) {
			System.out.println(pic);
		}
		
	}
	
	@Test
	public void testDelete() throws SQLException {
		
		dao.delete(23);
		
	}
	
	@Test
	public void testQueryById() throws SQLException, IOException {
		
		Pic pic = dao.queryById(3);
		
		System.out.println(pic.getPicName());
		
		InputStream in = pic.getPicData();
		
		int i = in.read();
		
	}

}