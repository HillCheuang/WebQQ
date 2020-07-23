package com.yc.bean;

import java.util.Date;

public class Friends {
	private String fno;
	private String fqq;
	private String qq;
	private Integer sno;
	private String addtime;
	private Integer fstatus;
	private Integer send;
	private Integer isagree;
	
	
	public Integer getIsagree() {
		return isagree;
	}
	public void setIsagree(Integer isagree) {
		this.isagree = isagree;
	}
	public String getFno() {
		return fno;
	}
	public void setFno(String fno) {
		this.fno = fno;
	}
	public String getFqq() {
		return fqq;
	}
	public void setFqq(String fqq) {
		this.fqq = fqq;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public Integer getFstatus() {
		return fstatus;
	}
	public void setFstatus(Integer fstatus) {
		this.fstatus = fstatus;
	}
	public Integer getSend() {
		return send;
	}
	public void setSend(Integer send) {
		this.send = send;
	}
	@Override
	public String toString() {
		return "Friends [fno=" + fno + ", fqq=" + fqq + ", qq=" + qq + ", sno=" + sno + ", addtime=" + addtime
				+ ", fstatus=" + fstatus + ", send=" + send + ", isagree=" + isagree + "]";
	}


}
