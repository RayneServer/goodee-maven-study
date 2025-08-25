<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Index Page</title>
	
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
					<h1>Index</h1>
					<sec:authorize access="isAuthenticated()">
						<h3>Add GitHub</h3>
						
						<sec:authentication property='principal.memberId' var="memberId" />
						<sec:authentication property='principal.memberEmail' var="memberEmail" />
						
						<h3><spring:message code="welcome.message"></spring:message></h3>
						<h3><spring:message code="user.info" arguments="${ memberId }, ${ memberEmail }" argumentSeparator=","></spring:message></h3>
						<h3>Add GitHub2</h3>
					</sec:authorize>
				</div>
			</div>
			
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
		
	</div>
	
	<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
</body>

</html>
