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
		
	</script>
</head>
<body>
	<%@ include file="Bank_FunMenu.jsp" %>
	<br/><br/><HR>		
	<h2>帳戶列表</h2><br/>
	<div style="margin-left:25px;">
	<p style="color:blue;">${sessionScope.deleteMsg}</p> 
	<% session.removeAttribute("deleteMsg"); %> <!-- 不浪費server空間 -->
	<table border="1">
		<tbody>
			<tr height="50" align="center">
				<td width="100"><b>身份證字號</b></td>
				<td width="100"><b>帳戶名稱</b></td> 
				<td width="100"><b>帳戶密碼</b></td>
				<td width="100"><b>帳戶餘額</b></td>
				<td width="100"><b>帳戶刪除</b></td>
			</tr>
			<c:forEach items="${accounts}" var="account">
				<tr height="30"  align="center">
					<td>${account.id}</td>
					<td>${account.name}</td>
					<td>${account.pwd}</td>					
					<td><fmt:formatNumber value="${account.balance}" type="number" pattern="$ #,###"/></td>
					<td>
					    <c:url value="/BankAction.do" var="deleUrl">
					        <c:param name="action" value="deleteAccount"/>
					        <c:param name="id" value="${account.id}"/>
					    </c:url>
						<a href="${deleUrl}">刪除</a>
					</td>		
				</tr>				
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>