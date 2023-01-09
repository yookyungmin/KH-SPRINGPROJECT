<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <script src="https://code.jquery.com/jquery-3.6.1.js"></script>
</head>
<body>
	<c:choose>
	<c:when test="${loginID != null}">
	${loginID}로 로그이 되었씁니다
	</c:when>
	<c:otherwise>
	로그인 되지 않습니다
	</c:otherwise>
	</c:choose>
	<a href="/member/login">login</a>
	<a href="/member/logout">logout</a>
	<hr>
	<a href="/memberOnly">록이한 사람만 볼수 있는 페이지</a>
	<a href="/everyone">모두 볼수 있는 페이지</a>
</body>
</html>
