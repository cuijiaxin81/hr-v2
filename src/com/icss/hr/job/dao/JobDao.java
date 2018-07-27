package com.icss.hr.job.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.icss.hr.common.DbUtil;
import com.icss.hr.job.pojo.Job;

/**
 * 职务dao
 * @author Administrator
 *
 */
public class JobDao {
	
	/**
	 * 插入
	 * @param job
	 * @throws SQLException 
	 */
	public void insert(Job job) throws SQLException{
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "insert into job values(job_seq.nextval,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, job.getJobName());
		pstmt.setInt(2, job.getJobMinSal());
		pstmt.setInt(3, job.getJobMaxSal());
		
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	
	/**
	 * 更新
	 * @param job
	 * @throws SQLException 
	 */
	public void update(Job job) throws SQLException{
		Connection conn = DbUtil.getConnection();
		String sql = "update job set job_name=?,job_min_sal=?,job_max_sal=? where job_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, job.getJobName());
		pstmt.setInt(2, job.getJobMinSal());
		pstmt.setInt(3, job.getJobMaxSal());
		pstmt.setInt(4, job.getJobId());
		
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		
	}
	
	/**
	 * 删除
	 * @throws SQLException 
	 */
	public void delete(Integer jobId) throws SQLException{
		Connection conn = DbUtil.getConnection();
		String sql = "delete from job where job_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, jobId);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		
	}
	
	/**
	 * 查询单条数据
	 * @param jobId
	 * @return
	 * @throws SQLException 
	 */
	public Job queryById(Integer jobId) throws SQLException{
		Connection conn = DbUtil.getConnection();
		String sql = "select * from job where job_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, jobId);
		
		ResultSet rs = pstmt.executeQuery();
		
		Job job = null;
		
		if (rs.next()) {
			job = new Job(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return job;
	}
	
	public List<Job> query() throws SQLException{
		
		ArrayList<Job> list= new ArrayList<>();
		Connection conn = DbUtil.getConnection();
		
		String sql = "select * from job";
		
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Job job = new Job(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
			list.add(job);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
	}
	
}