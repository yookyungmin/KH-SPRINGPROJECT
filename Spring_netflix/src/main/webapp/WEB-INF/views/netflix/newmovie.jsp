<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>netflix</title>
</head>
<body>


		<form action="/netflix/insert">
		<table border=1 align=center>
		<tr>
			<th>Input
		</tr>
		<tr>
				<td><input type="text" name=title placeholder="input title">
		</tr>
		<tr>
				<td><input type="text" name=genre placeholder="input genre">
		</tr>
		<tr>
			<td align=center><button>Submit</button>
			<a href="/"><button type="button">back home</button></a> 
		</tr>
		</table>
		</form>
</body>
</html>