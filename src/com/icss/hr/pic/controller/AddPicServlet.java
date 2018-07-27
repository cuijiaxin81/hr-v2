package com.icss.hr.pic.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.icss.hr.pic.pojo.Pic;
import com.icss.hr.pic.service.PicService;

/**
 * �ϴ�ͼ����Ƭ
 */
@WebServlet("/AddPicServlet")
public class AddPicServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();

		// ���Servlet�����Ķ���
		ServletContext context = this.getServletContext();

		// �����ļ��б�������
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// �����ڴ������С4KB
		factory.setSizeThreshold(4 * 1024);

		// �����ݴ�������ʱĿ¼�����ϴ��ļ��������õ��ڴ���Сʱ�����ݴ���������ת���ǳ���Ҫ��
		File temp = new File(context.getRealPath("/temp"));
		if (!temp.exists()) {
			temp.mkdirs();
		}		
		factory.setRepository(temp);

		// Servlet�ļ��ϴ�����
		ServletFileUpload upload = new ServletFileUpload(factory);

		// ����ϴ��б�
		try {
			// ��������ϴ��ļ���СΪ1000M��������С�ᷢ���쳣
			upload.setSizeMax(1024 * 1024 * 1000);

			// ��������ϴ��ļ����󼯺�
			List<FileItem> list = upload.parseRequest(request);

			// ����ļ�����������
			FileItem item = list.get(0);
			
			// ����ļ�����
			FileItem item2 = list.get(1);
			String picInfo = item2.getString();
			picInfo = new String(picInfo.getBytes("iso-8859-1"),"utf-8");
						
			// �ļ���С
			long picSize = item.getSize();

			// �ͻ����ļ�·��
			String fullName = item.getName();
			// ԭʼ�ļ�����
			String picName = fullName.substring(fullName.lastIndexOf("\\") + 1);
			
			//�û���¼��
			HttpSession session = request.getSession();
			String picAuthor = (String) session.getAttribute("empLoginName");
			
			//����ҵ����
			Pic pic = new Pic(picName, picInfo, picSize, picAuthor, item.getInputStream());
			
			PicService service = new PicService();
			
			service.addPic(pic);			

			// ɾ����ʱ�ļ�
			item.delete();			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}