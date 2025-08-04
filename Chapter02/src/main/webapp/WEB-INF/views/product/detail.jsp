<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Product Detail</title>
	
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
								<h4 class="m-0 font-weight-bold text-primary">${ product.productName }</h4>
								<h6 class="m-0">${ product.productDateToString }</h6>
							</div>
							<div class="card-body">
								<h6 class="mb-4 text-right">종류: ${ product.productKindDTO.productKindName } | 연이율: ${ product.productRate }</h6>
								<p>${ product.productContent }</p>
							</div>
						</div>
						
						<div class="d-flex mt-3 justify-content-end align-items-center">
							<form id="frm">
								<input type="hidden" name="boardNum" value="${ product.productNum }" />
							</form>
							
							<button class="btn btn-success mr-3 action" data-kind="u">Update</button>
							<button class="btn btn-danger action" data-kind="d">Delete</button>
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