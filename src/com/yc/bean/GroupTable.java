package com.yc.bean;

public class GroupTable {
	private String gno;
	private String gname;
	private String gdate;
	private String builder;
	private String goupheadphoto;		//群头像
	
	public String getGno() {
		return gno;
	}
	public void setGno(String gno) {
		this.gno = gno;
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
		return "GroupTable [gno=" + gno + ", gname=" + gname + ", gdate=" + gdate + ", builder=" + builder
				+ ", goupheadphoto=" + goupheadphoto + "]";
	}
	public String getGoupheadphoto() {
		return goupheadphoto;
	}
	public void setGoupheadphoto(String goupheadphoto) {
		this.goupheadphoto = goupheadphoto;
	}
	
	
	
	
}
