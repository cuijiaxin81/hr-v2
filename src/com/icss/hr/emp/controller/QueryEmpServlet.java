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
 * ��ҳ��ѯԱ������
 */
@WebServlet("/QueryEmpServlet")
public class QueryEmpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//�����
		PrintWriter out = response.getWriter();
		
		//���ҳ���ÿҳ������
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
		
		//����ҵ����
		EmpService service = new EmpService();
		
		try {
			//������ҳ����
			Pager pager = new Pager(service.getEmpCount(), pageSize, pageNum);
			
			//��ǰҳ������
			List<Emp> list = service.queryEmpByPage(pager);
			
			//��Map���ϴ洢��������
			HashMap<String, Object> map = new HashMap<>();
			map.put("pager", pager);
			map.put("list", list);
			
			//��Ӧjson���ݵ��ͻ���
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
