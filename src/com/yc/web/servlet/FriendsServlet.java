package com.yc.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.yc.bean.Friends;
import com.yc.bean.QQInfo;
import com.yc.bean.Subgroup;
import com.yc.biz.FriendsBiz;
import com.yc.biz.SubGroupBiz;

@WebServlet("/friends.action")
public class FriendsServlet extends BaseServlet{
	FriendsBiz biz=new FriendsBiz();
	SubGroupBiz SGBiz = new SubGroupBiz();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setCharacterEncoding(req, resp);
		String op=req.getParameter("op");
		if("find".equals(op)){
			doFind(req,resp);
		}else if("add".equals(op)){
			doAdd(req,resp);
		}else if ("agreeAsFriend".equals(op)) {
			doAgreeAsFriend(req,resp);
		}else if ("refuseAsFriend".equals(op)) {
			doRefuseAsFriend(req,resp);
		}else if ("changSubGroup".equals(op)) {
			doChangSubGroup(req,resp);
		}else if ("delFriend".equals(op)) {
			doDelFriend(req,resp);
		}
		
	}

	//删除好友
	private void doDelFriend(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session=req.getSession();
			QQInfo b=(QQInfo)session.getAttribute("qqInfo");
			Friends friends = parseRequest(req, Friends.class);
			friends.setQq(b.getQq());
			int i=biz.delectFriend(friends);
			Friends friends2=new Friends();
			friends2.setFqq(b.getQq());
			friends2.setQq(friends.getFqq());
			int j=biz.delectFriend(friends2);
			toPrintJson(resp, i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	//改变好友分组
	private void doChangSubGroup(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			HttpSession session=req.getSession();
			QQInfo b=(QQInfo)session.getAttribute("qqInfo");
			Friends friends = parseRequest(req, Friends.class);
			friends.setQq(b.getQq());
			int i=biz.ChangSubGroup(friends);
			toPrintJson(resp, i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}




	/**
	 * 拒绝好友情求
	 * @param req
	 * @param resp
	 */
	private void doRefuseAsFriend(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Friends friends = parseRequest(req, Friends.class);
			friends.setIsagree(2);
			int i=biz.updateFriend(friends);
			
			Friends friends2=new Friends();
			friends2.setFqq(friends.getQq());
			friends2.setQq(friends.getFqq());
			friends2.setIsagree(2);
			
			int j=biz.updateFriend(friends2);
			
			toPrintJson(resp, i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	/**
	 * 同意好友情求
	 * @param req
	 * @param resp
	 */
	private void doAgreeAsFriend(HttpServletRequest req, HttpServletResponse resp) {
		 
		try {
			Friends friends = parseRequest(req, Friends.class);
			
			friends.setIsagree(1);
			int i=biz.updateFriend(friends);
			Friends friends2=new Friends();
			
			friends2.setFqq(friends.getQq());
			friends2.setQq(friends.getFqq());
			friends2.setIsagree(1);
			int j=biz.updateFriend(friends2);
			toPrintJson(resp, i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	/**
	 * 添加好友设置默认状态为0(表示还未同意)
	 * @param req
	 * @param resp
	 */
private void doAdd(HttpServletRequest req, HttpServletResponse resp) {
		
		
		try {
			HttpSession session=req.getSession();
			QQInfo b=(QQInfo)session.getAttribute("qqInfo");
			Friends friends=parseRequest(req, Friends.class);
			
			Subgroup s=new Subgroup();
			s.setQq(b.getQq());
			s.setSname("我的好友");
			
			Subgroup subgroup=SGBiz.findByName(s).get(0);
			System.out.println(subgroup);
			//判断是否已经是好友
			Friends friends3=new Friends();
			friends3.setFqq(friends.getFqq());
			friends3.setQq(b.getQq());
			Friends friends2=null;
			List<Friends> list=biz.findFriend(friends3);
			if(list==null||list.isEmpty()){
				friends.setQq(b.getQq());
				friends.setIsagree(3);
				friends.setSno(subgroup.getSno());
				friends2=new Friends();
				friends2.setFqq(friends.getQq());
				friends2.setQq(friends.getFqq());
				friends2.setIsagree(4);
				int z=biz.addFriend(friends2);
				int i = biz.addFriend(friends);
				if(i!=0&&z!=0){
					 toPrintJson(resp, i);
				}
			}else if(list.get(0).getIsagree()==1){
				toPrintJson(resp, 2);
			}else if(list.get(0).getIsagree()==2){
				friends.setQq(b.getQq());
				friends.setIsagree(3);
				friends2=new Friends();
				friends2.setFqq(friends.getQq());
				friends2.setQq(friends.getFqq());
				friends2.setIsagree(4);
				int z=biz.updateFriend(friends2);
				int i = biz.updateFriend(friends);
				if(i!=0&&z!=0){
					 toPrintJson(resp, i);
				}
			}else if(list.get(0).getIsagree()==3) {
				 toPrintJson(resp, 3);
			}else if(list.get(0).getIsagree()==4) {
				 toPrintJson(resp, 4);
			}
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
	private void doFind(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session=req.getSession();
			QQInfo b=(QQInfo)session.getAttribute("qqInfo");
			Subgroup s=new Subgroup();
			s.setQq(b.getQq());
			List<Map<String,Object>> map=biz.findFriendBySub(s);
			toPrintJson(resp, map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
