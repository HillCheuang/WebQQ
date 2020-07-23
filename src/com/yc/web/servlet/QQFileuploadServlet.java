package com.yc.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.javafx.fxml.BeanAdapter;
import com.yc.bean.Friends;
import com.yc.bean.QQInfo;
import com.yc.util.FileUploadUtil;
@WebServlet("/QQFileUpload.action")
public class QQFileuploadServlet extends BaseServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//解析请求对象  文件上传
		try {
			QQInfo bean=FileUploadUtil.parseRequest(request, QQInfo.class);
			System.out.println("图片路径："+bean.getHeadphoto());
			toPrintJson(response, bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
