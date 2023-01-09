<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${list.seq}. ${list.title}</title>
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
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

                    textarea:nth-child(1){
                    	border:none;
                    	resize:none;
                        width: 100%;
                        height:100%;
                        background-color : transparent;
                        
                    }
	
					textarea:focus{
						outline:none;
					}
                    .right{
                        text-align: right;
                    }
                    
                    .contents{
                    	height:300px;
                    }
                    #replytext{
                    width:80%;
                    height:30px;
                    }
                    #add{
                    text-align:right
                    }
    </style>

    </head>
<body>
            <div class="container">
                <div class="row header">
                	<div class="d-none" id="seq">${list.seq}</div>
                	
                    <div class="col-12 col-md-12">${list.title}</div>
               	
                    <div class="col-4 col-md-2">
                    	<img src="/profile/${mList }">
                    	</div>
           
                    <div class="col-12 col-md-4">${list.writer }</div>
                    <div class="col-8 col-md-6">${list.write_date}</div>
                    <div class="col-4 col-md-2">${list.view_count }</div>
                
                   
                </div>
                <div class="row contents">
                    <div class="col-12">
                    	<textarea  disabled>${list.contents }</textarea>	
                    	
	
                    </div>
                </div>
                
                <div class="row footer">
                <div class="card col-12">
                       <div class="card-header">
                         Download Files List
                       </div>
                       <ul class="list-group list-group-flush">
                       
                       <c:forEach var="f" items="${filelist}">
                           <li class="list-group-item"><i class="bi bi-link-45deg"></i> ${f.seq }. <a href="/files/download?sysname=${f.sysname }&oriname=${f.oriname}"> ${f.oriname }</a></li>  <!-- 다운로드 흉내 중 -->
                       </c:forEach>
                        
                       </ul>
                     </div>
                    <div class="col-12 right">
                    
                    	
                    <c:if test="${loginID==list.writer}">
                        <button type="button" class="btn btn-outline-danger" id="toDelete">삭제하기</button>
                        <button type="button" class="btn btn-outline-secondary" id="toWriterForm">수정하기</button>
                        </c:if>
                        
                        <button type="button" class="btn btn-outline-secondary" id="toBoard">목록으로</button> 
                    </div>
                </div>
                
                
              
                 <div class="row reply">
                 		   <c:choose>
                            <c:when test="${not empty comments }">
                                <!-- 리스트가 비어있지않다면 -->
                                <c:forEach var="r" items="${comments }">
                        			<form action="" method="post" id="frm">
                                    <div class="row replycontents">
                                  
                                    	<input type="hidden" value="${r.seq}" name="seq" class="rseq">
                                    	
                                        <div class="col-12 col-md-3">${r.writer }</div>
                                        <div class="col-12 col-md-6"><textarea class="inputreply" name="contents"  disabled>${r.contents}</textarea> </div>
                                        <div class="col-12 col-md-1">${r.formDate }</div>
                                       
                                       <div class="col-md-2 commentedit">
                                       <c:if test="${loginID==r.writer}">
                                       <input type="hidden" value="${r.seq}" name="seq" class="rseq">
                                       <input type="hidden" value="${r.parent_seq}" name="parent_seq" class="rseq">
                      			 	 <button type="button"  class="btn btn-outline-danger deleteComment" seq="${r.seq}">삭제하기</button>
                      				  <button type="button" class="btn btn-outline-secondary updateComment" seq="${r.seq}">수정하기</button>
                      	 			 </c:if></div>
                                  		
                                    </div>
                            	 </form>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <div class="other">작성된 댓글이 없습니다</div>
                            </c:otherwise>
                        </c:choose>
                     
                 
                 	
                 		<form action="" method="post" id="detailFrm" >
                        <div class="col-12">
                            <div class="col-12"  id="replywriter">
                           	<input type="text" value="${loginID}" disabled>
                           	<input type="hidden" name="writer" value="${loginID}">
                        	
                            </div>
                        </div>
                        <div class="col-12"> 
                        	<textarea name="contents" id="replytext" ></textarea>
                        

                            <button class="btn btn-outline-secondary" id="addreply">Reply</button>
                        </div>
                        </form>
                     
                     </div>
                
                

            </div>
          
					 
            <script>
         
            $(document).on("click", ".updateComment", function () {
        		
            	$(this).closest(".replycontents").find(".inputreply").attr("disabled", false);
        		$(this).closest(".commentedit").find(".updateComment").hide();
        		$(this).closest(".commentedit").find(".deleteComment").hide();
        		
        		
        		
        		 let modifyOK = $("<button>");
        		 modifyOK.html("수정완료");
        		
        		 /*
        		let target = $(this).closest(".commentedit").find(".updateComment").val();
        		console.log(target);
        		*/
        		
        		 let target = $(this).attr("seq"); 
                 console.log("seq : "+seq);
                 console.log("list seq : "+${list.seq});
         
        		modifyOK.on("click",function(){
        			 
        
        			/* $(this).closest("#frm").attr("action","/comments/update?seq="+target+"&parent_seq="+${list.seq}); */
        			$(this).closest("#frm").attr("action","/comments/update");
        			 $(this).closest("#frm").submit();
                  })
          
                 let modifyCancel = $("<button>");
                 modifyCancel.attr("type","button");
                 modifyCancel.html("취소");
                 modifyCancel.on("click",function(){location.reload();});
                 
                 $(this).closest(".commentedit").find(".updateComment").before(modifyOK);
                 $(this).closest(".commentedit").find(".updateComment").before(modifyCancel);
              })
            
            
            
       
           			$(".deleteComment").on("click", function(){
           				 let target=$(this).attr("seq");
           				//location.href="/comments/delete?seq="+target+"&parent_seq="+${list.seq};
           			/*	location.href="/comments/delete" */
           			$(this).closest("#frm").attr("action","/comments/delete");
           			 $(this).closest("#frm").submit();
            			})
           		
            
            	$("#addreply").on("click", function(){
            		let replytextt = $("#replytext").val();
            
            		console.log(replytextt);
            		
            		if(replytextt==""){  //textarea 태그에 띄어쓰기 주의
            			console.log("입력된 내용이 빈칸입니다")
            			alert("입력된 내용이 없습니다");	
            			return false;
            			}
            				else{
            			$("#detailFrm").attr("action", "/comments/insert?parent_seq="+${list.seq})
                		$("#detailFrm").submit();
            		}
            		
            
            	})
          
     	  /*
          	$("#addreply").on("click", function(){
            		
            		let replytextt = $("#replytext").val();
            		console.log(replytextt);
            		
            		if(replytextt==""){
            			console.log("입력된 내용이없습니다")
            			alert("입력된 내용이 없습니다");	
            			return false;
            		}else{
            			alert("등록이 되었습니다")
            			
            			$("#reply").attr("action", "/insert.comments?parent_seq="+${list.seq})
            			$("#reply").submit();
            		}
            		
            	})  
            	
            	*/
           
            /*	$(".replydelete").on("click", function(){
            		
            		let rseq= $("#rseq").val();

                	let result = confirm("삭제하실건가요?");
                	
                	if(result){
                	 	location.href="/delete.comments?rseq="+rseq+"?parent_seq="+${list.seq};
                	}else{
                		return false;
                	}
                	
            	})*/
      
            
         			   /*
						c:if  else , else if 가없어서 하나의 경우의수만
						*/
            		/*	
            		$("#toBoard").on("click", function () { //인덱스 화면으로 가기
                     	history.back(); 글수정하고 뒤로가기 누르면 다시 글수정화면으로감
                    })   */
                    
                    $("#toBoard").on("click", function () { //인덱스 화면으로 가기
                     	location.href="/board/list"; 
                    	//history.back();
                    })
        	       //수정을 완료하고 목록으로 했을때 다시 수정화면으로 안가게?
          		
                    
                    $("#toDelete").on("click", function(){
                    	
                    	let seq= document.getElementById("seq").innerHTML;
                    	
                    	
                    
                    	let result = confirm("삭제하실건가요?");
                    	
                    	if(result){
                    	 	location.href="/board/deleteboard?seq="+seq;
                   
                    	}else{
                    		return false;
                    	}
                    	
                
                    })
                    
                    $("#toWriterForm").on("click", function(){
                    	let seq= document.getElementById("seq").innerHTML;
                    	location.href="/board/toupwriter?seq="+seq;
                    })
                    
                  
                 /*
                    $("#delete").on("click", fuction(){
                    	$("#detailFrm").attr("action", "/delete.board");   $("#detailFrm").attr("action", "/delete.board?seq=${dto.seq}"); 이것도됨
                    	$("#detailFrm").submit();
                    })
                    
                    $("#update").on("click", fuction(){
                    	$("#detailFrm").attr("action", "/update.board");
                    	$("#detailFrm").submit();
                    })   수정,삭제버튼에 타입버튼을 주고 폼태그로 묶꼬 action을 비우고 id값(detailFrm)을 준다음에 어떤 버튼을 누르냐에 따라 action 값이 바뀌게끔
                    
                    	name, select , 
                    	<input type=hidden value=$(dto.seq} name=seq )
                    		display : none 대신 hidden
                    		<input 태그 textarea , select  서브밋으로 넘어갈수 있는 데이터 
                    */
                    
                    
                    
                    /*
                    	
                    
                    */
            </script>


</body>
</body>
</html>