package com.yc.bean;

import com.google.gson.Gson;

public class Message {
	public Message() {
		// TODO Auto-generated constructor stub
	}
	public Message(String qq,String fqq,String groupqq) {
		m_qq=qq;
		m_fqq=fqq;
		m_groupqq=groupqq;
	}
	private Integer m_id;
	private String m_date;
	private String m_qq;
	private String m_fqq;
	private String m_content;
	private String m_groupqq;
	private String m_picture;
	private String m_file;
	private String isread;	//已读或未读
	private String m_type;	//状态 0私聊 1 群聊   
	public Integer getM_id() {
		return m_id;
	}
	public void setM_id(Integer m_id) {
		this.m_id = m_id;
	}
	public String getM_date() {
		return m_date;
	}
	public void setM_date(String m_date) {
		this.m_date = m_date;
	}
	public String getM_qq() {
		return m_qq;
	}
	public void setM_qq(String m_qq) {
		this.m_qq = m_qq;
	}
	public String getM_fqq() {
		return m_fqq;
	}
	public void setM_fqq(String m_fqq) {
		this.m_fqq = m_fqq;
	}
	public String getM_content() {
		return m_content;
	}
	public void setM_content(String m_content) {
		this.m_content = m_content;
	}
	public String getM_groupqq() {
		return m_groupqq;
	}
	public void setM_groupqq(String m_groupqq) {
		this.m_groupqq = m_groupqq;
	}
	public String getM_picture() {
		return m_picture;
	}
	public void setM_picture(String m_picture) {
		this.m_picture = m_picture;
	}
	public String getM_file() {
		return m_file;
	}
	public void setM_file(String m_file) {
		this.m_file = m_file;
	}
	@Override
	public String toString() {
		return "Message [m_id=" + m_id + ", m_date=" + m_date + ", m_qq=" + m_qq + ", m_fqq=" + m_fqq + ", m_content="
				+ m_content + ", m_groupqq=" + m_groupqq + ", m_picture=" + m_picture + ", m_file=" + m_file
				+ ", isread=" + isread + ", m_type=" + m_type + "]";
	}
	public String getIsread() {
		return isread;
	}
	public void setIsread(String isread) {
		this.isread = isread;
	}
	public String getM_type() {
		return m_type;
	}
	public void setM_type(String m_type) {
		this.m_type = m_type;
	}
	
	
	
	
}
