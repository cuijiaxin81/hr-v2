package com.icss.hr.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icss.hr.common.Pager;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;

/**
 * 分页查询员工数据
 */
@WebServlet("/QueryEmpServlet")
public class QueryEmpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//输出流
		PrintWriter out = response.getWriter();
		
		//获得页码和每页多少条
		String pageNumStr = request.getParameter("pageNum");
		
		int pageNum = 1;
		
		try {
			pageNum = Integer.parseInt(pageNumStr);
		} catch (NumberFormatException e) {			
			
		}
		
		String pageSizeStr = request.getParameter("pageSize");
		
		int pageSize = 10;
		
		try {
			pageSize = Integer.parseInt(pageSizeStr);
		} catch (NumberFormatException e) {
			
		}
		
		//调用业务功能
		EmpService service = new EmpService();
		
		try {
			//创建分页对象
			Pager pager = new Pager(service.getEmpCount(), pageSize, pageNum);
			
			//当前页的数据
			List<Emp> list = service.queryEmpByPage(pager);
			
			//用Map集合存储两项数据
			HashMap<String, Object> map = new HashMap<>();
			map.put("pager", pager);
			map.put("list", list);
			
			//响应json数据到客户端
			Gson gson = new GsonBuilder()  
					  .setDateFormat("yyyy-MM-dd")  
					  .create();
			
			out.print(gson.toJson(map));
			
		} catch (Exception e) {			
			e.printStackTrace();
		}	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
