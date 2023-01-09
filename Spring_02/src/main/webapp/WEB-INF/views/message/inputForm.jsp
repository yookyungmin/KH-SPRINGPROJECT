<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Input Form</title>
</head>
<body>
		<form action="/message/insert">
		<table border=1 align=center>
		<tr>
			<th>Input
		</tr>
		<tr>
				<td><input type="text" name=writer placeholder="input writer">
		</tr>
		<tr>
				<td><input type="text" name=message placeholder="input message">
		</tr>
		<tr>
			<td><input type="file" name=file placeholder="input message">
			<td align=center><button>Submit</button>
			
			<a href="/"><button type="button">back home</button></a> 
		</tr>
		</table>
		</form>
</body>
</html>