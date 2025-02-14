<%@page import="java.net.URLEncoder"%>
<%@page import="dto.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="dontainer-fluid">
	<h1 class="h3 mb-4 text-gray-800">Read</h1>
	<form id="readForm">
		<div class="form-group">
			<label for="bno">번호</label> <input type="text" class="form-control"
				id="bno" name="bno" value="${read.bno}" readonly>
		</div>
		<div class="form-group">
			<label for="title">제목</label> <input type="text" class="form-control"
				id="title" name="title" value="${read.title}" readonly>
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea rows="15" class="form-control" id="content" name="content"
				readonly>${read.content}</textarea>
		</div>
		<div class="form-group">
			<label for="fileF">첨부파일</label>
			<%
			BoardDTO dto = (BoardDTO) request.getAttribute("read");
			String encodeFileName = "";

			if (dto.getFileF() != null) {
				encodeFileName = URLEncoder.encode(dto.getFileF(), "utf-8");
			}
			%>
			<div>
				<a href="/board/download.jsp?fileName=<%=encodeFileName%>">${read.fileF}</a>
			</div>
		</div>
		<div class="form-group">
			<label for="name">작성자</label> <input type="text" class="form-control"
				id="name" name="name" value="${read.name}" readonly>
		</div>
		<input type="hidden" name="page" value="${sDto.page}" />
		<input type="hidden" name="amount" value="${sDto.amount}" />
		<input type="hidden" name="criteria" value="${sDto.criteria}" />
		<input type="hidden" name="keyword" value="${sDto.keyword}" />
		<button type="button" class="btn btn-info">수정</button>
		<button type="button" class="btn btn-secondary">답변</button>
		<button type="button" class="btn btn-success">목록</button>
	</form>
</div>
<form action="" method="get" id="actionForm">
	<input type="hidden" name="bno" value="${read.bno}" />
	<input type="hidden" name="page" value="${sDto.page}" />
	<input type="hidden" name="amount" value="${sDto.amount}" />
	<input type="hidden" name="criteria" value="${sDto.criteria}" />
	<input type="hidden" name="keyword" value="${sDto.keyword}" />
</form>
<script src="/js/custom/read.js"></script>
<%@ include file="../include/footer.jsp"%>