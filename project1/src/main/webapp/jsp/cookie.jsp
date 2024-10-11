<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String name = request.getParameter("name");
String age = request.getParameter("age");

Cookie cookie1 = new Cookie("name",name);
Cookie cookie2 = new Cookie("age",name);

response.addCookie(cookie1);
response.addCookie(cookie2);
%>
<h1>쿠키테스트</h1>
<h3><a href="cookie2.jsp">쿠키확인</a></h3>
