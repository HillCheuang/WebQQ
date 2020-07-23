package com.yc.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.QQInfo;
import com.yc.biz.QQInfoBiz;

@WebServlet("/foget.action")
public class FogetPassServlet  extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QQInfoBiz biz=new QQInfoBiz();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setCharacterEncoding(req, resp);
		String op=req.getParameter("op");
		if("forgetpass".equals(op)){
			doForgetPass(req,resp);
		}else if("changePWD".equals(op)){
			dochangPWD(req,resp);
		}
	}

	/**
	 * 修改密码
	 * @param req
	 * @param resp
	 */
	private void dochangPWD(HttpServletRequest request, HttpServletResponse response) {
		QQInfo bean;
		try {
			bean = parseRequest(request, QQInfo.class);
			int i=biz.changePwd(bean);
			toPrintJson(response, i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param req
	 * @param resp
	 */
	private void doForgetPass(HttpServletRequest req, HttpServletResponse resp) {
		QQInfo bean;
		try {
			bean = parseRequest(req, QQInfo.class);
			QQInfo qqInfo=biz.login(bean);
			toPrintJson(resp, qqInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
		
	

}
