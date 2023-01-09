<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
    crossorigin="anonymous"></script>
    
      <script src="https://code.jquery.com/jquery-3.6.1.js"></script>


    <style>
    
        /* @media ( min-width : 768px) { 
  			 .container {
    			  width: 400px;
 				 	 }
					} */


        *{
            box-sizing: border-box;
        }
		   div{
            border: 1px solid black;
        }
       .container{
            width: 100%;
            height: 80%; 
        }
    	.contents{
    	
		overflow-y
    	}

  
        .right{
        
        	text-align:right;
        }
        
        .other{
        
        	height:400px
        }
        a{
  
 		 text-decoration: none; /* 링크의 밑줄 제거 */
 	 	color: inherit; /* 링크의 색상 제거 */
		}
		a:hover{
		 color : black;
		 font-weight:700;
		}

        
    </style>


    </head>
<body>
           
            <div class="container">
                <div class="row header">
                    <div class="col-12 col-md-12 col-sm-12 text-center" ><strong>자유게시판</strong></div>
                    <div class="col-md-1 d-none d-md-block">번호</div>
                    <div class="col-5 col-md-6">제목</div>
                    <div class="col-3 col-md-2">작성자</div>
                    <div class="col-3 col-md-2">날짜</div>
                    <div class="col-1 col-md-1">조회</div>
                </div>
                
              <c:choose>
            	    <c:when test="${not empty list}">  <!-- 리스트가 비어있지않다면 -->
            	    <c:forEach var="i" items="${list}">
                	
		                <div class="row contents">
		                   	<div class="col-md-1 d-none d-md-block">${i.seq}</div>
		                    <div class="col-5 col-md-6"><a href="/board/detail?seq=${i.seq}&writer=${i.writer}">${i.title}</a></div>
		                    <div class="col-3 col-md-2">${i.writer}</div>
		                    <div class="col-3 col-md-2">${i.formDate}</div>
		                	<!-- <div class="col-3 col-md-2"><fmt:formatDate pattern="yyyy년 MM월 dd일" value="${i.write_date}"/></div> -->
		                    <div class="col-1 col-md-1">${i.view_count}</div> 
		                </div>    
          			</c:forEach>
          			</c:when>
          			<c:otherwise>
          					 <div class="other">출력할내용이 없습니다</div>
          			</c:otherwise>
          	</c:choose>
          		
          	
                <div class="row footer">
                        <div class="col-8 col-md-12 col-sm-8 navi">
                            <nav aria-label="Page navigation example">
  							<ul class="pagination justify-content-center">
  							${navi}
						  </ul>
						</nav>
								
                        </div>
                        <div class="col-4 col-md-12 col-sm-4 right">
                        
                        <button type="button" class="btn btn-outline-secondary" id="towriter">작성하기</button>
                          <button type="button" class="btn btn-outline-secondary" id="toindex">뒤로가기</button>
                          
                           <!--  <button type="button" id="towriter">작성하기</button>
                             <button type="button" id="toindex">뒤로가기</button>-->
                        </div>
                        
                </div>
            </div>
                <script>
                    $("#towriter").on("click", function(){
                        location.href="/board/towriter";
                    })
                    
                    $("#toindex").on("click", function(){
                    	location.href="/"
                    	
                    })
                </script>
</body>
</html>