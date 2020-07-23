package com.yc.biz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yc.bean.FGcommon;
import com.yc.bean.Friends;
import com.yc.bean.GroupTable;
import com.yc.bean.Message;
import com.yc.bean.QQInfo;
import com.yc.dao.FriendsDao;
import com.yc.dao.GroupDao;
import com.yc.dao.MessageDao;
import com.yc.dao.QQInfoDao;

public class MessageBiz {
		
	MessageDao dao = new MessageDao();
	GroupDao gDao=new GroupDao();
	QQInfoDao qDao=new QQInfoDao();
	FriendsDao fDao = new FriendsDao();
	
	/**
	 * 存储消息记录
	 * @throws Exception 
	 */
	
	public int setIsRead(Message m)throws Exception{
		return dao.update(m);
	}
	
	public int update(Message m) throws Exception{
		return dao.add(m);
	}
	
	public int read(int id)throws Exception{
		return dao.read(id);
	}
	
	/**
	 * 查看聊天信息
	 * @param m
	 * @return
	 * @throws Exception
	 */
	public List<Message> findByTrem(Message m)throws Exception{
		List<Message> messages = dao.findByTrem(m);
		return messages;
	}
	
	/**
	 * 查询所有好友的最新消息
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<Message> findlasetMessage(QQInfo t)throws Exception{
		Friends f =new Friends();
		f.setQq(t.getQq());
		List<Friends> friends = fDao.findByTrem(f);	//该QQ的所有好友
		List<Message> messages =new ArrayList<>();
		for(Friends ff:friends){
			Message m = dao.findNewFriendMessage( ff);
			messages.add(m);
		}
		return messages;
	}
	
	
	
	
	public List<FGcommon> findByMessage(QQInfo t) throws Exception{
		List<Message> groupmessage=dao.findNewMessage(t);
		List<Message> friendmessage=findlasetMessage(t);
		System.err.println(friendmessage);
		List<Message> messages = new ArrayList<>();
		if(groupmessage!=null){
			
			for(Message m:groupmessage){
				messages.add(m);
			}
		}
		if(friendmessage!=null){
			for(Message m:friendmessage){
				messages.add(m);
			}
		}
		List<FGcommon> list=null;
		GroupTable group=null;
		QQInfo qqInfo=null;
		FGcommon fg=null;
		if(messages!=null&&!messages.isEmpty()){
			System.out.println(messages);
			list=new ArrayList<>();
			for(Message m:messages){
				if(m==null){
					continue;
				}
				System.out.println(m);
				fg=new FGcommon();
				//判断是QQ群还是QQ好友
				if(m.getM_groupqq()!=null){
					fg.setQq(m.getM_groupqq());
					group=new GroupTable();
					group.setGno(m.getM_groupqq());
					System.out.println(gDao.findByTrem(group));
					fg.setName(gDao.findByTrem(group).get(0).getGname());
					//待添加群聊头像
					fg.setHeadPhoto(gDao.findByTrem(group).get(0).getGoupheadphoto());
				}else{
					if(t.getQq().equals(m.getM_qq())){
						fg.setQq(m.getM_fqq());
						qqInfo=new QQInfo();
						qqInfo.setQq(m.getM_fqq());
						fg.setName(qDao.findByTrem(qqInfo).get(0).getNickname());
						fg.setHeadPhoto(qDao.findByTrem(qqInfo).get(0).getHeadphoto());
						
					}else{
						fg.setQq(m.getM_qq());
						qqInfo=new QQInfo();
						qqInfo.setQq(m.getM_qq());
						fg.setName(qDao.findByTrem(qqInfo).get(0).getNickname());
						fg.setHeadPhoto(qDao.findByTrem(qqInfo).get(0).getHeadphoto());
						
					}
					
				}
				fg.setDate(m.getM_date());
				fg.setLastcontent(m.getM_content());
				list.add(fg);
			}
		}
		return list;
	}

	/**
	 * 查询未读的信息
	 * @throws SQLException 
	 */
	public int findNumberOfUnderRead(String qq) throws SQLException {
		int friendUnderRead = 	dao.findNumberOfUnderRead(qq); 
		int groupUnderRead = 0;
		try {
			List<Message> GroupMessage = dao.findNumberOfUnderReadGroup(qq);
			System.out.println(GroupMessage);
			for(Message m:GroupMessage){
				String split = m.getIsread();
					if(split==null||split.equals("")||!split.contains(qq)){
						groupUnderRead++;
					}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("未读好友消息："+friendUnderRead);
		System.out.println("未读群消息："+groupUnderRead);
		return (friendUnderRead+groupUnderRead);
	}
}
