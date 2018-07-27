package com.icss.hr.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;
import com.icss.hr.job.pojo.Job;

/**
 * 修改员工
 */
@WebServlet("/UpdateEmpServlet")
public class UpdateEmpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获得输出流
		PrintWriter out = response.getWriter();
		
		//获得表单数据
		String empId = request.getParameter("empId");
		String empName = request.getParameter("empName");
		String empLoginName = request.getParameter("empLoginName");
		String empPwd = request.getParameter("empPwd");
		String empEmail = request.getParameter("empEmail");
		String empPhone = request.getParameter("empPhone");
		String empHiredate = request.getParameter("empHiredate");
		String empSalary = request.getParameter("empSalary");
		String deptId = request.getParameter("deptId");
		String jobId = request.getParameter("jobId");
		String empInfo = request.getParameter("empInfo");
		
		//封装为pojo对象
		Dept dept = new Dept();
		dept.setDeptId(Integer.parseInt(deptId));
		
		Job job = new Job();
		job.setJobId(Integer.parseInt(jobId));
		
		Emp emp = new Emp(Integer.parseInt(empId),empName,empLoginName,empPwd,empEmail,empPhone,Date.valueOf(empHiredate),
				Double.parseDouble(empSalary),dept,job,null,empInfo);
		
		//调用业务功能
		EmpService service = new EmpService();
		
		try {
			service.updateEmp(emp);
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
