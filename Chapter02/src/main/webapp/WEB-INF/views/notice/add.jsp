<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
						<form method="POST">
							<input type="hidden" name="boardNum" value="${ notice.boardNum }" />
						
							<label for="noticeWriter" class="form-label">Writer</label>
							<input type="text" class="form-control mb-3" id="boardWriter" name="boardWriter" value="${ notice.boardWriter }" <c:if test="${ not empty notice }">readonly</c:if> />
							
							<label for="noticeTitle" class="form-label">Title</label>
							<input type="text" class="form-control mb-3" id="boardTitle" name="boardTitle" value="${ notice.boardTitle }" />
							
							<label for="noticeContent" class="form-label">Content</label>
							<textarea class="form-control mb-3" rows="9" id="boardContent" name="boardContent" style="resize: none;">${ notice.boardContent }</textarea>
							
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>
					</div>
				</div>
			</div>
			
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
		
	</div>
	
	<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
</body>

</html>