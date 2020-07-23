package com.yc.bean;

public class Subgroup {
	//好友分组表
	private Integer sno;
	private String sname;
	private String qq;
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	@Override
	public String toString() {
		return "Subgroup [sno=" + sno + ", sname=" + sname + ", qq=" + qq + "]";
	}
	
}
