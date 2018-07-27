package com.icss.hr.emp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.emp.service.EmpService;

/**
 * 删除员工数据
 */
@WebServlet("/DeleteEmpServlet")
public class DeleteEmpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获得员工编号
		String empId = request.getParameter("empId");
		
		//调用业务功能
		EmpService service = new EmpService();
		
		try {			
			service.deleteEmp(Integer.parseInt(empId));			
		} catch (NumberFormatException | SQLException e) {			
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}