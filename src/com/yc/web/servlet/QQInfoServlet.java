package com.yc.web.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.Friends;
import com.yc.bean.FriendsVo;
import com.yc.bean.QQInfo;
import com.yc.bean.Subgroup;
import com.yc.biz.QQInfoBiz;
import com.yc.biz.SubGroupBiz;
import com.yc.util.QQUtil;

@WebServlet("/qqinfo.action")
public class QQInfoServlet extends BaseServlet {

	private QQInfoBiz biz = new QQInfoBiz();
	private SubGroupBiz sbiz = new SubGroupBiz();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setCharacterEncoding(req, resp);
		String op = req.getParameter("op");
		if ("login".equals(op)) {
			doLogin(req, resp);
		} else if ("reg".equals(op)) {
			doReg(req, resp);
		} else if ("find".equals(op)) {
			doFind(req, resp);
		} else if ("check".equals(op)) {
			doCheck(req, resp);
		} else if ("findDisAgree".equals(op)) {
			dofindDisAgree(req, resp);
		} else if ("findheadphoto".equals(op)) {
			dofindHeadPhoto(req, resp);
		} else if ("changeDetail".equals(op)) {
			dochangeDetail(req, resp);
		} else if ("showDetail".equals(op)) {
			doshowDetail(req, resp);
		}else if("exit".equals(op)){
			doExit(req,resp);
		}
	}
	
	/**
	 * 退出
	 * @param req
	 * @param resp
	 */
	private void doExit(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().removeAttribute("qqinfo");
	}

	/**
	 * 显示详细信息
	 * 
	 * @param req
	 * @param resp
	 */
	private void doshowDetail(HttpServletRequest req, HttpServletResponse resp) {
		try {
			// QQInfo bean = this.parseRequest(req, QQInfo.class);
			HttpSession session = req.getSession();
			QQInfo a = (QQInfo) session.getAttribute("qqInfo");

			toPrintJson(resp, a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改详细信息
	 * 
	 * @param req
	 * @param resp
	 */
	private void dochangeDetail(HttpServletRequest req, HttpServletResponse resp) {
		try {
			
			QQInfo bean = this.parseRequest(req, QQInfo.class);
			System.out.println(bean);
			int i = biz.updateQQinfo(bean);
			QQInfo qqInfo = biz.login(bean);
			HttpSession session = req.getSession();
			session.setAttribute("qqInfo", qqInfo);
			toPrintJson(resp, i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void dofindHeadPhoto(HttpServletRequest req, HttpServletResponse resp) {
		try {
			QQInfo bean = parseRequest(req, QQInfo.class);
			QQInfo qqInfo = null;
			List<QQInfo> qqInfos = biz.findByTerm(bean);
			if (qqInfos != null && !qqInfos.isEmpty()) {
				qqInfo = qqInfos.get(0);
			}
			toPrintJson(resp, qqInfo);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void dofindDisAgree(HttpServletRequest req, HttpServletResponse resp) {

		try {
			HttpSession session = req.getSession();
			QQInfo qqInfo = (QQInfo) session.getAttribute("qqInfo");
			Friends bean = new Friends();
			if (qqInfo != null) {
				bean.setQq(qqInfo.getQq());
				bean.setIsagree(4);
				List<FriendsVo> list = biz.dofindDisAgree(bean);
				System.out.println(list);
				toPrintJson(resp, list);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 检查是否登录
	 * 
	 * @param req
	 * @param resp
	 */
	private void doCheck(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session = req.getSession();
			QQInfo qqInfo = (QQInfo) session.getAttribute("qqInfo");
			toPrintJson(resp, qqInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查找好友
	 * 
	 * @param req
	 * @param resp
	 */
	private void doFind(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session = req.getSession();
			QQInfo qqInfo = (QQInfo) session.getAttribute("qqInfo");
			QQInfo bean = parseRequest(req, QQInfo.class);
			List<QQInfo> list = biz.findFriends(bean);
			for(int i=0;i<list.size();i++){
				if(list.get(i).getQq().equals(qqInfo.getQq())){
					list.remove(i);
				}
			}
			toPrintJson(resp, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 注册操作
	 * 
	 * @param req
	 * @param resp
	 */
	private void doReg(HttpServletRequest req, HttpServletResponse resp) {
		try {
			QQInfo bean = parseRequest(req, QQInfo.class);
			// 随机生成QQ号码
			boolean flag = true;
			String qq = null;
			while (flag) {
				qq = QQUtil.getRandomQQ();
				QQInfo s = new QQInfo();
				s.setQq(qq);
				if (biz.login(s) == null) {
					bean.setQq(qq);
					flag = false;
				}
			}
			int i = biz.add(bean);

			if (i > 0) {
				Subgroup subgroup = new Subgroup();
				subgroup.setSname("我的好友");
				subgroup.setQq(qq);
				int j = sbiz.addSubGroup(subgroup);
				toPrintJson(resp, qq);
			} else {
				toPrintJson(resp, 0);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 登陆
	 * 
	 * @param req
	 * @param resp
	 */
	private void doLogin(HttpServletRequest req, HttpServletResponse resp) {
		try {
			QQInfo bean = parseRequest(req, QQInfo.class);
			QQInfo qqInfo = biz.login(bean);
			System.out.println(qqInfo);
			HttpSession session = req.getSession();
			if (null == qqInfo) {
				toPrintJson(resp, 0);
			} else {
				// 存储登录用户
				session.setAttribute("qqInfo", qqInfo);
				toPrintJson(resp, 1);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
