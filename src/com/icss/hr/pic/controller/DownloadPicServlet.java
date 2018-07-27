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
 * ����ͼƬ���ݵ��ͻ���
 */
@WebServlet("/DownloadPicServlet")
public class DownloadPicServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		//���ͼƬid
		String picId = request.getParameter("picId");
		
		//����ҵ����
		PicService service = new PicService();
		
		try {
			Pic pic = service.queryPicById(Integer.parseInt(picId));
			
			//�����ļ���ת��
			String filename = new String(pic.getPicName().getBytes("utf-8"),"iso-8859-1");
			
			//���ñ�ͷ��֪ͨ������Ը�����ʽ��������
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);
						
			//���ͼƬ������
			InputStream in = pic.getPicData();
			
			//�����
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