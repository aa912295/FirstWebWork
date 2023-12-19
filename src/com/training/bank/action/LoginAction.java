package com.training.bank.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.training.bank.dao.BankDao;
import com.training.bank.model.Account;

public class LoginAction extends DispatchAction {
	
	private BankDao bankDao = BankDao.getInstance();
	
	/**
	 * info:這是負責"登入"的action method
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward login(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 登入請求
    	ActionForward actFwd = null;
    	HttpSession session = request.getSession();
    	String inputID = request.getParameter("id");
        String inputPwd = request.getParameter("pwd");
        // Step2:依使用者所輸入的帳戶名稱取得 Member
        Account account = bankDao.queryAccountById(inputID);
        String loginMsg = null;
    	if(account != null) {
    		// Step3:取得帳戶後進行帳號、密碼比對
    		String id = account.getId();
    		String pwd = account.getPwd();
    		if(id.equals(inputID) && pwd.equals(inputPwd)) {
    			loginMsg = account.getName() + " 先生/小姐您好!";
    			// 將account存入session scope 以供LoginCheckFilter之後使用!
    			session.setAttribute("account", account);
    			actFwd = mapping.findForward("success");        			
    		} else {
                // Step4:帳號、密碼錯誤,轉向到 "/BankLogin.html" 要求重新登入.
    			loginMsg = "帳號或密碼錯誤";
    			actFwd = mapping.findForward("fail");
    		}
    	} else {
            // Step5:無此帳戶名稱,轉向到 "/BankLogin.html" 要求重新登入.
    		loginMsg = "無此帳戶名稱,請重新輸入!";	
    		actFwd = mapping.findForward("fail");
    	}
    	request.setAttribute("loginMsg", loginMsg);
    	
    	return actFwd;
    }
    

    public ActionForward logout(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
    	// 登出請求
    	HttpSession session = request.getSession();
		session.removeAttribute("account");
		request.setAttribute("loginMsg", "謝謝您的光臨!");
    	
    	return mapping.findForward("fail");
    }
}
