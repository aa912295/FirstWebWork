<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="/" var="WEB_PATH"/>
<c:url value="/js" var="JS_PATH"/>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-tw">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>BankLogin</title>
<script src="${JS_PATH}/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#accountID").bind("change",function(){
				
				var accountID = $("#accountID option:selected").val();
				var accountParam ={id : accountID};
				if(accountID != ""){
					$.ajax({
					  url: '${WEB_PATH}BankAction.do?action=getModifyAccount', // 指定要進行呼叫的位址
					  type: "GET", // 請求方式 POST/GET
					  data: accountParam , // 傳送至 Server的請求資料(物件型式則為 Key/Value pairs)
					  dataType : 'JSON', // Server回傳的資料類型
					  success: function(accountInfo) { // 請求成功時執行函式
					  	$("#accountName").val(accountInfo.name);
					  	$("#accountPwd").val(accountInfo.pwd);
					  	$("#balanceDiv").html(accountInfo.balanceText);
					  },
					  error: function(error) { // 請求發生錯誤時執行函式
					  	alert("Ajax Error!");
					  }
					});
				}else{
				  	$("#accountName").val('');
				  	$("#accountPwd").val('');
				  	$("#balanceDiv").empty();
				}
			});
		});
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
			 <select size="1" id="accountID" name="id">
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
			<input type="text" id="accountName" name="name" size="10" value="${modifyAccount.name}"/>
		</p>
		<p>
			帳戶密碼：
			<input type="password" id="accountPwd" name="pwd" size="10" value="${modifyAccount.pwd}"/>
		</p>
		<p>
			<div style="display:inline-block;">帳戶餘額：</div>
			<div id="balanceDiv" style="display:inline-block;">
				<fmt:formatNumber value="${modifyAccount.balance}" type="number" pattern="$ #,###"/>
			</div>			
		</p>
		<p>
			<input type="submit" value="修改">
		</p>
	</form>
	</div>
</body>
</html>