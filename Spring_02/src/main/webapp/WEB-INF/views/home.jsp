<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
		<table border=1 align =center>
			<tr>
			<th colspan =2> index
			</tr>
			<tr>
			<td><a href="/message/toInput">toInnput</a>
			
			<td><a href="/message/getList">toOuput</a>
			</tr>
		</table>	
		<hr>
		<a href="/message/selectBySeq?seq=4">4번글검색 SELECT BY SEQ </a>
		
		<hr>
		<filedset>
		<legend>search</legend>
		<form action="/message/selectByCon">
			<select name=condition>
				<option value="writer">writerrr</option>
				<option value="message">message</option>
				<!-- value에는 컬럼과 똑같은 이름 -->
				<input type=text name = keyword placeholder="검색할 단어입력">
				<button>검색</button>
			</select>
		</form>
		</filedset>
		
		<hr>
		
		<fieldset>
		<legend>search2</legend>
		<form action="/message/selectByMulticon">
			<input type=text name=writer placeholder="검색할작성자"><br>
			<input type=text name= message placeholder="메시지"><br>
			<button>검색</button>
		</form>
		</fieldset>
		<hr>
		<form action="/file/upload" method="post" enctype ="multipart/form-data">
		<table  border="1px" >
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
		
		
		<form action="/file/multiUpload" method="post" enctype ="multipart/form-data">
		<table  border="1px" >
		<tr>
			<td><input type=text name=writer>
		</tr>
		<tr>
			<td><input type=text name=message>
			
		</tr>
		<tr>
		<td><input type=file name=files multiple>
		</tr>
			<tr>
				<td> <button>제출</button>
			</tr>
		</table>
		
		</form>
		
		
		<form action="/file/multiUpload" method="post" enctype ="multipart/form-data">
		<table  border="1px" >
		<tr>
			<td><input type=text name=writer>
		</tr>
		<tr>
			<td><input type=text name=message>
			
		</tr>
		<tr>
		<td><input type=file name=files>
		<td><input type=file name=files>
		<td><input type=file name=files>
		</tr>
			<tr>
				<td> <button>제출2</button>
			</tr>
		</table>
		
		</form>
		
		
	<img src="/resources/spring.png" width="100">
</body>
</html>