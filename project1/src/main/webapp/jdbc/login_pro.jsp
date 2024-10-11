<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page import="dto.MemberDTO"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
MemberDTO dto= new MemberDTO();
dto.setUserid(request.getParameter("userid"));
dto.setPassword(request.getParameter("password"));

MemberDAO dao = new MemberDAO();
MemberDTO loginDto = dao.login(dto);

if(loginDto!=null){
	session.setAttribute("loginDto", loginDto);
	response.sendRedirect("main.jsp");
}else{
	response.sendRedirect("login.jsp");
}

%>