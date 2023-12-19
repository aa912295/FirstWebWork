package com.training.bank.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.training.bank.model.Account;
import com.training.bank.service.BankService;
import com.training.bank.vo.FormData;

public class BankActionAjax extends DispatchAction {
	
	private BankService bankService = BankService.getInstance();

    public ActionForward queryAllAccount(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) {
		List<Account> accounts = bankService.queryAllAccount();
		request.setAttribute("accounts", accounts);

		return mapping.findForward("accountListView");
	}
	
    public ActionForward modifyAccountView(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) {
    	    	
    	// 身份證字號選單資料
		List<Account> accounts = bankService.queryAllAccount();
		request.setAttribute("accounts", accounts);
		
		// 被選擇要修改的帳號資料
		String id = (String)request.getSession().getAttribute("modifyAccountID");
		if(id != null){
			Account account = bankService.queryAccountById(id);
			request.setAttribute("modifyAccount", account);
		}
		
    	return mapping.findForward("accountModifyView");
    }
    
    // for AJAX 使用
    public ActionForward getModifyAccount(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 被選擇要修改的帳號資料		
		String id = request.getParameter("id");
		Account account = bankService.queryAccountById(id);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(JSONObject.fromObject(account));
		out.flush();
		out.close();
		
    	return null;
    }
    
    public ActionForward modifyAccount(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception{
    	HttpSession session = request.getSession();
    	// 將表單資料使用 struts ActionForm 方式自動綁定，省去多次由 request getParameter 取表單資料的工作
    	FormData formData = (FormData) form;
    	
		// 將Struts BackedActionForm 資料複製 Goods
		// 將表單資料轉換儲存資料物件(commons-beanutils-1.8.0.jar)
		Account account = new Account();
		BeanUtils.copyProperties(account, formData);
		
		boolean modifyResult = bankService.modifyAccount(account);
		String message = modifyResult ? "帳戶資料修改成功！" : "帳戶資料修改失敗！";
		session.setAttribute("modifyMsg", message);
		session.setAttribute("modifyAccountID", account.getId());
		
		return mapping.findForward("accountModify");
	}
	
    public ActionForward createAccountView(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) {
    	
    	return mapping.findForward("accountCreateView");
    }
    
    public ActionForward createAccount(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception{
    	HttpSession session = request.getSession();
    	// 將表單資料使用 struts ActionForm 方式自動綁定，省去多次由 request getParameter 取表單資料的工作
    	FormData formData = (FormData) form;
    	
		// 將Struts BackedActionForm 資料複製 Goods
		// 將表單資料轉換儲存資料物件(commons-beanutils-1.8.0.jar)
		Account account = new Account();
		BeanUtils.copyProperties(account, formData);
		boolean createResult = bankService.createAccount(account);
		String message = createResult ? "帳戶資料新增成功！" : "帳戶資料新增失敗！";
		session.setAttribute("createMsg", message);
		
		return mapping.findForward("accountCreate");
	}	

    public ActionForward deleteAccount(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception{
    	HttpSession session = request.getSession();
		String id = request.getParameter("id");		
		boolean deleteResult = bankService.deleteAccount(id);
		String message = deleteResult ? "帳戶資料刪除成功！" : "帳戶資料刪除失敗！";
		// 因為刪除為交易動作是用重導的方式至頁面，所以將訊息儲存在session才能跨request存取顯示!
		session.setAttribute("deleteMsg", message);

		return mapping.findForward("deleteAccountRedirect");
	}
    
    
}
