<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Board List</title>
	
	<c:import url="/WEB-INF/views/include/head_css.jsp"></c:import>
</head>

<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/include/sidebar.jsp"></c:import>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/include/topbar.jsp"></c:import>
				<div class="container-fluid">
					<!-- Contents Area -->
					<div class="width-100 mb-5 d-flex justify-content-center align-items-center">
						<h1>${ requestScope.boardName }</h1>
					</div>
					<div class="col-8 offset-2">
						<form id="searchForm">
							<div class="input-group mb-3">
								<input type="hidden" id="pageNum" name="pageNum" value=${ pager.pageNum } />
								<select class="custom-select" name="kind">
									<option value="k1" ${ pager.kind eq 'k1' ? 'selected' : '' }>제목</option>
									<option value="k2" ${ pager.kind eq 'k2' ? 'selected' : '' }>내용</option>
									<option value="k3" ${ pager.kind eq 'k3' ? 'selected' : '' }>작성자</option>
								</select>
							  <input type="text" class="form-control" name="keyword" placeholder="검색어" value="${ pager.keyword }" />
							  <div class="input-group-append">
							    <button class="btn btn-outline-secondary" type="submit">Search</button>
							  </div>
							</div>
						</form>
					
						<table class="table align-middle text-center">
							<thead>
								<tr>
									<th scope="col" class="col-1">번호</th>
									<th scope="col" class="col-5">제목</th>
									<th scope="col" class="col-2">작성자</th>
									<th scope="col" class="col-3">작성일</th>
									<th scope="col" class="col-1">조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="board" items="${ requestScope.boardList }">
									<tr>
										<td>${ board.boardNum }</td>
										<td class="text-left">
											<a href="./detail?boardNum=${ board.boardNum }">
												<c:catch>
													<c:forEach begin="1" end="${ board.boardDepth }" varStatus="index">
														<c:if test="${ not index.last }">&nbsp;&nbsp;</c:if>
														<c:if test="${ index.last }">ㄴ</c:if>
													</c:forEach>
												</c:catch>
												${ board.boardTitle }
											</a>
										</td>
										<td>${ board.boardWriter }</td>
										<td>${ board.boardDateToString }</td>
										<td>${ board.boardHit }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<div class="d-flex w-100 justify-content-between align-items-center">
							<nav class="text-center">
							  <ul class="pagination m-0">
							    <li class="page-item ${ pager.startBlockNum eq 1 ? 'disabled' : '' }">
							      <a class="page-link page-num" data-page-num="${ pager.startBlockNum - 1 }" >
							        <span aria-hidden="true">&laquo;</span>
							      </a>
							    </li>
							    <c:forEach begin="${ pager.startBlockNum }" end="${ pager.endBlockNum }" var="i">
								    <li class="page-item"><a class="page-link page-num" data-page-num="${ i }" >${ i }</a></li>
									</c:forEach>
							    <li class="page-item ${ pager.endBlockNum eq pager.totalPage ? 'disabled' : '' }">
							      <a class="page-link page-num" data-page-num="${ pager.endBlockNum + 1 }" >
							        <span aria-hidden="true">&raquo;</span>
							      </a>
							    </li>
							  </ul>
							</nav>
							
							<a href="./add" class="btn btn-primary">Add</a>
						</div>
					</div>
				</div>
			</div>
			
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
		
	</div>
	
	<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
	<script src="/js/board/board-list.js"></script>
</body>

</html>