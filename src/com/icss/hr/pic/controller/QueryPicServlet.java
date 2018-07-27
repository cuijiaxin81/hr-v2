package com.icss.hr.pic.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import com.icss.hr.pic.pojo.Pic;
import com.icss.hr.pic.service.PicService;

/**
 * 查询公司图库
 */
@WebServlet("/QueryPicServlet")
public class QueryPicServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 输出流
		PrintWriter out = response.getWriter();

		// 获得页码和每页多少条
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
		PicService service = new PicService();
		
		try {
			Pager pager = new Pager(service.getPicCount(), pageSize, pageNum);
			
			List<Pic> list = service.queryPicByPage(pager);
			
			//用Map集合存储两项数据
			HashMap<String, Object> map = new HashMap<>();
			map.put("pager", pager);
			map.put("list", list);
			
			//响应json数据到客户端
			Gson gson = new GsonBuilder()  
					  .setDateFormat("yyyy-MM-dd")  
					  .create();
			
			out.print(gson.toJson(map));
		} catch (SQLException e) {			
			e.printStackTrace();
		}		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}