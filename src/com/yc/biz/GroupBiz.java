package com.yc.biz;

import java.util.List;

import com.yc.bean.GroupTable;
import com.yc.bean.GroupVo;
import com.yc.dao.GroupDao;

public class GroupBiz {

	GroupDao dao = new GroupDao();

	public List<GroupVo> findGroupVo(GroupVo t) throws Exception {
		return dao.findGroupVo(t);
	}

	public List<GroupTable> findGroup(GroupTable t) throws Exception {
		return dao.findByTrem(t);
	}

	public List<GroupTable> findByGnoOrGname(GroupTable t) throws Exception {
		return dao.findByGnoOrGname(t);
	}

	public int createGroup(GroupTable t) throws Exception {

		return dao.add(t);
	}


}
