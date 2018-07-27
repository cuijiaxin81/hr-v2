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
 * ���Ӳ���
 */
@WebServlet("/AddDeptServlet")
public class AddDeptServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		//�����
		PrintWriter out = response.getWriter();
		
		//��ñ����ݣ����������
		String deptName = request.getParameter("deptName");
		String deptLoc = request.getParameter("deptLoc");
		
		//��װΪPOJO����
		Dept dept = new Dept(deptName,deptLoc);
		
		//����ҵ����
		DeptService service = new DeptService();
		
		try {
			service.addDept(dept);
		} catch (SQLException e) {			
			e.printStackTrace();
			
			//�ֶ�ת����errorҳ
		}
		
		try {
			//ģ�������ӳ�
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