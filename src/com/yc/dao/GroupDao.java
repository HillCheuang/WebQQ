package com.yc.dao;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.GroupTable;
import com.yc.bean.GroupVo;
import com.yc.bean.UserGroup;
import com.yc.commons.DbHelper;

public class GroupDao implements BaseDAO<GroupTable> {
	DbHelper db=new DbHelper();
	
	
	/* 创建群
	 * @see com.yc.dao.BaseDAO#add(java.lang.Object)
	 */
	@Override
	public int add(GroupTable t) throws Exception {
		String sql="insert into grouptable values(?,?,?,?,?)";
		return db.update(sql, t.getGno(),t.getGname(),t.getGdate(),t.getBuilder(),t.getGoupheadphoto());
	}

	/* 查找群
	 * @see com.yc.dao.BaseDAO#findByTrem(java.lang.Object)
	 */
	@Override
	public List<GroupTable> findByTrem(GroupTable t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select gno,gname,gdate,builder,goupheadphoto from grouptable where 1=1 ");
		List<Object> params=null;
		if(t!=null){
			params=new ArrayList<Object>();
			if(t.getGno()!=null){
				sb.append("and gno=? ");
				params.add(t.getGno());
			}if(t.getGname()!=null){
				sb.append("and gname=? ");
				params.add(t.getGname());
			}if(t.getGdate()!=null){
				sb.append("and gdate=? ");
				params.add(t.getGdate());
			}if(t.getBuilder()!=null){
				sb.append("and builder=? ");
				params.add(t.getBuilder());
			}
		}
		return db.findMutil(sb.toString(), params,GroupTable.class);
	}

	
	
	
	public List<GroupTable> findByGnoOrGname(GroupTable t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select gno,gname,gdate,builder,goupheadphoto from grouptable  ");
		List<Object> params=null;
		if(t!=null){
			params=new ArrayList<Object>();
			if(t.getGno()!=null){
				sb.append("where gno=? or gname=? ");
				params.add(t.getGno());
				params.add(t.getGname());
			}
		}
		return db.findMutil(sb.toString(), params,GroupTable.class);
	}
	
	
	@Override
	public int update(GroupTable t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(GroupTable t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	/* 查找群用户信息
	 * @see com.yc.dao.BaseDAO#findByTrem(java.lang.Object)
	 */
	public List<UserGroup> findByTrem(UserGroup t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select ugno,qq,gno,status, from user_group where 1=1 ");
		List<Object> params=null;
		if(t!=null){
			params=new ArrayList<Object>();
			if(t.getUgno()!=null){
				sb.append("and ugno=? ");
				params.add(t.getUgno());
			}if(t.getQq()!=null){
				sb.append("and qq=? ");
				params.add(t.getQq());
			}if(t.getGno()!=null){
				sb.append("and gno=? ");
				params.add(t.getGno());
			}if(t.getStatus()!=null){
				sb.append("and status=? ");
				params.add(t.getStatus());
			}
		}
		return db.findMutil(sb.toString(), params,UserGroup.class);
	}
	
	/**
	 * 查找用户加入的群（grouptable，user_group）
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<GroupVo> findGroupVo(GroupVo t) throws Exception{
		StringBuffer sb=new StringBuffer();
		sb.append("select u.ugno,u.qq,u.gno,u.status,g.gname,g.gdate,g.builder,g.goupheadphoto from user_group u left join grouptable g on u.gno=g.gno where u.qq=? ");
		List<Object> params=new ArrayList<>();
		params.add(t.getQq());
		
		return db.findMutil(sb.toString(), params, GroupVo.class);

	}
}
