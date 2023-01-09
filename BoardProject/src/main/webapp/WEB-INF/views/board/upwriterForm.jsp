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
                        width: 100%;
                        height: 80%;
                    }

             		   textarea {
             		   border:none;
                       width: 100%;
                       height: 500px;
              		  }  
              		  
              		  textarea:focus{
						outline:none;
					}
                    .right {
                        text-align: right;
                    }
                    
                    input{
                    width:100%;
                    height:100%;
                    }
                </style>

        </head>

            <body>
                <form action="/board/update"method="post" id="form">
                <div class="container">
                    <div class="row header">
              <div class="d-none" id="seq"><input name="seq" value="${list.seq}">${list.seq}</div> 
                        <div class="col-12 col-md-12"><input type="text" name="title" value="${list.title}" ></div>
                        <div class="col-12 col-md-4">${list.writer }</div>
                        <div class="col-8 col-md-6">${list.write_date}</div>
                        <div class="col-4 col-md-2">${list.view_count }</div>
                    </div>
                    <div class="row contents">
                        <div class="col-12">
                           <!--  <input type="textarea" name="upcontents" value="${list.contents }"> -->
                        	<textarea  name="contents">${list.contents }</textarea>	
                        </div>
                    </div>

                    <div class="row footer">
                        <div class="col-12 right">
           								
                                    <button type="submit" class="btn btn-outline-secondary" id="toUpdate">수정 완료</button>
                                   
                            <button type="button" class="btn btn-outline-secondary" id="toBack" onclick="history.back()">뒤로가기</button>
                        </div>
                    </div>

                </div>
                </form>

                <script>
                
                //	let seq =document.getElementById("seq").innerHTML;
                	
                	
                	
                //    $("#toBack").on("click", function () { //인덱스 화면으로 가기
                  //      location.href = "/detail.board?seq="+seq;
                 //   })


               	//	 $("#toUpdate").on("click", function () {
				//		$("#form").attr("action","/update.board");
				//		$("#form").submit();
                //     //location.href="/update.board?seq="+seq;

               //  })
               
               /*
               	contenteditable 로 하면 hidden input에 담아서 서브밋
               	$("input_contents").val()($("#contents").html)
               	$("#input_title").val($("#title").html())

               	
               	$("#modify").on("click", function() {
         $("#delete,#modify").hide();
         
         $("#title,#contents").attr("contenteditable","true");
         
         let modifyOK = $("<button>");
         modifyOK.html("수정완료");
         modifyOK.on("click",function(){
            $("#detailFrm").attr("action","/update.board");
            
            $("#input_contents").val($("#contents").html());
            $("#input_title").val($("#title").html());
            
            $("#detailFrm").submit();
         })
         
         let modifyCancel = $("<button>");
         modifyCancel.attr("type","button");
         modifyCancel.html("취소");
         modifyCancel.on("click",function(){location.reload();});
         
         $("#back").before(modifyOK);
         $("#back").before(modifyCancel);
      })
               	
               	
               	썸머러스에디터 반응형이랑 호환이좋음
               */

                </script>


            </body>

</html>