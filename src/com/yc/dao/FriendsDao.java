package com.yc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.bean.Friends;
import com.yc.bean.FriendsVo;
import com.yc.bean.QQInfo;
import com.yc.commons.DbHelper;

public class FriendsDao implements BaseDAO<Friends>{
	DbHelper db=new DbHelper();
	
	/* 添加好友信息
	 * @see com.yc.dao.BaseDAO#add(java.lang.Object)
	 */
	@Override
	public int add(Friends t) throws Exception {
		String sql="insert into friends values(?,?,?,?,?,?,?,?);";
		return db.update(sql, t.getFno(),t.getFqq(),t.getQq(),t.getSno(),t.getAddtime(),t.getFstatus(),t.getSend(),t.getIsagree());
	}
	
	
	/*查询好友信息
	 * @see com.yc.dao.BaseDAO#findByTrem(java.lang.Object)
	 */
	@Override
	public List<Friends> findByTrem(Friends t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select fno,fqq,qq,sno,addtime,fstatus,send,"+
		"isagree from friends where 1=1 ");
		List<Object> params=null;
		if(t!=null){
			params=new ArrayList<Object>();
			if(t.getFno()!=null){
				sb.append("and fno=? ");
				params.add(t.getFno());
			}if(t.getFqq()!=null){
				sb.append("and fqq=? ");
				params.add(t.getFqq());
			}if(t.getQq()!=null){
				sb.append("and qq=? ");
				params.add(t.getQq());
			}if(t.getSno()!=null){
				sb.append("and sno=? ");
				params.add(t.getSno());
			}if(t.getAddtime()!=null){
				sb.append("and addtime=? ");
				params.add(t.getAddtime());
			}if(t.getFstatus()!=null){
				sb.append("and fstatus=? ");
				params.add(t.getFstatus());
			}if(t.getSend()!=null){
				sb.append("and send=? ");
				params.add(t.getSend());
			}if(t.getIsagree()!=null){
				sb.append("and isagree=? ");
				params.add(t.getIsagree());
			}
		}
		return db.findMutil(sb.toString(), params, Friends.class);
	}
	
	
	public FriendsVo findFriendVo(FriendsVo t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select f.fno,f.fqq,f.qq,f.sno,f.addtime,f.fstatus,f.send,"+
		"f.isagree,q.qq, q.nickname,q.pwd,q.email,q.phonenum,q.question,q.answer,"+
		"q.headphoto,q.qstatus,q.isremainpwd,q.sex,q.bloodType,q.signature,q.job,q.company,addr,"+
		"q.hometown from  qqinfo q left join friends f on f.fqq=q.qq where f.qq=? and f.fqq =?");
		List<Object> params=new ArrayList<>();
		params.add(t.getFqq());
		params.add(t.getQq());
		return db.findMutil(sb.toString(), params, FriendsVo.class).get(0);
	}
	
	public FriendsVo findNotic(FriendsVo t) throws Exception{
		StringBuffer sb=new StringBuffer();
		sb.append("select f.fno,f.fqq,f.qq,f.sno,f.addtime,f.fstatus,f.send,"+
		"f.isagree,q.qq, q.nickname,q.pwd,q.email,q.phonenum,q.question,q.answer,"+
		"q.headphoto,q.qstatus,q.isremainpwd,q.sex,q.bloodType,q.signature,q.job,q.company,addr,"+
		"q.hometown from  qqinfo q left join friends f on f.fqq=q.qq where f.qq=? and f.fqq=?");
		List<Object> params=new ArrayList<>();
		params.add(t.getQq());
		params.add(t.getFqq());
		return db.findMutil(sb.toString(), params, FriendsVo.class).get(0);
	}
 

	/* 根据好友qq更新好友数据
	 * @see com.yc.dao.BaseDAO#update(java.lang.Object)
	 */
	@Override
	public int update(Friends t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("update friends set ");
		List<Object> params=null;
		if(t!=null){
			params=new ArrayList<Object>();
			if(t.getFno()!=null){
				sb.append("fno=?,");
				params.add(t.getFno());
			}if(t.getQq()!=null){
				sb.append("qq=?,");
				params.add(t.getQq());
			}if(t.getSno()!=null){
				sb.append("sno=?,");
				params.add(t.getSno());
			}if(t.getAddtime()!=null){
				sb.append("addtime=?,");
				params.add(t.getAddtime());
			}if(t.getFstatus()!=null){
				sb.append("fstatus=?,");
				params.add(t.getFstatus());
			}if(t.getSend()!=null){
				sb.append("send=?,");
				params.add(t.getSend());
			}if(t.getIsagree()!=null){
				sb.append("isagree=?,");
				params.add(t.getIsagree());
			}
		}
		sb.append(" fqq=? where fqq=?");
		params.add(t.getFqq());
		params.add(t.getFqq());
		if(t.getQq()!=null){
			sb.append(" and qq=?");
			params.add(t.getQq());
		}
		System.out.println(sb.toString());
		List<String> sqls=new ArrayList<>();
		sqls.add(sb.toString());
		
		List<List<Object>> list=new ArrayList<>();
		list.add(params);
		
		return db.update(sqls, list);
	}

	/* 根据fqq删除好友信息
	 * @see com.yc.dao.BaseDAO#delete(java.lang.Object)
	 */
	@Override
	public int delete(Friends t) throws Exception {
		String sql="delete from friends where fqq=?";
		
		return db.update(sql, t.getFqq());
	}
	
	public int ChangSubGroup(Friends t) throws SQLException {
		StringBuffer sb=new StringBuffer();
		sb.append("update friends set sno=? where qq=? and fqq=? ");
		List<Object> params=new ArrayList<>();
		params.add(t.getSno());
		params.add(t.getQq());
		params.add(t.getFqq());
		
		List<String> sqls=new ArrayList<>();
		sqls.add(sb.toString());
		
		List<List<Object>> list=new ArrayList<>();
		list.add(params);
		
		return db.update(sqls, list);
	}

}
