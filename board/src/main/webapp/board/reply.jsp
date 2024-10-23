<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="dontainer-fluid">
	<h1 class="h3 mb-4 text-gray-800">Reply</h1>
	<form method="post" action="/reply.do" id="reply">
		<div class="form-group">
			<label for="title">제목</label> <input type="text" class="form-control"
				id="title" name="title" required="required" value="Re : ${read.title}">
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
			<label for="password">비밀번호</label> <input type="password"
				class="form-control" id="password" name="password"
				required="required">
		</div>
		<!-- 부모글 정보 -->
		<input type="hidden" name="reRef" value="${read.reRef}" />
		<input type="hidden" name="reLev" value="${read.reLev}" />
		<input type="hidden" name="reSeq" value="${read.reSeq}" />
		<input type="hidden" name="bno" value="${read.bno}" />
		<button type="submit" class="btn btn-primary">작성완료</button>
		<button type="button" class="btn btn-success">목록</button>
	</form>
</div>
<form action="" method="get" id="actionForm">
</form>
<script src="/js/custom/reply.js"></script>
<%@ include file="../include/footer.jsp"%>