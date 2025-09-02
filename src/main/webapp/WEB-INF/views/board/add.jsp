<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Notice Add</title>
	
	<c:import url="/WEB-INF/views/include/head_css.jsp"></c:import>
</head>

<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/include/sidebar.jsp"></c:import>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/include/topbar.jsp"></c:import>
				<div class="container-fluid">
					<div class="col-6 offset-3">
						<!-- action을 명시하지 않으면 요청을 이 페이지의 url로 보냄 -->
						<form:form method="POST" modelAttribute="boardVO" enctype="multipart/form-data">
							<form:hidden path="boardNum" />
						
							<div class="mb-3">
								<label for="boardWriter" class="form-label">Writer</label>
								<form:input cssClass="form-control" path="boardWriter" readonly="true" />	
							</div>
														
							<div class="mb-3">
								<label for="boardTitle" class="form-label">Title</label>
								<form:input path="boardTitle" cssClass="form-control" />
								<form:errors path="boardTitle"></form:errors>
							</div>
							
							<div class="mb-3">
								<label for="boardContent" class="form-label">Content</label>
								<form:textarea cssClass="form-control" rows="9" path="boardContent" style="resize: none;"></form:textarea>
							</div>
							
							<div class="d-flex align-items-center">
								<button type="button" id="addBtn" class="btn btn-primary mr-3">Add</button>
								<span>현재 첨부파일: </span>
								<c:forEach var="file" items="${ board.boardFileDTOs }">
									<button class="deleteFile btn btn-outline-secondary py-0 ml-2" type="button" style="width: 80px; overflow: hidden; text-overflow: ellipsis;" data-file-num="${ file.fileNum }">${ file.originName }</button>
								</c:forEach>
							</div>
								
							<div id="addResult" data-file-count="${ fn:length(board.boardFileDTOs) }">

							</div>

							<div class="mt-3">
								<form:button type="submit" class="btn btn-primary">Submit</form:button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
			
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
		
	</div>
	
	<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
	<script src="/js/board/board-add.js"></script>
</body>

</html>