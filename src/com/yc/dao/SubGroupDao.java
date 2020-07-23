package com.yc.dao;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Subgroup;
import com.yc.commons.DbHelper;

public class SubGroupDao {
	DbHelper db=new DbHelper();
	public List<Subgroup> findSubgroup(Subgroup t) throws Exception{
		String sql="select sno,sname,qq from subgroup where qq=?";
		List<Object> parmas=new ArrayList<Object>();
		parmas.add(t.getQq());
		return db.findMutil(sql, parmas, Subgroup.class);
	}
	// 删除分组
		public int delSubgroup(Subgroup t) throws Exception {
			String sql = "delete from subgroup where sno=?";
			return db.update(sql, t.getSno());
		}

		// 分组重命名
		public int rNameSubgroup(Subgroup t) throws Exception {
			String sql = "update subgroup set sname=? where sno=?";
			return db.update(sql, t.getSname(), t.getSno());
		}

		// 创建分组
		public int addSubgroup(Subgroup t) throws Exception {
			String sql = "insert into subgroup values(null,?,?)";
			return db.update(sql, t.getSname(), t.getQq());
		}
		public List<Subgroup> findByName(Subgroup t) throws Exception{
			String sql="select sno,sname,qq from subgroup where qq=? and sname=? ";
			List<Object> parmas=new ArrayList<Object>();
			parmas.add(t.getQq());
			parmas.add(t.getSname());
			return db.findMutil(sql, parmas, Subgroup.class);
		}
}
