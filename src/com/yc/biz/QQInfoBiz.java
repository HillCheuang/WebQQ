package com.yc.biz;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Friends;
import com.yc.bean.FriendsVo;
import com.yc.bean.QQInfo;
import com.yc.dao.FriendsDao;
import com.yc.dao.QQInfoDao;

public class QQInfoBiz {
	QQInfoDao dao=new QQInfoDao();
	FriendsDao fdao=new FriendsDao();
	
	/**
	 * 登录操作
	 * @param t
	 * @return
	 * @throws Exception 
	 */
	public QQInfo login(QQInfo t) throws Exception{
		List<QQInfo> list=dao.findByTrem(t);
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	
	}
	
	public int add(QQInfo t) throws Exception{
		return dao.add(t);
	}
	
	public List<QQInfo> findFriends(QQInfo t) throws Exception{
		List<QQInfo> list=dao.findFriends(t);
		return list;
	}
	
public List<FriendsVo> dofindDisAgree(Friends t) throws Exception {
		
		List<Friends> friends=fdao.findByTrem(t);
		
		FriendsVo friendsVo=null;
		List<FriendsVo> friendsVos=null;
		if(friends!=null&&!friends.isEmpty()){
			friendsVos=new ArrayList<>();
			for(Friends f:friends){
				friendsVo=new FriendsVo();
				friendsVo.setQq(f.getQq());
				friendsVo.setFqq(f.getFqq());
				
				friendsVos.add(fdao.findNotic(friendsVo));
			}
		}
		return friendsVos;
	}
	
	public List<QQInfo> findByTerm(QQInfo t) throws Exception{
		List<QQInfo> list=dao.findByTrem(t);
		return list;
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	public int changePwd(QQInfo t)throws Exception{
		return dao.update(t);
	}
	/**
	 * 修改详细信息
	 * @param t
	 * @return
	 * @throws Exception 
	 */
	public int changeDetail(QQInfo t) throws Exception{
		return dao.update(t);
	}
	/**修改个人信息
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public int updateQQinfo(QQInfo t) throws Exception{
		return dao.update(t);
	}
	
}
