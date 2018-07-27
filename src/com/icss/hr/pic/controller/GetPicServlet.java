package com.icss.hr.pic.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.pic.pojo.Pic;
import com.icss.hr.pic.service.PicService;

/**
 * 响应二进制图片数据到客户端
 */
@WebServlet("/GetPicServlet")
public class GetPicServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		//获得图片id
		String picId = request.getParameter("picId");
		
		//调用业务功能
		PicService service = new PicService();
		
		try {
			Pic pic = service.queryPicById(Integer.parseInt(picId));
			
			//获得图片数据流
			InputStream in = pic.getPicData();
			
			//输出流
			OutputStream out = response.getOutputStream();
			
			byte[] b = new byte[1024 * 8];
			
			int len = in.read(b);
			
			while (len != -1) {
				out.write(b, 0, len);
				len = in.read(b);
			}
			
			in.close();
			out.close();
			
		} catch (NumberFormatException | SQLException e) {			
			e.printStackTrace();
		}

	}

}