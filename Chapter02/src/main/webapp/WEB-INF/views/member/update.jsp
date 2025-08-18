<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Update Profile</title>
	
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
            <div class="p-5">
              <div class="text-center">
                <h1 class="h4 text-gray-900 my-5">회원 정보 수정</h1>
              </div>
              <form:form method="POST" modelAttribute="memberDTO" enctype="multipart/form-data">
              	<form:hidden path="memberId"/>
                <div class="form-group row">
                  <div class="col-6">
                  	<form:input path="memberName" cssClass="form-control" placeholder="Name" />
                  	<form:errors path="memberName"></form:errors>
                  </div>
                  <div class="col-6">
                  	<form:input type="date" path="memberBirth" cssClass="form-control" value="${ memberDTO.memberBirth }" />
                  	<form:errors path="memberBirth"></form:errors>
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-6">
                  	<form:input path="memberEmail" cssClass="form-control" placeholder="Email" />
                  	<form:errors path="memberEmail"></form:errors>
                  </div>
                  <div class="col-6">
                  	<form:input path="memberPhone" cssClass="form-control" placeholder="Phone" />
                  	<form:errors path="memberPhone"></form:errors>
                  </div>
                </div>
                <div class="form-group row">
                	<div class="col-4">
	                	<button type="button" id="profileBtn" class="btn btn-primary mb-3">Add Profile Icon</button>
                	</div>
                	<div id="profileBlock" class="col-8"></div>
                </div>
                <form:button type="submit" class="btn btn-primary btn-block">수정</form:button>
              </form:form>
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