package com.yc.biz;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Subgroup;
import com.yc.dao.SubGroupDao;

public class SubGroupBiz {
	SubGroupDao dao=new SubGroupDao();
	public List<Subgroup> findSubgroup(Subgroup t) throws Exception{
		return dao.findSubgroup(t);
	}
	// 删除分组
		public int delSubGroup(Subgroup t) throws Exception {
			return dao.delSubgroup(t);
		}

		// 分组重命名
		public int rnameSubGroup(Subgroup t) throws Exception {
			return dao.rNameSubgroup(t);
		}

		// 分组重命名
		public int addSubGroup(Subgroup t) throws Exception {
			return dao.addSubgroup(t);
		}
		public List<Subgroup> findByName(Subgroup t) throws Exception{
			return dao.findByName(t);
		}
}
