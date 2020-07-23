package com.yc.bean;

public class FGcommon {
	private String name;
	private String qq;
	private String lastcontent;
	private String headPhoto;
	public String getHeadPhoto() {
		return headPhoto;
	}

	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}

	private String date;
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getLastcontent() {
		return lastcontent;
	}

	public void setLastcontent(String lastcontent) {
		this.lastcontent = lastcontent;
	}

	@Override
	public String toString() {
		return "FGcommon [name=" + name + ", qq=" + qq + ", lastcontent=" + lastcontent + ", headPhoto=" + headPhoto
				+ ", date=" + date + "]";
	}
	
	
}
