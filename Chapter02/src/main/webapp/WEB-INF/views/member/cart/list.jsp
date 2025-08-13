<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>My Cart</title>
	
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
						<div class="text-center my-4">
							<h1>내 장바구니</h1>
							<input type="checkbox" class="form-check-input" id="checkAll">
							<label for="chechAll" class="form-check-label">전체 선택</label>
						</div>
						
						<c:forEach var="product" items="${ productList }">
							<div class="card col-12 my-3">
							  <div class="card-body">
							  	<div class="d-flex justify-content-between align-items-center mb-3">
								    <input type="checkbox" class="form-check-input m-0 cartCheck" value="${ product.productNum }">
								    <h4 class="card-title mb-0 ml-4">${ product.productName }</h4>
							  	</div>
							    <h6 class="card-subtitle mb-2 text-muted">마감일: ${ product.productDateToString }</h6>
							    <p class="card-text">${ product.productContent }</p>
							    <a href="/product/detail?productNum=${ product.productNum }" class="card-link">상세 보기</a>
							  </div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
		
	</div>
	
	<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
	<script src="/js/member/cart/member-cart-list.js"></script>
</body>

</html>