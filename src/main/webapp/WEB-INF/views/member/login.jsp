<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Log-In</title>
	
	<c:import url="/WEB-INF/views/include/head_css.jsp"></c:import>
</head>

<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/include/sidebar.jsp"></c:import>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/include/topbar.jsp"></c:import>
				<div class="container-fluid">
					<div class="col-4 offset-4">
            <div class="p-5">
              <div class="text-center">
                <h1 class="h4 text-gray-900 mt-5 mb-4">Welcome Back!</h1>
              </div>
              <form method="POST">
                <div class="form-group">
                  <input type="text" class="form-control form-control-user" id="memberId" name="memberId" placeholder="ID" value="${ cookie.rememberId.value }">
                </div>
                <div class="form-group">
                  <input type="password" class="form-control form-control-user" id="memberPw" name="memberPw" placeholder="Password">
                </div>  
                
                <div>
	                <input type="checkbox" class="form-check-input ml-2" id="rememberId" name="rememberId" value="1" <c:if test="${ not empty cookie.rememberId.value }">checked</c:if> />
	                <label for="rememberId" class="form-label ml-4">아이디 기억</label>
	                
	                <input type="checkbox" class="form-check-input ml-2" id="rememberMe" name="rememberMe" value="1" />
	                <label for="rememberMe" class="form-label ml-4">자동 로그인</label>
                </div>
                <button type="submit" class="btn btn-primary btn-user btn-block">Login</button>
              </form>
              <a href="/oauth2/authorization/kakao" class="btn btn-primary btn-user btn-block mt-2">카카오 로그인</a>
              <hr>
              <div class="text-center">
                <a class="small" href="#">Forgot Password?</a>
              </div>
              <div class="text-center">
                <a class="small" href="join">Create an Account!</a>
              </div>
            </div>
        	</div>
				</div>
			</div>
			
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
		
	</div>
	
	<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
	<script>
		const failMsg = "${ param.failMsg }";
		if (failMsg != null && failMsg != "") {
			Swal.fire({ icon: "error", text: failMsg });
		}
	</script>
</body>

</html>