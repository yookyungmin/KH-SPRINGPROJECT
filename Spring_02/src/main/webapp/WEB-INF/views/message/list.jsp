<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message List</title>
</head>
<body>
		<form action="/message/delete" >
		<table border=1 align=center>
			<tr>
				<th colspan=3> Message List
			</tr>
			<tr>
					<th>seq
					<th>writer
					<th>message
			</tr>
			<c:forEach var="i" items="${list }">
			<tr>
				<td>${i.seq}
				<td>${i.writer }
				<td>${i.message }
			</tr>
			</c:forEach>
			<tr>
				<tr align =center>
				<td><input type="text" name="seq" >
			</tr>
				<td colspan=3 align=center>
				
				<button>del</button>
				<a href="/"><button type="button">back</button></a>
			</tr>
			
			
		</table>
		</form>
		
</body>
</html>