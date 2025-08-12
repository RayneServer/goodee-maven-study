<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Join Member</title>
	
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
                <h1 class="h4 text-gray-900 my-5">Create an Account!</h1>
              </div>
              <form method="POST" enctype="multipart/form-data">
                <div class="form-group row">
                  <div class="col-6">
                     <input type="text" class="form-control" id="memberId" name="memberId" placeholder="ID">
                  </div>
                  <div class="col-6">
                    <input type="password" class="form-control" id="memberPw" name="memberPw" placeholder="Password">
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-6">
                    <input type="text" class="form-control" id="memberName" name="memberName" placeholder="Name">
                  </div>
                  <div class="col-6">
                  	<input type="date" class="form-control" id="memberBirth" name="memberBirth" >
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-6">
                     <input type="text" class="form-control" id="memberEmail" name="memberEmail" placeholder="Email">
                  </div>
                  <div class="col-6">
                  	<input type="text" class="form-control" id="memberPhone" name="memberPhone" placeholder="Phone">
                  </div>
                </div>
                <div class="form-group row">
                	<div class="col-4">
	                	<button type="button" id="profileBtn" class="btn btn-primary mb-3">Add Profile Icon</button>
                	</div>
                	<div id="profileBlock" class="col-8"></div>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Register Account</button>
              </form>
              <hr>
              <div class="text-center">
                <a class="small" href="forgot-password.html">Forgot Password?</a>
              </div>
              <div class="text-center">
                <a class="small" href="login.html">Already have an account? Login!</a>
              </div>
            </div>
        	</div>
				</div>
			</div>
			
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
		
	</div>
	
	<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
	<script src="/js/member/member-join.js"></script>
</body>

</html>