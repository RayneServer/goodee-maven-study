<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Product Form</title>
	
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
					<div class="col-6 offset-3">
						<div class="text-center">
							<h1>Add Product</h1>
						</div>
						<form method="POST">
							<div class="row mt-4">
								<div class="col-6">
									<label for="productName" class="form-label">상품이름</label>
									<input type="text" class="form-control" id="productName" name="productName" value="${ product.productName }" />
								</div>
								<div class="col-6">
									<label for="productKindNum" class="form-label">상품종류</label>
									<select class="custom-select" id="productKindNum" name="productKindNum">
										<option <c:if test="${ empty product }">selected</c:if>>-- 선택 --</option>
										<option value="1" <c:if test="${ product.productKindNum eq 1 }">selected</c:if>>예금</option>
										<option value="2" <c:if test="${ product.productKindNum eq 2 }">selected</c:if>>적금</option>
										<option value="3" <c:if test="${ product.productKindNum eq 3 }">selected</c:if>>대출</option>
									</select>
								</div>
							</div>
							
							<div class="row mt-4">
								<div class="col-6">
									<label for="productDate" class="form-label">마감기한</label>
									<input type="date" class="form-control" id="productDate" name="productDate" value="${ product.productDate }" />
								</div>
								<div class="col-6">
									<label for="productRate" class="form-label">상품이율</label>
									<input type="text" class="form-control" id="productRate" name="productRate" value="${ product.productRate }" />
								</div>
							</div>
							
							<label for="productContent" class="form-label mt-4">내용</label>
							<textarea class="form-control" rows="15" id="productContent" name="productContent" style="resize: none;">${ product.productContent }</textarea>
							
							<div class="width-100 mt-5 d-flex justify-content-center align-items-center">
								<button type="submit" class="btn btn-primary">등록</button>
							</div>
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