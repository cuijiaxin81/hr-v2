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
 * ���ص�ǰ�û�����
 */
@WebServlet("/GetEmpPwdServlet")
public class GetEmpPwdServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//��������
		PrintWriter out = response.getWriter();
		
		//��õ�ǰ�û��ĵ�¼��
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		System.out.println(empLoginName);
		//����ҵ����
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
