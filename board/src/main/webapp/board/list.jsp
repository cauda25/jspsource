<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="dontainer-fluid">
	<h1 class="h3 mb-4 pl-4 text-gray-800">List</h1>
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<form action="/list.do" id="searchForm">
					<input type="hidden" name="page" value="${pDto.serDTO.page}" />
					<input type="hidden" name="amount" value="${pDto.serDTO.amount}" />
				<select name="criteria" id="criteria" class="form-select">
					<option value="n"
						<c:out value="${sdto.criteria == null?'selected':''}"/>>-------</option>
					<option value="title"
						<c:out value="${sdto.criteria == null?'selected':''}"/>>title</option>
					<option value="content"
						<c:out value="${sdto.criteria == null?'selected':''}"/>>content</option>
					<option value="name"
						<c:out value="${sdto.criteria == null?'selected':''}"/>>name</option>
				</select> 
				<input type="text" name="keyword" id="keyword" value="${sdto.keyword}" />
				<button type="submit" class="btn btn-warning">검색</button>
			</form>

		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<div>
						<thead>
							<tr>
								<th>bno</th>
								<th>title</th>
								<th>regdate</th>
								<th>name</th>
								<th>readcnt</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="dto" items="${list}">
								<tr>
									<td>${dto.bno}</td>
									<td><c:if test="${dto.reLev!=0}">
											<c:forEach begin="0" end="${dto.reLev*1}">
								&nbsp;
								</c:forEach>
										</c:if> <a href="${dto.bno}" class="text-decoration-none text-reset">${dto.title}</a>
									</td>
									<td>${dto.regdate}</td>
									<td>${dto.name}</td>
									<td>${dto.readcnt}</td>
								</tr>
							</c:forEach>
						</tbody>
				</table>
			</div>
			<div class="row">
				<div class="col-sm-12 col-md-12">
					<ul class="pagination justify-content-center">
						
						<c:if test="${pDto.prev}">
							<li class="paginate_button page-item previous">
								<a href="${pDto.startPage-1}" class="page-link">previous</a>
							</li>
						</c:if>
						
						<c:forEach begin="${pDto.startPage}" end="${pDto.endPage}" var="idx">
							<li class="paginate_button page-item <c:out value="${pDto.serDTO.page == idx?'active':'' }"/>" >
									<a href="${idx}" class="page-link">${idx}</a>
								</li>
						</c:forEach>
						
						<c:if test="${pDto.next}">
							<li class="paginate_button page-item next">
								<a href="${pDto.endPage+1}" class="page-link">next</a>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
		<form action="" method="get" id="actionForm">
			<input type="hidden" name="bno" value="" />
			<input type="hidden" name="page" value="${pDto.serDTO.page}" />
			<input type="hidden" name="amount" value="${pDto.serDTO.amount}" />
			<input type="hidden" name="criteria" value="${pDto.serDTO.criteria}" />
			<input type="hidden" name="keyword" value="${pDto.serDTO.keyword}" />
		</form>
		<script src="/js/custom/list.js"></script>
		<%@ include file="../include/footer.jsp"%>