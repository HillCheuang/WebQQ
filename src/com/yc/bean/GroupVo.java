package com.yc.bean;

public class GroupVo {
	private Integer ugno;
	private String qq;
	private String gno;
	private Integer status;
	private String gname;
	private String gdate;
	private String builder;
	private String goupheadphoto;		//群头像
	
	public Integer getUgno() {
		return ugno;
	}
	public void setUgno(Integer ugno) {
		this.ugno = ugno;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getGno() {
		return gno;
	}
	public void setGno(String gno) {
		this.gno = gno;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getGdate() {
		return gdate;
	}
	public void setGdate(String gdate) {
		this.gdate = gdate;
	}
	public String getBuilder() {
		return builder;
	}
	public void setBuilder(String builder) {
		this.builder = builder;
	}
	
	
	@Override
	public String toString() {
		return "GroupVo [ugno=" + ugno + ", qq=" + qq + ", gno=" + gno + ", status=" + status + ", gname=" + gname
				+ ", gdate=" + gdate + ", builder=" + builder + ", goupheadphoto=" + goupheadphoto + "]";
	}
	public String getGoupheadphoto() {
		return goupheadphoto;
	}
	public void setGoupheadphoto(String goupheadphoto) {
		this.goupheadphoto = goupheadphoto;
	}
	
}
