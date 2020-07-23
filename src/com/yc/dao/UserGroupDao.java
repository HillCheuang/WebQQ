package com.yc.dao;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.UserGroup;
import com.yc.commons.DbHelper;

public class UserGroupDao implements BaseDAO<UserGroup>{
	DbHelper db=new DbHelper();
	@Override
	public int add(UserGroup t) throws Exception {
		String sql="insert into user_group values(?,?,?,?)";
		return db.update(sql, t.getUgno(),t.getQq(),t.getGno(),t.getStatus());
	}

	@Override
	public List<UserGroup> findByTrem(UserGroup t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select ugno,qq,gno,status from user_group where 1=1 ");
		List<Object> params=null;
		if(t!=null){
			params=new ArrayList<Object>();
			if(t.getUgno()!=null){
				sb.append(" and ugno=? ");
				params.add(t.getUgno());
			}
			if(t.getQq()!=null){
				sb.append(" and qq=? ");
				params.add(t.getQq());
			}if(t.getGno()!=null){
				sb.append(" and gno=? ");
				params.add(t.getGno());
			}if(t.getStatus()!=null){
				sb.append(" and status=? ");
				params.add(t.getStatus());
			}
		}
		return db.findMutil(sb.toString(), params, UserGroup.class);
	}

	@Override
	public int update(UserGroup t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(UserGroup t) throws Exception {
		String sql="delete from user_group where gno=? and qq=? ";
		return db.update(sql, t.getGno(),t.getQq());
	}
	
	

}
