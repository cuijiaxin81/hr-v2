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

import com.icss.hr.emp.service.EmpService;

/**
 * 返回当前用户密码
 */
@WebServlet("/GetEmpPwdServlet")
public class GetEmpPwdServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获得输出流
		PrintWriter out = response.getWriter();
		
		//获得当前用户的登录名
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		System.out.println(empLoginName);
		//调用业务功能
		EmpService service = new EmpService();
		
		try {
			String empPwd = service.queryEmpPwd(empLoginName);
			out.print(empPwd);
			System.out.println(empPwd);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
