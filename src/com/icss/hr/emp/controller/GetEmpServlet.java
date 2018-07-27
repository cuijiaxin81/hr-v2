package com.icss.hr.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;

/**
 * 获得指定id的员工数据
 */
@WebServlet("/GetEmpServlet")
public class GetEmpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();

		//获得员工编号
		String empId = request.getParameter("empId");
		
		//调用业务功能
		EmpService service = new EmpService();
		
		try {			
			Emp emp = service.queryEmpById(Integer.parseInt(empId));
			
			//响应json数据到客户端
			Gson gson = new GsonBuilder()  
					  .setDateFormat("yyyy-MM-dd")  
					  .create();
			
			out.print(gson.toJson(emp));
			
		} catch (NumberFormatException | SQLException e) {			
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}