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
 * Servlet implementation class UpdateEmpPwdServlet
 */
@WebServlet("/UpdateEmpPwdServlet")
public class UpdateEmpPwdServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		//获得请求参数
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");		
		String empPwd = request.getParameter("empPwd");
		
		//调用业务功能
		EmpService service = new EmpService();
				
		try {
			service.updateEmpPwd(empLoginName, empPwd);
		} catch (SQLException e) {
					
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
