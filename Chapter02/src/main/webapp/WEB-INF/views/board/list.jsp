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
					<div class="row col-8 offset-2">
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
						
						<div class="d-flex w-100 justify-content-end align-items-center">
							<a href="./add" class="btn btn-primary">Add</a>
						</div>
					</div>
				</div>
			</div>
			
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
		
	</div>
	
	<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
</body>

</html>