package com.training.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.training.bank.model.Account;

public class BankDao {

	private static BankDao bankDao = new BankDao();

	private BankDao(){ }

	public static BankDao getInstance(){
		return bankDao;
	}
	
	public Account queryAccountById(String id){
		Account account = null;		
		// querySQL SQL
		String querySQL = "SELECT ID, NAME, PWD, BALANCE FROM BANK_ACCOUNT WHERE ID = ?";		
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();
		    // Step2:Create prepareStatement For SQL
			PreparedStatement stmt = conn.prepareStatement(querySQL)){
			stmt.setString(1, id);
			try(ResultSet rs = stmt.executeQuery()){
				if(rs.next()){
					account = new Account();
					account.setId(rs.getString("ID"));
					account.setName(rs.getString("NAME"));
					account.setPwd(rs.getString("PWD"));
					account.setBalance(rs.getDouble("BALANCE"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}
	
	public List<Account> queryAllAccount(){
		List<Account> accounts = new ArrayList<>();
		// querySQL SQL
		String querySQL = "SELECT ID, NAME, PWD, BALANCE FROM BANK_ACCOUNT";
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();
		    // Step2:Create prepareStatement For SQL
			PreparedStatement stmt = conn.prepareStatement(querySQL);
			ResultSet rs = stmt.executeQuery()){
			while(rs.next()){
				Account account = new Account();
				account.setId(rs.getString("ID"));
				account.setName(rs.getString("NAME"));
				account.setPwd(rs.getString("PWD"));
				account.setBalance(rs.getDouble("BALANCE"));
				accounts.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accounts;
	}

	public boolean updateAccount(Account account) {
		boolean updateSuccess = false;
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();){
			// 設置交易不自動提交
			conn.setAutoCommit(false);
			// Update SQL
			String updateSQL = "UPDATE BANK_ACCOUNT SET NAME = ?, PWD = ? WHERE ID = ?";
			// Step2:Create prepareStatement For SQL
			try (PreparedStatement stmt = conn.prepareStatement(updateSQL)){
				// Step3:將"資料欄位編號"、"資料值"作為引數傳入
				stmt.setString(1, account.getName());
				stmt.setString(2, account.getPwd());
				stmt.setString(3, account.getId());
				// Step4:Execute SQL
				int recordCount = stmt.executeUpdate();
				updateSuccess = (recordCount > 0) ? true : false;
				// Step5:Transaction commit(交易提交)
				conn.commit();
			} catch (SQLException e) {
				// 若發生錯誤則資料 rollback(回滾)
				conn.rollback();
				throw e;
			}			
		} catch (SQLException e) {
			updateSuccess = false;
			e.printStackTrace();
		}
		
		return updateSuccess;
	}

	public boolean createAccount(Account account) {
		boolean createSuccess = false;
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();){
			// 設置交易不自動提交
			conn.setAutoCommit(false);
			// Insert SQL
			String insertSQL = "INSERT INTO BANK_ACCOUNT (ID, NAME, PWD, BALANCE) VALUES (?, ?, ?, ?)";
			// Step2:Create prepareStatement For SQL
			try (PreparedStatement stmt = conn.prepareStatement(insertSQL)){
				// Step3:將"資料欄位編號"、"資料值"作為引數傳入
				stmt.setString(1, account.getId());
				stmt.setString(2, account.getName());
				stmt.setString(3, account.getPwd());
				stmt.setDouble(4, account.getBalance());
				// Step4:Execute SQL
				int recordCount = stmt.executeUpdate();
				createSuccess = (recordCount > 0) ? true : false;
				// Step5:Transaction commit(交易提交)
				conn.commit();
			} catch (SQLException e) {
				// 若發生錯誤則資料 rollback(回滾)
				conn.rollback();
				throw e;
			}			
		} catch (SQLException e) {
			createSuccess = false;
			e.printStackTrace();
		}
		
		return createSuccess;
	}

	public boolean deleteAccount(String id) {
		boolean deleteSuccess = false;
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();){
			// 設置交易不自動提交
			conn.setAutoCommit(false);
			// Insert SQL
			String insertSQL = "DELETE FROM BANK_ACCOUNT WHERE ID = ?";
			// Step2:Create prepareStatement For SQL
			try (PreparedStatement stmt = conn.prepareStatement(insertSQL)){
				// Step3:將"資料欄位編號"、"資料值"作為引數傳入
				stmt.setString(1, id);
				// Step4:Execute SQL
				int recordCount = stmt.executeUpdate();
				deleteSuccess = (recordCount > 0) ? true : false;
				// Step5:Transaction commit(交易提交)
				conn.commit();
			} catch (SQLException e) {
				// 若發生錯誤則資料 rollback(回滾)
				conn.rollback();
				throw e;
			}			
		} catch (SQLException e) {
			deleteSuccess = false;
			e.printStackTrace();
		}
		
		return deleteSuccess;
	}
	
	
}
