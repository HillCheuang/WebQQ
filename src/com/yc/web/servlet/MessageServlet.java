package com.yc.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.yc.bean.FGcommon;
import com.yc.bean.Message;
import com.yc.bean.MessageGroupVo;
import com.yc.bean.QQInfo;
import com.yc.biz.MessageBiz;
import com.yc.biz.MessageGroupVoBiz;

@WebServlet("/message.action")
public class MessageServlet extends BaseServlet{

	MessageBiz biz = new MessageBiz();
	MessageGroupVoBiz voBiz = new MessageGroupVoBiz();
	/**
	 * 
	 */
	private static final long serialVersionUID = 4692621578098195428L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if(op.equals("checkChatLog")){
			doCheckChatLog(request,response);
		}else if(op.equals("checkChateGroupLog")){
			docheckChateGroupLog(request,response);
		}else if("findByMessage".equals(op)){
			doFindByMessage(request,response);
		}else if("updateRead".equals(op)){
			doRead(request,response);	//将消息设置为已读
		}else if("findUnread".equals(op)){
			doFindUnerad(request,response);
		}else if("setGroupIsRead".equals(op)){
			doReadGroupIsRead(request,response);
		}
	}
	/**
	 * 设置群消息已读
	 * @param request
	 * @param response
	 */
	private void doReadGroupIsRead(HttpServletRequest request, HttpServletResponse response) {
		Integer m_id =Integer.parseInt(request.getParameter("id")); 
		String qq =request.getParameter("qq");
		String gqq = request.getParameter("groupqq");
		Message message =new Message();
		message.setM_groupqq(gqq);
		try {
			List<Message> findByTrem = biz.findByTrem(message);
			System.out.println("111"+findByTrem);
			for(Message m:findByTrem){
				
				String isread = m.getIsread();
				if(isread==null){
					isread=qq;
				}
				else if (isread.contains(qq)) {
					continue;
				}
				else if(isread.equals("0")){
					isread = qq;
				}else{
					isread = isread+","+qq;
				}
				System.out.println(isread);
				
				Message mm =new Message();
				mm.setM_id(m.getM_id());
				mm.setIsread(isread);
				int update = biz.setIsRead(mm);
				toPrintJson(response, update);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 查询未读的消息
	 * @param request
	 * @param response
	 */
	private void doFindUnerad(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String qq = request.getParameter("qq");
		try {
			int unread =biz.findNumberOfUnderRead(qq);
			System.err.println(unread);
			toPrintJson(response, unread);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 将消息设置为已读
	 * @param request
	 * @param response
	 */
private void doRead(HttpServletRequest request, HttpServletResponse response) {
		int id =Integer.parseInt( request.getParameter("id"));
		try {
			int i =biz.read(id);
			toPrintJson(response, i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
/**
 * 群聊信息
 * @param request
 * @param response
 */
	private void docheckChateGroupLog(HttpServletRequest request, HttpServletResponse response) {
		String groupqq=request.getParameter("gourpqq");
		try {
			List<MessageGroupVo> messages = voBiz.findMessageByGroup(groupqq);
			System.out.println(messages);
			toPrintJson(response, messages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doCheckChatLog(HttpServletRequest request, HttpServletResponse response) {
		String qq = request.getParameter("qq");	//本机的qq
		
		String fqq= request.getParameter("fqq");	//对方的qq
		String groupqq=request.getParameter("groupqq");	//群聊号码
		Message msg = new Message(qq,fqq,groupqq);
		try {
			List<Message>  messages = biz.findByTrem(msg);
			System.out.println("~~~"+messages);
			toPrintJson(response, messages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void doFindByMessage(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			HttpSession session=req.getSession();
			QQInfo b=(QQInfo)session.getAttribute("qqInfo");
			List<FGcommon> fGcommons=biz.findByMessage(b);
			System.out.println(fGcommons);
			toPrintJson(resp, fGcommons);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
