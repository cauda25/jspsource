<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="dontainer-fluid">
	<h1 class="h3 mb-4 text-gray-800">Create</h1>
	<%--
	 enctype="application/x-www-form-urlencoded"(기본)
	 
	 첨부파일
	 enctype="multipart/form-data"	
	--%>
	<form method="post" action="/create.do" enctype="multipart/form-data">
		<div class="form-group">
			<label for="title">제목</label> <input type="text" class="form-control"
				id="title" name="title" required="required">
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea rows="15" class="form-control" id="content" name="content"
				required="required"></textarea>
		</div>
		<div class="form-group">
			<label for="name">작성자</label> <input type="text" class="form-control"
				id="name" name="name" required="required">
		</div>
		<div class="form-group">
			<label for="fileF">첨부파일</label> <input type="file"
				class="form-control" id="fileF" name="fileF">
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label> <input type="password"
				class="form-control" id="password" name="password"
				required="required">
		</div>
			<input type="hidden" name="page" value="1" />
			<input type="hidden" name="amount" value="10" />
			<input type="hidden" name="criteria" value="" />
			<input type="hidden" name="keyword" value="" />
		<button type="submit" class="btn btn-primary">작성완료</button>
	</form>
</div>

<%@ include file="../include/footer.jsp"%>