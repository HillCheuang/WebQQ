package com.yc.dao;

import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Params;
import com.yc.bean.MessageGroupVo;
import com.yc.commons.DbHelper;

public class MessageGroupVoDao implements BaseDAO<MessageGroupVo> {

	DbHelper db = new DbHelper();
	@Override
	public int add(MessageGroupVo t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MessageGroupVo> findByTrem(MessageGroupVo t) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from  (select a.*,u.* from (select m_id,m.m_date date,m.m_groupqq groupqq,m.m_content content,isread,m_type ,g.gname groupname,g.goupheadphoto groupheadphoto,  m.m_qq mqq from message  m inner join  grouptable g on   g.gno = m.m_groupqq)a inner join user_group u where u.gno = a.groupqq and mqq =u.qq)q ");
		sb.append("inner join qqinfo c on    c.qq = q.qq where 1 =1 ");
		List<Object> params =null;
		if(t!=null){
			params = new ArrayList<>();
			if(t.getGroupqq()!=null){
				sb.append(" and groupqq= ?");
				params.add(t.getGroupqq());
			}
			
		}
		sb.append("order by date asc");
		return db.findMutil(sb.toString(), params,MessageGroupVo.class);
	}

	@Override
	public int update(MessageGroupVo t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(MessageGroupVo t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
		
}
