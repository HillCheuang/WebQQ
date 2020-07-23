package com.yc.bean;
public class UserGroup {
	private Integer ugno;
	private String qq;
	private String gno;
	private Integer status;
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
	@Override
	public String toString() {
		return "UserGroup [ugno=" + ugno + ", qq=" + qq + ", gno=" + gno + ", status=" + status + "]";
	}
	
	
}
