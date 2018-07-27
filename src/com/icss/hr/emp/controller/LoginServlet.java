package com.icss.hr.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.hr.dept.service.DeptService;
import com.icss.hr.emp.service.EmpService;

/**
 * 用户登录
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//输出流
		PrintWriter out = response.getWriter();
		
		//获得请求参数 
		String empLoginName = request.getParameter("empLoginName");
		String empPwd = request.getParameter("empPwd");
		
		//调用业务功能
		EmpService service = new EmpService();
		
		try {
			//登录验证结果响应到前端
			int result = service.checkLogin(empLoginName, empPwd);	
			
			//假如登录成功
			if (result == 3) {
				HttpSession session = request.getSession();
				session.setAttribute("empLoginName", empLoginName);
			}
			
			out.print(result);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}