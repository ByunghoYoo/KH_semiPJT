<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%
	String msg = (String)request.getAttribute("error-msg");
	Exception e = (Exception)request.getAttribute("exception");
	// 일반적으로 jsp에 사용되는 exception 내장 객체는 jspException 클래스를 활용한다.
	// 따라서 사용자 정의 예외를 구현할 때에는 Exception 객체를 직접 선언하여 사용하여야 한다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Service ERROR</title>
</head>
<body>
<%@ include file="../common/header.jsp" %>
	<div class="container">
		<div class="row">
			<div class="col-md-6 mx-auto">
			<h1>서비스 에러 발생!!!</h1>
			<h3 style="color:red;">ERROR : <%= e.getMessage() %></h3>
			<h4><span>서</span>비스 수행 중 에러가 발생했습니다. 전송 값을 확인해 보시고,<br>
			이상이 없을 시 부서 담당자에게 연락하시기 바랍니다.</h4>
			</div>
		</div>
	</div>
	
	<div style="height : 300px;"></div>
	
<%@ include file="../common/footer.jsp" %>
</body>
</html>