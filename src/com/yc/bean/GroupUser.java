package com.yc.bean;

/**
 * 群与好友的表的关系
 * @author dell
 *
 */
public class GroupUser {

	private String gno;		//群编号（）
	private String gname;	//群名字
	private String goupheadphoto;	//群头像
	private String qq;		//群员的qq号码
	private String nickname;	//群员名称
	private String headphoto;	//群员QQ头像
	private String job;			//群员的职业
	private String company;		//群员的公司
	private String addr;		//群员的地址
	public String getGno() {
		return gno;
	}
	public void setGno(String gno) {
		this.gno = gno;
	}
	@Override
	public String toString() {
		return "GroupUser [gno=" + gno + ", gname=" + gname + ", goupheadphoto=" + goupheadphoto + ", qq=" + qq
				+ ", nickname=" + nickname + ", headphoto=" + headphoto + ", job=" + job + ", company=" + company
				+ ", addr=" + addr + "]";
	}
	public String getGoupheadphoto() {
		return goupheadphoto;
	}
	public void setGoupheadphoto(String goupheadphoto) {
		this.goupheadphoto = goupheadphoto;
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
	public String getHeadphoto() {
		return headphoto;
	}
	public void setHeadphoto(String headphoto) {
		this.headphoto = headphoto;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
}
