<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Product List</title>
	
	<c:import url="/WEB-INF/views/include/head_css.jsp"></c:import>
</head>

<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/include/sidebar.jsp"></c:import>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/include/topbar.jsp"></c:import>
				<div class="container-fluid">
					<div class="col-8 offset-2">
						<div class="width-100 mb-5 d-flex justify-content-center align-items-center">
							<h1>Product List</h1>
						</div>
						
						<table class="table text-center">
							<thead>
								<tr">
									<th class="col-1">상품번호</th>
									<th class="col-5">상품명</th>
									<th class="col-2">이율</th>
									<th class="col-4">마감일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="product" items="${ productList }">
									<tr>
										<td>${ product.productNum }</td>
										<td><a href="detail?productNum=${ product.productNum }">${ product.productName }</a></td>
										<td>${ product.productRate }</td>
										<td>${ product.productDateToString }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<div class="width-100 mt-5 d-flex justify-content-center align-items-center">
							<a href="add" class="btn btn-primary">상품등록</a>
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