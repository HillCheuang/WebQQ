package com.yc.biz;

import java.util.List;

import com.yc.bean.UserGroup;
import com.yc.dao.UserGroupDao;

public class UserGroupBiz {
	UserGroupDao dao=new UserGroupDao();
	public int update(UserGroup t) throws Exception{
		return dao.add(t);
	}
	
	public int delUserGroup(UserGroup t) throws Exception{
		return dao.delete(t);
	}
	public List<UserGroup> findGroup(UserGroup t) throws Exception {
		// TODO Auto-generated method stub
		return dao.findByTrem(t);
	}
}
