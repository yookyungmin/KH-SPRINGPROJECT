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
	<form action="/file/upload" method="post" enctype ="multipart/form-data">
		<table  border="1px">
		<tr>
			<td><input type=text name=writer>
		</tr>
		<tr>
			<td><input type=text name=message>
			
		</tr>
		<tr>
		<td><input type=file name=file>
		</tr>
			<tr>
				<td> <button>제출</button>
			</tr>
		</table>
		
		</form>
</body>
</html>