package com.yc.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.yc.bean.GroupUser;
import com.yc.bean.Message;
import com.yc.bean.MessageVo;
import com.yc.bean.QQInfo;
import com.yc.biz.QQInfoBiz;

@ServerEndpoint("/Message")
public class MessageServer {
	private String qq;				//保存登录服务器的qq
	private static List<Session> sessions =new ArrayList<Session>();			//服务器与浏览器的映射
	private static Map<String, Session> map = new HashMap<String, Session>();	//映射
	private static List<String> qqs = new ArrayList<>();						//保存连接服务器的qqs
	
	private static Gson gson =new Gson();
	@OnOpen
	public void open(Session session ){
		//当前的webSocket的session对象 不是selvet的session
		String string = session.getQueryString();
		qq = string.split("=")[1];
		this.sessions.add(session);
		this.qqs.add(qq);
		this.map.put(qq, session);
	}
	//离线提示
			@OnClose
			public void close(Session session){
				this.sessions.remove(session);
				this.qqs.remove(this.qq);
			}
			@OnMessage
			public void Message(Session session,String json){
				Message fromJson = gson.fromJson(json, Message.class);
				System.out.println(json);
				System.out.println(fromJson);
					//将消息存储到数据库中
				if(fromJson.getM_type().equals("2")){			//好友请求
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
				}else if(fromJson.getM_type().equals("3")){
					String qq = fromJson.getM_qq();
					Session to_session = this.map.get(qq);
					if(to_session==null){	//对方不在线 ，则将数据存入数据库中
						return;
					}
					try {
						String text = gson.toJson("RemoveFriend");
						to_session.getBasicRemote().sendText(text);
						System.err.println(111);
						return;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
}
