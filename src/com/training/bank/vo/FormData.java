package com.training.bank.vo;

import org.apache.struts.action.ActionForm;

public class FormData extends ActionForm {
	// 身份證字號
	private String id;	
	// 帳戶名稱
	private String name;
	// 帳戶密碼
	private String pwd;
	// 帳戶餘額
	private double balance;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
