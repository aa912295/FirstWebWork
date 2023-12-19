package com.training.bank.service;

import java.util.List;

import com.training.bank.dao.BankDao;
import com.training.bank.model.Account;

public class BankService {
	
	private static BankService bankService = new BankService();

	private BankService(){ }	
	
	public static BankService getInstance(){
		return bankService;
	}
	
	private BankDao bankDao = BankDao.getInstance();
	
	public List<Account> queryAllAccount(){
		
		return bankDao.queryAllAccount();
	}
	
	public Account queryAccountById(String id){
		
		return bankDao.queryAccountById(id);
	}
	
	public boolean modifyAccount(Account account) {	
		
		return bankDao.updateAccount(account);
	}

	public boolean createAccount(Account account) {
		
		return bankDao.createAccount(account);
	}

	public boolean deleteAccount(String id) {

		return bankDao.deleteAccount(id);
	}
}
