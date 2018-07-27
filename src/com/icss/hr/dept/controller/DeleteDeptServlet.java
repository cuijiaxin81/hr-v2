package com.icss.hr.dept.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.dept.service.DeptService;

/**
 * 删除部门
 */
@WebServlet("/DeleteDeptServlet")
public class DeleteDeptServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获得部门id
		String deptId = request.getParameter("deptId");
		
		//调用业务功能
		DeptService service = new DeptService();
		
		try {
			service.deleteDept(Integer.parseInt(deptId));
		} catch (Exception e) {			
			e.printStackTrace();
		} 

	}

}