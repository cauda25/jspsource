<%@page import="dao.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
//가져올 값(==넘어오는 값)이 있는지 확인
//시작하는 페이지에서 form 이 존재 하는 경우
//a 태그 ? 다음에 넘어오는지 
int code = Integer.parseInt(request.getParameter("code"));
// db 작업 
BookDAO dao = new BookDAO();
int deleteRow = dao.delete(code);
// 페이지 이동
// 0 이 리턴되면 modify_pro.jsp
// 1 이 리턴되면 list_pro.jsp
if(deleteRow == 0){
	response.sendRedirect("modify_pro.jsp?code="+code); 
}else{
	response.sendRedirect("list_pro.jsp");
}
%>