<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<style>
/* div{
                            border: 1px solid black;
                        } */
.container {
	width: 500px;
	height: 100%;
	margin: auto;
}

.box1 {
	height: 400px;
	border: 1px solid black;
	overflow-y: auto;
	background-color: wheat;
}

.box2 {
	height: 150px;
	border: 1px dotted black;
}

.box2>#input {
	width: 100%;
	height: 80%;
	background-color: lavender;
	overflow-y: auto;
	text-align: top;
}

.box2>#emo {
	width: 30px;
	height: 30px;
	background-color: blueviolet;
}

#emo>button {
	width: 30px;
	height: 30px;
}

button>img {
	width: 100%;
	height: 100%;
}

.textbubble {
	position: relative;
	display: inline-block;
	/* max-width: calc(100% - 70px); */
	padding: 10px;
	margin-top: 7px;
	font-size: 15px;
	border-radius: 10px;
	left: 10px;
	background-color: white;
}

#emobox {
	width: 100%;
	height: 100%;
	background-color: beige;
	display: none;
}

.emoti {
	width: 80px;
	height: 80px;
}

.msg-box {
	max-width: 250px;
	word-wrap: break-word;
	border: 1px dotted black;
	border-radius: 5px;
	margin: 5px;
	padding: 5px;
	display: inline-block;
}
</style>
<script>
	function updateScroll() {
		var element = document.getElementsByClassName("box1")[0];
		element.scrollTop = element.scrollHeight;
	}

	$(function() {
		let ws = new WebSocket("ws://192.168.150.35/chat");//webSocket 인스턴스생성
		//연결	
		//@OnOpen

		ws.onmessage = function(e) {
			console.log(e.data);
			let data = JSON.parse(e.data);
			
			
			for(let i = 0; i< data.length; i++){
		
				let outer = $("<div>");
				let line = $("<div>");
				line.addClass("msg-box")
				
		
				line.append(data[i].msg);
				
				outer.append(line);
				$(".box1").append(outer);
				updateScroll();
			}
			
		}

		$("#input").on("keydown", function(e) {
			if (e.keyCode == 13) {
				let text = $("#input").text();
				$("#input").text("");
				ws.send(text); //@OnMessage로
				return false;
			}
		});
	})
</script>
</head>

<body>

	<div class="container">
		<div class="box1" id="chat"></div>
		<div class="box2">
			<div id="input" contenteditable="true"></div>
			<button>전송</button>
			<div id="emo">
				<button id="emoadd">
					<img src="emoticon1.png" id="econ01">
				</button>
			</div>


		</div>
		<div id="emobox">

			<img src="emoticon1.png" id="econ01" class="emoti"> <img
				src="emoticon2.gif" id="econ02" class="emoti"> <img
				src="emoticon3.gif" id="econ02" class="emoti"> <img
				src="emoticon4.gif" id="econ02" class="emoti"> <img
				src="emoticon5.gif" id="econ02" class="emoti"> <img
				src="emoticon6.gif" id="econ02" class="emoti">

		</div>

	</div>

</body>


</html>