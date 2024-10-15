<%@page import="dto.BookDTO"%>
<%@page import="dao.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	// pk,수정할 컬럼 가져오기
	BookDTO updateDto = new BookDTO();
	updateDto.setCode(Integer.parseInt(request.getParameter("code")));
	updateDto.setPrice(Integer.parseInt(request.getParameter("price")));
	updateDto.setDescription(request.getParameter("description"));
	
	// db 작업 
	BookDAO dao = new BookDAO();
    int updateRow = dao.update(updateDto);
    
    // 결과값 공유(수정,삭제,삽입 x)
    
    // 페이지 이동
    // 0 이 리턴되면 modify_pro.jsp
    // 1 이 리턴되면 list_pro.jsp
    if(updateRow == 0){
    	response.sendRedirect("modify_pro.jsp?code="+ updateDto.getCode());
    } else{
    	response.sendRedirect("list_pro.jsp");
    }
%>