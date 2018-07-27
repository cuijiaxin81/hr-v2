package com.icss.hr.dept.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.dept.service.DeptService;

/**
 * 增加部门
 */
@WebServlet("/AddDeptServlet")
public class AddDeptServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		//输出流
		PrintWriter out = response.getWriter();
		
		//获得表单数据（请求参数）
		String deptName = request.getParameter("deptName");
		String deptLoc = request.getParameter("deptLoc");
		
		//封装为POJO对象
		Dept dept = new Dept(deptName,deptLoc);
		
		//调用业务功能
		DeptService service = new DeptService();
		
		try {
			service.addDept(dept);
		} catch (SQLException e) {			
			e.printStackTrace();
			
			//手动转发到error页
		}
		
		try {
			//模拟网络延迟
			Thread.sleep(2000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}