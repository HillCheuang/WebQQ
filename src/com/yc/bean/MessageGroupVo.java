package com.yc.bean;

/**
 * 群消息记录表（记录了发消息的每个人的qq号码和头像）
 * @author dell
 *
 */
public class MessageGroupVo {
	private String date;	//日期
	private String groupqq;	//群号
	private String content;	//内容
	private String groupname;	//群姓名
	private String groupheadphoto;	//群头像
	private String qq;				//发送到给群的qq号码记录
	private String nickname;		//用户名
	private String headphoto;		//用户头像
	private String m_type;			//类型
	private Integer m_id;	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getGroupqq() {
		return groupqq;
	}
	public void setGroupqq(String groupqq) {
		this.groupqq = groupqq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getGroupheadphoto() {
		return groupheadphoto;
	}
	public void setGroupheadphoto(String groupheadphoto) {
		this.groupheadphoto = groupheadphoto;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "MessageGroupVo [date=" + date + ", groupqq=" + groupqq + ", content=" + content + ", groupname="
				+ groupname + ", groupheadphoto=" + groupheadphoto + ", qq=" + qq + ", nickname=" + nickname
				+ ", headphoto=" + headphoto + ", m_type=" + m_type + ", m_id=" + m_id + "]";
	}
	public String getHeadphoto() {
		return headphoto;
	}
	public void setHeadphoto(String headphoto) {
		this.headphoto = headphoto;
	}
	public String getM_type() {
		return m_type;
	}
	public void setM_type(String m_type) {
		this.m_type = m_type;
	}
	public Integer getM_id() {
		return m_id;
	}
	public void setM_id(Integer m_id) {
		this.m_id = m_id;
	}
}
