<%@page import="dao.MemberDAO"%>
<%@page import="dto.ChangeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// password.jsp 에서 넘긴 값 가져와서 ChangeDTO에 담기
ChangeDTO changeDTO = new ChangeDTO();
changeDTO.setUserid(request.getParameter("userid"));
changeDTO.setCurentPassword(request.getParameter("curent_password"));
changeDTO.setChangePassword(request.getParameter("change_password"));

MemberDAO dao = new MemberDAO();
int updateRow = dao.update(changeDTO);

if (updateRow == 1) {
// updateRow == 1 일 때 세션 종료 / login.jsp 로 이동
	session.invalidate();
	response.sendRedirect("login.jsp");
} else {
// updateRow == 0 일 때 password.jsp 로 이동
	response.sendRedirect("password.jsp");
}
%>
