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
					<div class="col-8 offset-2">
						<div class="card shadow">
							<div class="card-header d-flex justify-content-between align-items-center py-3">
								<h4 class="m-0 font-weight-bold text-primary">${ notice.boardTitle }</h4>
								<h6 class="m-0">${ notice.boardDate }</h6>
							</div>
							<div class="card-body">
								<h6 class="mb-4 text-right">작성자: ${ notice.boardWriter } | 조회수: ${ notice.boardHit }</h6>
								<p>${ notice.boardContent }</p>
							</div>
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