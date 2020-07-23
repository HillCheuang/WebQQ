package com.yc.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.GroupTable;
import com.yc.bean.QQInfo;
import com.yc.util.FileUploadUtil;
@WebServlet("/GroupFileUpload.action")
public class GroupFileuploadServlet extends BaseServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 解析请求对象 文件上传
		try {
			GroupTable bean = FileUploadUtil.parseRequest(request, GroupTable.class);
			System.out.println("图片路径：" + bean.getGoupheadphoto());
			toPrintJson(response, bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
