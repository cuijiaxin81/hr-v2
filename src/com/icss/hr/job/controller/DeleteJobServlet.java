package com.icss.hr.job.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.job.service.JobService;

/**
 * 删除职务
 */
@WebServlet("/DeleteJobServlet")
public class DeleteJobServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获得职务Id
		String jobId = request.getParameter("jobId");
		
		//调用业务功能
		JobService service = new JobService();
		
		try {
			service.deleteJob(Integer.parseInt(jobId));
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}