<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<!DOCTYPE html>
<html>
<head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>
            <!-- CSS only -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
                crossorigin="anonymous">
            <!-- JavaScript Bundle with Popper -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
                crossorigin="anonymous"></script>


            <script src="https://code.jquery.com/jquery-3.6.1.js"></script>

            <style>
                * {
                    box-sizing: border-box;
                }

                div {
                    border: 1px solid black;
                }

                .container {
                    width: 50%;
                    height: 100%;
                    padding: 0;
                }

                .wcontents {
                    height: 500px;
                }
                
                .right{
                	text-align:right;
                }
                #titlee{
                	width:100%;
                	height:100%;
                }
                
                textarea{
                	width:100%;
                	height:100%
                }
            </style>
        </head>

        <body>
            <form action="/board/writer" method="post" id="write" enctype ="multipart/form-data">
            <div class="container">
                <div class="header">
                    <div style="text-align:center">
                        <Strong>??????????????? ??? ????????????</Strong>
                    </div>
                    <div>
                        <input type="text" placeholder="??? ????????? ???????????????" name="title" id="titlee">
                    </div>
                </div>
                <div class="contents">
                    <div class="wcontents">
                        <textarea name="contents" id ="textt"></textarea>
                    </div>

                </div>
                <div class="footer">
                    <div class="right">
                    	
                    
                    	<button type=button id="fileAdd" class="btn btn-dark">????????????</button>
                    	
                    
                        <button type="button" id="toBoard" class="btn btn-outline-primary">????????????</button>
                          <button type="submit" class="btn btn-primary">????????????</button>
                    </div>
                
                </div>
            </div>
            </form>
            <script>
            //????????? name??? ???????????? ?????? ??? ??????  multiple???????????????
            
            	let count=0;
				$("#fileAdd").on("click", function(){
				
					if($("input[type=file]").length>4){
						alert("????????? ?????? 5???????????? ????????? ???????????????");
						return;
					}
			
					let fileDiv=$("<div>");
					
					let inputFile = $("<input>");
					inputFile.attr("type","file");
					inputFile.attr("name","files");
					
					
					let delBtn=$("<a>");
					delBtn.html("x");
					delBtn.css("text-decoration-line", "none")
					delBtn.addClass("line-del");
					
					delBtn.on("click", function(){
						 $(this).parent().remove();
					})
					
					fileDiv.append(inputFile);
					fileDiv.append(delBtn);
					fileDiv.css("border", "none")
					
					$("#fileAdd").parent().after(fileDiv);
				})
            
            
                $("#toBoard").on("click", function () { //????????? ???????????? ??????
                    location.href = "/board/list";
            
                })
                 
                $("#write").on("submit", function(){   //???????????? ?????? ?????? ????????? ???????????? ????????????
                	let title = $("#titlee").val();
                	let contents = $("#textt").val();
                	
                	
      	
                	console.log(title);
                	console.log(contents);
                	
                	if(title==""||contents==""){
                		alert("???????????? ????????? ???????????? ???????????????")
                		return false;
                	}else{
                		alert("????????? ???????????????")
                	}
                })
            </script>
            
        </body>
</html>