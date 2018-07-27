package com.icss.hr.analyze.controller;

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
import com.icss.hr.analyze.dto.DeptEmpCount;
import com.icss.hr.analyze.service.AnaService;

/**
 * 查询部门人数
 */
@WebServlet("/QueryDeptEmpCountServlet")
public class QueryDeptEmpCountServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		//调用业务功能
		AnaService service = new AnaService();
		
		try {
			List<DeptEmpCount> list = service.queryEmpCount();
			
			//响应json数据到客户端
			Gson gson = new Gson();			
			out.print( gson.toJson(list) );
		} catch (SQLException e) {			
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}