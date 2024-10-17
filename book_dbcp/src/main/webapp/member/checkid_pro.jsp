<%@page import="dto.MemberDTO"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String userid = request.getParameter("userid");
//System.out.print(userid);

MemberDAO dao = new MemberDAO();
boolean dupId = dao.dupId(userid);

if(dupId){
	out.print("true");
}else{
	out.print("false");
}
%>