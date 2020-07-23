package com.yc.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yc.bean.GroupUser;
import com.yc.bean.Message;
import com.yc.bean.MessageVo;
import com.yc.bean.QQInfo;
import com.yc.biz.GroupUserBiz;
import com.yc.biz.MessageBiz;
import com.yc.biz.QQInfoBiz;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


import com.google.gson.Gson;

@ServerEndpoint("/ChatSocket")
public class ChatServer {
	private String qq;				//保存登录服务器的qq
	private static List<Session> sessions =new ArrayList<Session>();			//服务器与浏览器的映射
	private static Map<String, Session> map = new HashMap<String, Session>();	//映射
	private static List<String> qqs = new ArrayList<>();						//保存连接服务器的qqs
	
	private static Gson gson =new Gson();
	MessageBiz biz = new MessageBiz();
	GroupUserBiz groupUserBiz = new GroupUserBiz();
	
	@OnOpen
	public void open(Session session ){
		//当前的webSocket的session对象 不是selvet的session
		String string = session.getQueryString();
		qq = string.split("=")[1];
		this.sessions.add(session);
		this.qqs.add(qq);
		this.map.put(qq, session);
	}
	
	//广播类
	public void broadcast(List<Session> ss, String msg) {
		// TODO Auto-generated method stub
		for(Iterator iterator=ss.iterator();iterator.hasNext();){
			Session session =(Session) iterator.next();
			try {
				session.getBasicRemote().sendText(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	@OnMessage
	public void Message(Session session,String json){
		Message fromJson = gson.fromJson(json, Message.class);
		System.out.println(json);
		
			//将消息存储到数据库中
		if(fromJson.getM_type()=="2"){			//好友请求
			String fqq = fromJson.getM_fqq();
			Session to_session = this.map.get(fqq);
			if(to_session==null){	//对方不在线 ，则将数据存入数据库中
				return;
			}
			try {
				String text = gson.toJson("addFriend");
				to_session.getBasicRemote().sendText(text);
				System.err.println(111);
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		 if( fromJson.getM_groupqq()==null ||fromJson.getM_groupqq().trim().equals("")){	//私聊
			 
			//首先根据qq找到对应的session对象
			String fqq=fromJson.getM_fqq();
			Session to_session = this.map.get(fqq);
			System.out.println(to_session);
			fromJson.setM_type("0");
			fromJson.setIsread("0");
			try {
				biz.update(fromJson);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(to_session==null){	//对方不在线 ，则将数据存入数据库中
				return;
			}
			else{
				try {
					
					String text = gson.toJson(fromJson);
					to_session.getBasicRemote().sendText(text);			//提醒用户数据已经存到了数据库中
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{//群聊			要遍历群里面的所有QQ号码
			String groupqq= fromJson.getM_groupqq();
			List<Session> groupsessions =new ArrayList<>();;
			MessageVo messageVo = null;
			fromJson.setM_type("1");
			fromJson.setIsread("0");
			
			
			try {
				biz.update(fromJson);
				List<GroupUser> list = groupUserBiz.findByGroupno(groupqq);
				System.out.println(list);
				System.out.println(this.map);
				for(GroupUser u:list){		//遍历群聊的全部QQ号码
					Session to_session =this.map.get(u.getQq());
					if(to_session==null){
						continue;
					}
					System.out.println(u.getQq()+"的session为："+to_session);
					if(u.getQq().equals(fromJson.getM_qq())){		//发送给自己
						continue;
					}
					QQInfo qqInfo =new QQInfo();
					qqInfo.setQq(fromJson.getM_qq());
					QQInfoBiz biz = new QQInfoBiz();
					List<QQInfo> findByTerm = biz.findByTerm(qqInfo);
					QQInfo myself = findByTerm.get(0);
					messageVo = new MessageVo(fromJson,myself.getHeadphoto(), myself.getNickname());
					groupsessions.add(to_session);
					continue;
				}
				if(groupsessions!=null&&groupsessions.size()>=0){
					System.err.println(groupsessions);
					String text = gson.toJson(messageVo);
					for(Session s:groupsessions){
						System.out.println("发送群聊消息成功！！");
						System.out.println(text);
						s.getBasicRemote().sendText(text);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	
	//离线提示
		@OnClose
		public void close(Session session){
			this.sessions.remove(session);
			this.qqs.remove(this.qq);
			this.map.remove(this.qq);
		}
	
		
}
