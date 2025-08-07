<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Notice Detail</title>
	
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
						<div class="card shadow">
							<div class="card-header d-flex justify-content-between align-items-center py-3">
								<h4 class="m-0 font-weight-bold text-primary">${ board.boardTitle }</h4>
								<h6 class="m-0">${ board.boardDateToString }</h6>
							</div>
							<div class="card-body">
								<h6 class="mb-4 text-right">작성자: ${ board.boardWriter } | 조회수: ${ board.boardHit }</h6>
								<c:forEach var="boardFile" items="${ board.boardFileDTOs }">
									<img width="100%" alt="" src="/files/${ requestScope.boardName }/${ boardFile.savedName }">
								</c:forEach>
								<p>${ board.boardContent }</p>
							</div>
						</div>
						
						<div class="d-flex mt-3 justify-content-end align-items-center">
							<form id="frm">
								<input type="hidden" name="boardNum" value="${ board.boardNum }" />
							</form>
							
							<c:if test="${ requestScope.boardName ne 'Notice' }">
								<button class="btn btn-primary action" data-kind="r">Reply</button>
							</c:if>
							<button class="btn btn-success ml-3 action" data-kind="u">Update</button>
							<button class="btn btn-danger ml-3 action" data-kind="d">Delete</button>
						</div>
					</div>
				</div>
			</div>
			
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
		
	</div>
	
	<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
	<script src="/js/board/board-detail.js"></script>
</body>

</html>