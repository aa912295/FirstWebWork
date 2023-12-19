<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-tw">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>BankLogin</title>
	<script type="text/javascript">
		function accountSelected(){
	        document.updateAccountForm.action.value = "modifyAccountView";
	        document.updateAccountForm.submit();
		}
	</script>
</head>
<body>
	<%@ include file="Bank_FunMenu.jsp" %>
	<br/><br/><HR>		
	<h2>帳戶修改</h2><br/>
	<div style="margin-left:25px;">
	<p style="color:blue;">${sessionScope.modifyMsg}</p>
	<% session.removeAttribute("modifyMsg"); %>
	<form name="updateAccountForm" action="BankAction.do" method="post">
		<input type="hidden" name="action" value="modifyAccount"/>
		<p>
			身份證字號：
			 <select size="1" name="id" onchange="accountSelected();">
			 	<option value="">----- 請選擇 -----</option>
			 	<c:forEach items="${accounts}" var="account">
					<option <c:if test="${account.id eq modifyAccount.id}">selected</c:if> 
						value="${account.id}">
						${account.id}
					</option>
				</c:forEach>
			</select>
		</p>		
		<p>
			帳戶名稱： 
			<input type="text" name="name" size="10" value="${modifyAccount.name}"/>
		</p>
		<p>
			帳戶密碼：
			<input type="password" name="pwd" size="10" value="${modifyAccount.pwd}"/>
		</p>
		<p>		
			帳戶餘額：<fmt:formatNumber value="${modifyAccount.balance}" type="number" pattern="$ #,###"/>		
		</p>
		<p>
			<input type="submit" value="修改">
		</p>
	</form>
	</div>
</body>
</html