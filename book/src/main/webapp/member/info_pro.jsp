<%@page import="dto.ChangeDTO"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	
	ChangeDTO changeDto = new ChangeDTO();
	changeDto.setChangePassword(request.getParameter("change_password"));
	changeDto.setUserid(request.getParameter("userid"));
	changeDto.setCurrentPassword(request.getParameter("current_password"));

	MemberDAO dao = new MemberDAO();
	int updateRow = dao.update(changeDto);
	
	if(updateRow==1){
		session.invalidate(); // session.removeAttribute("login.sjp")
		response.sendRedirect("login.jsp");
	} else {
		response.sendRedirect("info.jsp");
	}
%>