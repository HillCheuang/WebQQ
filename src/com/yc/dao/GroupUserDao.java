package com.yc.dao;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.GroupUser;
import com.yc.commons.DbHelper;

public class GroupUserDao implements BaseDAO<GroupUser>{

	DbHelper db = new DbHelper();
	@Override
	public int add(GroupUser t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GroupUser> findByTrem(GroupUser t) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from (select g.gno gno,gname,goupheadphoto,qq from grouptable g inner join user_group u on  g.gno = u.gno )a ");
		sb.append("inner join qqinfo q on q.qq = a.qq where 1 =1 ");
		List<Object> params = null;
		if(t!=null){
			params  = new ArrayList<Object>();
			if(t.getGno()!=null){
				sb.append(" and gno = ?");
				params.add(t.getGno());
			}
		}
		return db.findMutil(sb.toString(), params, GroupUser.class);
	}

	@Override
	public int update(GroupUser t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(GroupUser t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
