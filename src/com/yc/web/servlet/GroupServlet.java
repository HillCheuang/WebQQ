

package com.yc.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.yc.bean.GroupTable;
import com.yc.bean.GroupVo;
import com.yc.bean.QQInfo;
import com.yc.bean.Subgroup;
import com.yc.bean.UserGroup;
import com.yc.biz.GroupBiz;
import com.yc.biz.SubGroupBiz;
import com.yc.biz.UserGroupBiz;
import com.yc.util.QQUtil;
@WebServlet("/groups.action")
public class GroupServlet extends BaseServlet {

	GroupBiz biz = new GroupBiz();
	SubGroupBiz sbiz = new SubGroupBiz();
	UserGroupBiz ugbiz=new UserGroupBiz();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		setCharacterEncoding(req, resp);
		String op = req.getParameter("op");
		if ("find".equals(op)) {
			doFind(req, resp);
		}else if ("Rname".equals(op)) {
			doRname(req, resp);
		} else if ("AddSubGroup".equals(op)) {
			doAddSubGroup(req, resp);
		}else if ("delSubGroup".equals(op)) {
			delSubGroup(req, resp);
		}else if ("findSubgroup".equals(op)) {
			doFinderSubGroup(req, resp);
		}else if ("findBySnoOrSname".equals(op)) {
			doFindBySnoOrSname(req, resp);
		}else if ("creatGroup".equals(op)) {
			doCreatGroup(req, resp);
		}else if ("joinGroup".equals(op)) {
			doJoinGroup(req, resp);
		}else if ("delGroup".equals(op)) {
			doDelGroup(req, resp);
		}

	}

	
	private void doDelGroup(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			HttpSession session = req.getSession();
			QQInfo bean = (QQInfo) session.getAttribute("qqInfo");
			UserGroup userGroup = parseRequest(req, UserGroup.class);
			userGroup.setQq(bean.getQq());
			System.out.println(userGroup);
			int i=ugbiz.delUserGroup(userGroup);
			toPrintJson(resp, i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	private void doJoinGroup(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session = req.getSession();
			QQInfo bean = (QQInfo) session.getAttribute("qqInfo");
			
			
			UserGroup userGroup=parseRequest(req, UserGroup.class);
			userGroup.setQq(bean.getQq());
			//判断是否已经加入该群
			List<UserGroup> list=ugbiz.findGroup(userGroup);
			if(list!=null&&!list.isEmpty()){
				toPrintJson(resp, 2);
			}else{
				int i=ugbiz.update(userGroup);
				toPrintJson(resp,i);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void doCreatGroup(HttpServletRequest req, HttpServletResponse resp) {
		try {
			
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	String gdate=format.format(new Date());
			HttpSession session = req.getSession();
			QQInfo bean = (QQInfo) session.getAttribute("qqInfo");
			GroupTable groupTable=parseRequest(req, GroupTable.class);
			groupTable.setBuilder(bean.getQq());
			groupTable.setGdate(gdate);
			boolean flag=true;
			String gno=null;
			while (flag) {
				gno=QQUtil.getRandomGno();
				GroupTable s=new GroupTable();
				s.setGno(gno);;
				if(biz.findGroup(s)==null||biz.findGroup(s).isEmpty()){
					groupTable.setGno(gno);;
					flag=false;
				}
			} 
			System.out.println(groupTable);
			int i=biz.createGroup(groupTable);
			UserGroup gTable=new UserGroup();
			gTable.setGno(gno);;
			gTable.setQq(bean.getQq());
			gTable.setStatus(1);
			int j=ugbiz.update(gTable);
			
			toPrintJson(resp, i);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void doFindBySnoOrSname(HttpServletRequest req, HttpServletResponse resp) {
		try {
			GroupTable groupTable=parseRequest(req, GroupTable.class);
			groupTable.setGname(groupTable.getGno());
			List<GroupTable> list=biz.findByGnoOrGname(groupTable);
			System.out.println(list);
			toPrintJson(resp, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void doFinderSubGroup(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			HttpSession session = req.getSession();
			QQInfo bean = (QQInfo) session.getAttribute("qqInfo");
			Subgroup subgroup=new Subgroup();
			subgroup.setQq(bean.getQq());
			List<Subgroup> subgroups=sbiz.findSubgroup(subgroup);
			toPrintJson(resp, subgroups);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	//删除分组
	private void delSubGroup(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Subgroup subgroup=parseRequest(req, Subgroup.class);
			int i=sbiz.delSubGroup(subgroup);
			toPrintJson(resp, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 添加分组
	private void doAddSubGroup(HttpServletRequest req, HttpServletResponse resp) {
		try {
			GroupVo vo = new GroupVo();
			HttpSession session = req.getSession();
			QQInfo bean = (QQInfo) session.getAttribute("qqInfo");
			Subgroup subgroup=parseRequest(req, Subgroup.class);
			subgroup.setQq(bean.getQq());
			int i=sbiz.addSubGroup(subgroup);
			toPrintJson(resp, i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// 重命名
	private void doRname(HttpServletRequest req, HttpServletResponse resp) {
		try {

			Subgroup subgroup = parseRequest(req, Subgroup.class);

			int i = sbiz.rnameSubGroup(subgroup);
			toPrintJson(resp, i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	private void doFind(HttpServletRequest req, HttpServletResponse resp) {
		try {
			GroupVo vo = new GroupVo();
			HttpSession session = req.getSession();
			QQInfo bean = (QQInfo) session.getAttribute("qqInfo");
			vo.setQq(bean.getQq());
			List<GroupVo> list = biz.findGroupVo(vo);
			toPrintJson(resp, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
