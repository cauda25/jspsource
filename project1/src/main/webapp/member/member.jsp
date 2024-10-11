<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
*{
margin: 0;
padding: 0;
box-sizing: border-dox;
}
table,td{
margin-top:10px;
padding: 10px;
display:flex;
justify-content: center;
}

</style>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String name = request.getParameter("name");
String password = request.getParameter("password");
String tel = request.getParameter("tel");
%>

<table>
<tr>
<th>입력하신 회원가입 정보입니다.</th>
</tr>
	<tr>
	<td>ID : <%=id %></td>
	<td>NAME : <%=name %></td>
	<td>PASSWORD : <%=password %></td>
	<td>TEL : <%=tel %></td>
	</tr>
</table>
</body>
</html>