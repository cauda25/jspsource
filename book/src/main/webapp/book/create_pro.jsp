<%@page import="dto.BookDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setCharacterEncoding("utf-8");

BookDTO insertDto = new BookDTO();
insertDto.setCode(Integer.parseInt(request.getParameter("code")));
insertDto.setTitle(request.getParameter("title"));
insertDto.setWriter(request.getParameter("writer"));
insertDto.setPrice(Integer.parseInt(request.getParameter("price")));
insertDto.setDescription(request.getParameter("description"));
%>