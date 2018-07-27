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
import com.icss.hr.analyze.dto.DeptSal;
import com.icss.hr.analyze.service.AnaService;

/**
 * 部门最低最高工资统计分析
 */
@WebServlet("/QueryDeptSalServlet")
public class QueryDeptSalServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		PrintWriter out = response.getWriter();
		
		AnaService service = new AnaService();
		
		try {
			List<DeptSal> list = service.queryDeptSal();
			
			Gson gson = new Gson();
			out.print(gson.toJson(list));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
