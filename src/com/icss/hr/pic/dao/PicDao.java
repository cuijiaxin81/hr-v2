package com.icss.hr.pic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.icss.hr.common.DbUtil;
import com.icss.hr.common.Pager;
import com.icss.hr.pic.pojo.Pic;


/**
 * 图库dao
 * @author Administrator
 *
 */
public class PicDao {
	
	/**
	 * 插入数据
	 * @throws SQLException 
	 */
	public void insert(Pic pic) throws SQLException {
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "insert into pic values (pic_seq.nextval,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, pic.getPicName());
		pstmt.setString(2, pic.getPicInfo());
		pstmt.setLong(3, pic.getPicSize());
		pstmt.setString(4, pic.getPicAuthor());
		pstmt.setBinaryStream(5, pic.getPicData());		
		Timestamp timestamp = new Timestamp(new Date().getTime()); //把java.uitl.Date转换为TimeStamp类型		
		pstmt.setTimestamp(6, timestamp);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();		
	}
	
	/**
	 * 分页查询图库
	 * @throws SQLException 
	 */
	public List<Pic> queryByPage(Pager pager) throws SQLException {
		
		ArrayList<Pic> list = new ArrayList<>();
		
		Connection conn = DbUtil.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * ");
		sql.append("FROM   (SELECT rownum rnum,");
		sql.append("               p.* ");
		sql.append("        FROM   (SELECT * ");
		sql.append("                FROM   pic ");
		sql.append("                ORDER  BY pic_datetime DESC) p) ");
		sql.append("WHERE  rnum BETWEEN ? AND ?");
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		
		pstmt.setInt(1, pager.getStart());
		pstmt.setInt(2, pager.getEnd());
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Pic pic = new Pic(rs.getInt(2),rs.getString(3), rs.getString(4), rs.getLong(5), rs.getString(6)
					, null,rs.getTimestamp(8));
			list.add(pic);		
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
	}	
	
	/**
	 * 获得总记录数
	 * @throws SQLException 
	 */
	public int getCount() throws SQLException {
		
		Connection conn = DbUtil.getConnection();

		String sql = "select count(*) from pic";
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
	 * 删除图片
	 * @throws SQLException 
	 */
	public void delete(Integer picId) throws SQLException {
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "delete from pic where pic_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, picId);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();		
	}	
	
	/**
	 * 通过id返回图片数据
	 * @throws SQLException 
	 */
	public Pic queryById(Integer picId) throws SQLException {
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "select * from pic where pic_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, picId);
		
		ResultSet rs = pstmt.executeQuery();
		
		Pic pic = null;
		
		if (rs.next()) {
			pic = new Pic();
			pic.setPicName(rs.getString(2));
			pic.setPicData(rs.getBinaryStream(6));
		}
		
		rs.close();
		pstmt.close();
//		conn.close();
		
		return pic;
	}

}