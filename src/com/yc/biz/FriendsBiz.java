package com.yc.biz;

import java.security.acl.Group;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.xerces.internal.util.EntityResolver2Wrapper;
import com.yc.bean.Friends;
import com.yc.bean.FriendsVo;
import com.yc.bean.Subgroup;
import com.yc.dao.FriendsDao;
import com.yc.dao.SubGroupDao;

public class FriendsBiz {
	FriendsDao dao=new FriendsDao();
	SubGroupDao subdao=new SubGroupDao();
	/**根据QQ查找每个分组的好友
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> findFriendBySub(Subgroup t) throws Exception{
		List<Subgroup> subgroups=subdao.findSubgroup(t);
		FriendsVo f=null;
		List<Friends> list=null;
		Friends bean=null;
		List<Map<String,Object>> friends=null;
		Map<String,Object> map=null;
		List<FriendsVo> vos=null;
		
		if(subgroups!=null&&!subgroups.isEmpty()){
			friends=new ArrayList<>();
			for(Subgroup s:subgroups){
				
				bean=new Friends();
				bean.setSno(s.getSno());
				bean.setIsagree(1);
				list=dao.findByTrem(bean);
				vos=new ArrayList<>();
				for(Friends l:list){
					map=new HashMap<>();
					f=new FriendsVo();
					f.setSno(s.getSno());
					f.setQq(l.getFqq());
					f.setFqq(l.getQq());
					vos.add(dao.findFriendVo(f));
				}
				map=new HashMap<>();
				map.put("subgroup", s);
				map.put("friends", vos);
				friends.add(map);
			}
		}
		
		return friends;

	}
	/**
	 * 添加好友
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public int addFriend(Friends t) throws Exception{
		return dao.add(t);
	}
	
	public int updateFriend(Friends t) throws Exception{
		return dao.update(t);
		
	}

	public int ChangSubGroup(Friends friends) throws SQLException {
		// TODO Auto-generated method stub
		return dao.ChangSubGroup(friends);
	}

	public int delectFriend(Friends friends) throws Exception {
		// TODO Auto-generated method stub
		return dao.delete(friends);
	}
	public List<Friends> findFriend(Friends friends) throws Exception {
		// TODO Auto-generated method stub
		return dao.findByTrem(friends);
	}
}
