<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
			<form action="/netflix/delete">
			<table border=1 align=center>
			<tr>
				<th colspan=4> Message List
			</tr>
			<tr>
					<th>seq
					<th>title
					<th>genre
					<th>reg_date
			</tr>
			<c:forEach var="i" items="${list }">
			<tr>
				<td>${i.seq}
				<td>${i.title }
				<td>${i.genre }
				<td>${i.reg_date }
			</tr>
			</c:forEach>
			<tr align =center>
				<td><input type="text" name="seq" >
			</tr>
			<tr>
				<td colspan=4 align=center>
				<a href="/"><button>back</button></a>
				<button>delete</button>
			</tr>
			</form>
			
		</table>
</body>
</html>