package com.yc.biz;

import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.If;

import com.yc.bean.GroupUser;
import com.yc.dao.GroupUserDao;

public class GroupUserBiz {
	GroupUserDao dao = new GroupUserDao();
	
	public List<GroupUser> findByGroupno(String gno) throws Exception{
		GroupUser t = new GroupUser();
		t.setGno(gno);
		return dao.findByTrem(t);
	}
}
