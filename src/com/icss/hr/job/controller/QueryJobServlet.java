package com.icss.hr.job.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.icss.hr.job.pojo.Job;
import com.icss.hr.job.service.JobService;

/**
 * ��ѯְ������
 */
@WebServlet("/QueryJobServlet")
public class QueryJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//�����
		PrintWriter out = response.getWriter();
		
		//����ҵ����
		JobService service = new JobService();
		
		try {
			List<Job> list = service.queryJob();
			
			//ת��json���ݣ���Ӧ���ͻ���
			Gson gson = new Gson();
			
			out.write(gson.toJson(list));		
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}