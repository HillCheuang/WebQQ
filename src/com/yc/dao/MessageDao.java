package com.yc.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.org.apache.xpath.internal.operations.And;
import com.yc.bean.Friends;
import com.yc.bean.Message;
import com.yc.bean.QQInfo;
import com.yc.commons.DbHelper;

public class MessageDao implements BaseDAO<Message> {
	DbHelper db=new DbHelper();
	FriendsDao fdao = new FriendsDao();
	
	/**
	 * 将消息设置为已读
	 * @param m
	 * @return
	 */
	public int read(int id){
		String sql = "update message set isread = 1 where m_id=?";
		return db.update(sql,id);
	}
	
	/* 添加消息记录
	 * @see com.yc.dao.BaseDAO#add(java.lang.Object)
	 */
	@Override
	public int add(Message t) throws Exception {
		String sql="insert into message values(?,?,?,?,?,?,?,?,?,?);";
		/*Date date = new Date(t.getM_date());
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy/MM/dd HH:mm:ss秒");
		String time = simpleDateFormat.format(date);*/
		System.out.println(t.getM_date()+"111");
		return db.update(sql,null,t.getM_date(),t.getM_qq(),t.getM_fqq(),t.getM_content(),t.getM_groupqq(),t.getM_picture(),t.getM_file(),t.getIsread(),t.getM_type());
	}

	/* 查询消息
	 * @see com.yc.dao.BaseDAO#findByTrem(java.lang.Object)
	 */
	@Override
	public List<Message> findByTrem(Message t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select m_id,m_date,m_qq,m_fqq,m_content,m_groupqq,m_picture,m_file,isread,m_type "+
		" from message where 1=1 ");
		List<Object> params=null;
		if(t!=null){
			params=new ArrayList<Object>();
			if(t.getM_id()!=null){
				sb.append("and m_id=? ");
				params.add(t.getM_id());
			}if(t.getM_date()!=null){
				sb.append("and m_date=? ");
				params.add(t.getM_date());
			}if(t.getM_qq()!=null){
				sb.append("and m_qq =? and m_fqq=? ");
				params.add(t.getM_qq());
				params.add(t.getM_fqq());
			}if(t.getM_fqq()!=null){
				sb.append("or m_qq =? and m_fqq=? ");
				params.add(t.getM_fqq());
				params.add(t.getM_qq());
			}if(t.getM_content()!=null){
				sb.append("and m_content=? ");
				params.add(t.getM_content());
			}if(t.getM_groupqq()!=null){
				sb.append("and m_groupqq=? ");
				params.add(t.getM_groupqq());
			}if(t.getM_file()!=null){
				sb.append("and m_file=? ");
				params.add(t.getM_file());
			}if(t.getM_picture()!=null){
				sb.append("and m_picture=? ");
				params.add(t.getM_picture());
			}if(t.getIsread()!=null){
				sb.append("and isread=? ");
				params.add(t.getIsread());
			}
		}
		sb.append(" order by m_date asc ");
		System.out.println(sb.toString());
		return db.findMutil(sb.toString(), params,Message.class);
	}

	@Override
	public int update(Message t) throws Exception {
		System.err.println(t);
		String sql = "update message set isread=? where m_id = ?";
		List<Object> params = new ArrayList<>();
		return db.update(sql, t.getIsread(),t.getM_id());
	}

	@Override
	public int delete(Message t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	//查找群的最新消息
	public List<Message> findNewMessage(QQInfo t) throws Exception{
		String group="select * from (select * from message where m_groupqq in(select gno from user_group where qq = ?)  order BY m_date desc)a  GROUP BY m_groupqq"; //查询最新的群消息
		List<Object> params = new ArrayList<>();
		params.add(t.getQq());
		List<Message> groupMessage = db.findMutil(group, params, Message.class);
		//TODO:
		Friends f = new Friends();
		f.setQq(t.getQq());
	
		
		//组合
		return groupMessage;
	}
	
	
	
	
	
	public Message findNewFriendMessage(Friends f) throws Exception{
		String sql ="select * from message where (m_qq = ? and m_fqq = ?) or (m_qq = ? and m_fqq = ?)  order by m_date desc ";
		List<Object> params = new ArrayList<>();
		params.add(f.getQq());
		params.add(f.getFqq());
		params.add(f.getFqq());
		params.add(f.getQq());
		return db.find(sql, params, Message.class);
	}

	/**
	 * 查询未读信息
	 * @param qq
	 * @return
	 * @throws SQLException 
	 */
	public int findNumberOfUnderRead(String qq) throws SQLException {
		String sql= "select count(isread) from message where   isread = 0 and m_fqq=?";
		List<Object> params  =new ArrayList<>();
		params.add(qq);
		return (int) db.getPolymer(sql, params);
	}
	
	public List<Message> findNumberOfUnderReadGroup(String qq) throws Exception {
		String sql= "select * from message where m_groupqq in ((select gno from user_group where qq = ?))and m_qq !=?";
		List<Object> params  =new ArrayList<>();
		params.add(qq);
		params.add(qq);
		return db.findMutil(sql, params, Message.class);
	}
	

}
