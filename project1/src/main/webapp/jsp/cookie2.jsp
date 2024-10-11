<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Cookie[] cookies = request.getCookies();

for(Cookie c: cookies){
	out.print("<p>"+c.getName()+ " : "+ c.getValue()+"<p>");
}
%>
