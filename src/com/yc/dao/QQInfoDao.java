package com.yc.dao;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.QQInfo;
import com.yc.commons.DbHelper;

public class QQInfoDao implements BaseDAO<QQInfo> {
	
	DbHelper db=new DbHelper();
	/* 
	 * 添加用户信息
	 * @see com.yc.dao.BaseDAO#add(java.lang.Object)
	 */
	@Override
	public int add(QQInfo t) throws Exception {
		String sql="insert into qqinfo values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		return db.update(sql,t.getQq(),t.getNickname(),t.getPwd(),t.getEmail(),t.getPhonenum(),t.getQuestion(),
				t.getAnswer(),t.getHeadphoto(),t.getQstatus(),t.getIsremainpwd(),t.getSex(),t.getBloodType(),
				t.getSignature(),t.getJob(),t.getCompany(),t.getAddr(),t.getHometown());
	}

	/* 查找用户信息
	 * @see com.yc.dao.BaseDAO#findByTrem(java.lang.Object)
	 */
	@Override
	public List<QQInfo> findByTrem(QQInfo t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select qq,nickname,pwd,email,phonenum,question,answer,"+
		"headphoto,qstatus,isremainpwd,sex,bloodType,signature,job,company,addr,"+
		"hometown from qqinfo where 1=1 ");
		List<Object> params=null;
		if(t!=null){
			params=new ArrayList<Object>();
			if(t.getQq()!=null){
				sb.append("and qq=? ");
				params.add(t.getQq());
			}if(t.getNickname()!=null){
				sb.append("and nickname=? ");
				params.add(t.getNickname());
			}if(t.getPwd()!=null){
				sb.append("and pwd=? ");
				params.add(t.getPwd());
			}if(t.getEmail()!=null){
				sb.append("and email=? ");
				params.add(t.getEmail());
			}if(t.getPhonenum()!=null){
				sb.append("and phonenum=? ");
				params.add(t.getPhonenum());
			}if(t.getQuestion()!=null){
				sb.append("and question=? ");
				params.add(t.getQuestion());
			}if(t.getAnswer()!=null){
				sb.append("and answer=? ");
				params.add(t.getAnswer());
			}if(t.getHeadphoto()!=null){
				sb.append("and headphoto=? ");
				params.add(t.getHeadphoto());
			}if(t.getQstatus()!=null){
				sb.append("and qstatus=? ");
				params.add(t.getQstatus());
			}if(t.getIsremainpwd()!=null){
				sb.append("and isremainpwd=? ");
				params.add(t.getIsremainpwd());
			}if(t.getSex()!=null){
				sb.append("and sex=? ");
				params.add(t.getSex());
			}if(t.getBloodType()!=null){
				sb.append("and bloodType=? ");
				params.add(t.getBloodType());
			}if(t.getSignature()!=null){
				sb.append("and signature=? ");
				params.add(t.getSignature());
			}if(t.getJob()!=null){
				sb.append("and job=? ");
				params.add(t.getJob());
			}if(t.getCompany()!=null){
				sb.append("and company=? ");
				params.add(t.getCompany());
			}if(t.getAddr()!=null){
				sb.append("and addr=? ");
				params.add(t.getAddr());
			}if(t.getHometown()!=null){
				sb.append("and hometown=? ");
				params.add(t.getHometown());
			}
		}
		return db.findMutil(sb.toString(), params, QQInfo.class);
		
	}

	/* 更新用户信息,只能根据QQ号码更新其他数据
	 * @see com.yc.dao.BaseDAO#update(java.lang.Object)
	 */
	@Override
	public int update(QQInfo t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("update qqinfo set ");
		List<Object> params=null;
		if(t!=null){
			params=new ArrayList<Object>();
			if(t.getNickname()!=null){
				sb.append("nickname=?,");
				params.add(t.getNickname());
			}if(t.getPwd()!=null){
				sb.append("pwd=?,");
				params.add(t.getPwd());
			}if(t.getEmail()!=null){
				sb.append("email=?,");
				params.add(t.getEmail());
			}if(t.getPhonenum()!=null){
				sb.append("phonenum=?,");
				params.add(t.getPhonenum());
			}if(t.getQuestion()!=null){
				sb.append("question=?,");
				params.add(t.getQuestion());
			}if(t.getAnswer()!=null){
				sb.append("answer=?,");
				params.add(t.getAnswer());
			}if(t.getHeadphoto()!=null){
				sb.append("headphoto=?,");
				params.add(t.getHeadphoto());
			}if(t.getQstatus()!=null){
				sb.append("qstatus=?,");
				params.add(t.getQstatus());
			}if(t.getIsremainpwd()!=null){
				sb.append("isremainpwd=?,");
				params.add(t.getIsremainpwd());
			}if(t.getSex()!=null){
				sb.append("sex=?,");
				params.add(t.getSex());
			}if(t.getBloodType()!=null){
				sb.append("bloodType=?,");
				params.add(t.getBloodType());
			}if(t.getSignature()!=null){
				sb.append("signature=?,");
				params.add(t.getSignature());
			}if(t.getJob()!=null){
				sb.append("job=?,");
				params.add(t.getJob());
			}if(t.getCompany()!=null){
				sb.append("and company=?,");
				params.add(t.getCompany());
			}if(t.getAddr()!=null){
				sb.append("and addr=?,");
				params.add(t.getAddr());
			}if(t.getHometown()!=null){
				sb.append("and hometown=?,");
				params.add(t.getHometown());
			}
		}
		sb.append("qq=? where qq=? ");
		params.add(t.getQq());
		params.add(t.getQq());
		List<String> sqls=new ArrayList<>();
		sqls.add(sb.toString());
		
		List<List<Object>> list=new ArrayList<>();
		list.add(params);
		
		return db.update(sqls, list);
		
	}

	/* 根据qq删除用户信息
	 * @see com.yc.dao.BaseDAO#delete(java.lang.Object)
	 */
	@Override
	public int delete(QQInfo t) throws Exception {
		String sql="delete from qqinfo where qq=?";
		
		return db.update(sql, t.getQq());
	}

	/**
	 * 查找好友
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<QQInfo> findFriends(QQInfo t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select qq,nickname,pwd,email,phonenum,question,answer,"+
		"headphoto,qstatus,isremainpwd,sex,bloodType,signature,job,company,addr,"+
		"hometown from qqinfo where 1=1 ");
		List<Object> params=null;
		if(t!=null){
			params=new ArrayList<Object>();
			if(t.getQq()!=null){
				sb.append("and qq=? or nickname=? ");
				params.add(t.getQq());
				params.add(t.getQq());
			}if(t.getSex()!=null){
				sb.append("and sex=? ");
				params.add(t.getSex());
			}if(t.getAddr()!=null){
				sb.append("and addr=? ");
				params.add(t.getAddr());
			}
		}
		return db.findMutil(sb.toString(), params, QQInfo.class);
	}

}
