<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<h1>Bank Account Service</h1>
<p style="color:blue;">
	${sessionScope.account.name} 先生/小姐您好!
	<a href="LoginAction.do?action=logout" align="left">(登出)</a>
</p>
<br/>
<table class="table table-hover" border="1" style="border-collapse:collapse;margin-left:25px;">
	<tr>
		<td width="200" height="50" align="center">
			<a href="BankAction.do?action=queryAllAccount">帳戶列表 / 帳戶刪除</a>
		</td>
		<td width="200" height="50" align="center">
			<a href="BankAction.do?action=modifyAccountView">帳戶修改</a>
		</td>
		<td width="200" height="50" align="center">
			<a href="BankAction.do?action=createAccountView">帳戶新增</a>
		</td>
	</tr>
</table>