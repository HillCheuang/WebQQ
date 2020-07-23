package com.yc.bean;

/**
 * 此消息类专门用于群消息
 * @author dell
 *
 */
public class MessageVo {
	private Integer m_id;
	private String m_date;
	private String m_qq;
	private String m_fqq;
	private String m_content;
	private String m_groupqq;
	private String m_picture;
	private String m_file;
	private String headphoto;		//头像
	private String nickName;		//姓名
	
	public MessageVo() {
		// TODO Auto-generated constructor stub
	}
	
	public MessageVo(Message message,String headphoto,String nickName) {
		this.m_date=message.getM_date();
		this.m_qq=message.getM_qq();
		this.m_fqq=message.getM_fqq();
		this.m_content=message.getM_content();
		this.m_groupqq=message.getM_groupqq();
		this.m_picture=message.getM_picture();
		this.m_file=message.getM_file();
		this.headphoto=headphoto;
		this.nickName=nickName;
		
	}
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
	public String getHeadphoto() {
		return headphoto;
	}
	public void setHeadphoto(String headphoto) {
		this.headphoto = headphoto;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Override
	public String toString() {
		return "MessageVo [m_id=" + m_id + ", m_date=" + m_date + ", m_qq=" + m_qq + ", m_fqq=" + m_fqq + ", m_content="
				+ m_content + ", m_groupqq=" + m_groupqq + ", m_picture=" + m_picture + ", m_file=" + m_file
				+ ", headphoto=" + headphoto + ", nickName=" + nickName + "]";
	}
}
