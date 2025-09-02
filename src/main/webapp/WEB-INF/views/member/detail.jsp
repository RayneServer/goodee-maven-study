<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>My Data</title>
	
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
						<div class="text-center mb-4">
	            <h1 class="h3 mb-0 text-gray-800">My Information</h1>
	          </div>
	          
	          <sec:authentication property="principal" var="member"/>
	          
	          <div class="row">
	          	<div class="col-12 mb-4">
	              <div class="card shadow mb-4">
	                <div class="card-header py-3">
	                    <h6 class="m-0 font-weight-bold text-primary">Profile</h6>
	                </div>
	                <div class="card-body">
	                  <div class="text-center">
	                    <img class="img-fluid px-3 mt-3 mb-4" src="/files/member/${ member.memberProfileDTO.savedName }" >
	                  </div>
	                </div>
	              </div>
	            </div>
	          </div>
	          
	          <div class="row">
	          	<div class="col-6 mb-4">
	              <div class="card border-left-primary shadow h-100 py-2">
	                <div class="card-body">
	                  <div class="row no-gutters align-items-center">
	                    <div class="col mr-2">
	                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">My ID</div>
	                      <div class="h5 mb-0 font-weight-bold text-gray-800">${ member.memberId }</div>
	                    </div>
	                    <div class="col-auto">
	                      <i class="fas fa-user fa-2x text-gray-300"></i>
	                    </div>
	                  </div>
	                </div>
	              </div>
	            </div>
	            
	            <div class="col-6 mb-4">
	              <div class="card border-left-success shadow h-100 py-2">
	                <div class="card-body">
	                  <div class="row no-gutters align-items-center">
	                    <div class="col mr-2">
	                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">My Phone</div>
	                      <div class="h5 mb-0 font-weight-bold text-gray-800">${ member.memberPhone }</div>
	                    </div>
	                    <div class="col-auto">
	                      <i class="fas fa-phone fa-2x text-gray-300"></i>
	                    </div>
	                  </div>
	                </div>
	              </div>
	            </div>
	          </div>
	          
	          <div class="row">
	          	<div class="col-6 mb-4">
	              <div class="card border-left-info shadow h-100 py-2">
	                <div class="card-body">
	                  <div class="row no-gutters align-items-center">
	                    <div class="col mr-2">
	                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">My E-mail</div>
	                      <div class="h5 mb-0 font-weight-bold text-gray-800">${ member.memberEmail }</div>
	                    </div>
	                    <div class="col-auto">
	                      <i class="fas fa-envelope fa-2x text-gray-300"></i>
	                    </div>
	                  </div>
	                </div>
	              </div>
	            </div>
	            
	            <div class="col-6 mb-4">
	              <div class="card border-left-warning shadow h-100 py-2">
	                <div class="card-body">
	                  <div class="row no-gutters align-items-center">
	                    <div class="col mr-2">
	                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">My Birth</div>
	                      <div class="h5 mb-0 font-weight-bold text-gray-800">${ member.memberBirth }</div>
	                    </div>
	                    <div class="col-auto">
	                      <i class="fas fa-calendar fa-2x text-gray-300"></i>
	                    </div>
	                  </div>
	                </div>
	              </div>
	            </div>
	            
	            <a href="delete" class="btn btn-danger">회원탈퇴</a>
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