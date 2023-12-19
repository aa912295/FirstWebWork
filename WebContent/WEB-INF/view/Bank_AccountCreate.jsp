<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-tw">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>BankLogin</title>
	<script type="text/javascript">
		
	</script>
</head>
<body>
	<%@ include file="Bank_FunMenu.jsp" %>
	<br/><br/><HR>		
	<h2>帳戶新增</h2><br/>
	<div style="margin-left:25px;">
	<p style="color:blue;">${sessionScope.createMsg}</p>
	<% session.removeAttribute("createMsg"); %>
	<form action="BankAction.do?action=createAccount" method="post">
		<p>
			身份證字號：
			<input type="text" name="id" size="10"/>
		</p>		
		<p>
			帳戶名稱： 
			<input type="text" name="name" size="10"/>
		</p>
		<p>
			帳戶密碼：
			<input type="password" name="pwd" size="10"/>
		</p>
		<p>
			開戶金額：
			<input type="number" name="balance" size="10"/>			
		</p>
		<p>
			<input type="submit" value="新增">
		</p>
	</form>
	</div>
</body>
</html>