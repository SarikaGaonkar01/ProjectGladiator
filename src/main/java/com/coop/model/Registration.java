package com.coop.model;

public class Registration {
	private String accno;
	private String uname;
	private String pcard;
	private String acard;
	private String bdetail;
	private String adetail;
	private String acctype;
	private int amt;
	private String password;
	private int age;
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPcard() {
		return pcard;
	}
	public void setPcard(String pcard) {
		this.pcard = pcard;
	}
	public String getAcard() {
		return acard;
	}
	public void setAcard(String acard) {
		this.acard = acard;
	}
	public String getBdetail() {
		return bdetail;
	}
	public void setBdetail(String bdetail) {
		this.bdetail = bdetail;
	}
	public String getAdetail() {
		return adetail;
	}
	public void setAdetail(String adetail) {
		this.adetail = adetail;
	}
	public String getAcctype() {
		return acctype;
	}
	public void setAcctype(String acctype) {
		this.acctype = acctype;
	}
	public int getAmt() {
		return amt;
	}
	public void setAmt(int amt) {
		this.amt = amt;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = MD5.getMd5(password);
	}
	
	


	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
}
