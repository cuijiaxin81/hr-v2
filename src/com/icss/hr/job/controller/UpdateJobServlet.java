package com.icss.hr.job.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.job.pojo.Job;
import com.icss.hr.job.service.JobService;

/**
 * 修改职务
 */
@WebServlet("/UpdateJobServlet")
public class UpdateJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//输出流
		PrintWriter out = response.getWriter();
		
		//获得表单数据
		String jobId = request.getParameter("jobId");
		String jobName = request.getParameter("jobName");
		String jobMinSal = request.getParameter("jobMinSal");
		String jobMaxSal = request.getParameter("jobMaxSal");
				
		//封装为POJO对象
		Job job = new Job(Integer.parseInt(jobId), jobName, Integer.parseInt(jobMinSal), Integer.parseInt(jobMaxSal));
		
		//调用业务功能
		JobService service = new JobService();
		
		try {
			service.updateJob(job);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
