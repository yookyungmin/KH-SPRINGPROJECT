<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
        crossorigin="anonymous"></script>
	   <script src="https://code.jquery.com/jquery-3.6.1.js"></script>
	   <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<style>
        /*768px까지는 400px로 보인다*/
        		@media ( min-width : 768px) { 
  			 .container {
    			  width: 400px;
 				 	 }
					}
            *{
                box-sizing: border-box;
            }
            div{
                /* border: 1px solid black; */
            }
            .container{
           
                height: 100%;
            }
            header{
                
            }

            input{
                width: 100%;
                height: 100%;
            }
            button{
                width: 100%;
                height: 100%;
            }

            .font{
                size: 20px;
             
            }
            
            
            td>button{
            	width:100%;
            	height:100%;
            }
            th{
            padding:10px;
            }

        </style>
</head>
<body>
<c:choose>
		<c:when test="${loginID!=null}"> <!-- loginID null 값아니라면, 로그인을 한 사용자-->
			
			<table border=1 align=center>
				<tr>
					<th colspan=4>${loginID }님 안녕하세요
				</tr>
				<tr>
					<td><button id="toBoard">자유게시판</button>
					<td><button id="mypage">마이페이지</button>
					<td><button id ="logout">로그아웃</button>
					<td><button id="memberout">회원탈퇴</button>
				</tr>
			
			</table>
	
			<script>
				$("#toBoard").on("click", function(){
					location.href="/board/list";
				})
			
				$("#logout").on("click", function(){ //로그아웃
					location.href="/member/logout"
				})
				
				$("#memberout").on("click", function(){ //회원탈퇴
					Swal.fire({
						   title: '정말로 그렇게 하시겠습니까?',
						   text: '다시 되돌릴 수 없습니다. 신중하세요.',
						   icon: 'warning',
						   
						   showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
						   confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
						   cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
						   confirmButtonText: '승인', // confirm 버튼 텍스트 지정
						   cancelButtonText: '취소', // cancel 버튼 텍스트 지정
						   
						   reverseButtons: true, // 버튼 순서 거꾸로
						   
						}).then(result => {
						   // 만약 Promise리턴을 받으면,
						   if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
								alert("회원정보가 모두 삭제되었습니다")
						    //  Swal.fire('승인이 완료되었습니다.', '화끈하시네요~!', 'success');
								location.href="/member/memberOut"
						   }else{
							   return false;
						   }
						});
						}
				)
				
				$("#mypage").on("click", function(){
						location.href="/member/mypage";
					
				})
				
			</script>
		</c:when>
		<c:otherwise>
	<form action="/member/login" method="post">
    <div class="container">
        <div class="row header">
                <div class =" col-12 col-sm-12 col-md 12 text-center">
                    Login Box
                </div>
        </div>

        <div class="row contents">
            <div class="col-12 col-sm-12 col-md-3 font ">
               로그인
            </div>
                <div class="col-12 col-sm-12 col-md-9">
                    <input type="text" placeholder ="input your id" name="id">
                </div>
            
            <div class="col-12 col-sm-12 col-md-3 ">
                패스워드
            </div>
            <div class="col-12 col-sm-12 col-md-9 ">
                <input type="password" placeholder="input you pw" name="pw">
            </div>
        </div>

        <div class="row footer">
                <div class="col-12 col-sm-6">
                <button type="submit" class="btn btn-primary">login</button>
                </div>
                <div class="col-12 col-sm-6">
                <a href="/member/toSignup"><button type="button" class="btn btn-primary">sign up</button></a>
                </div>
                <div class="col-12 col-sm-12 text-center">
               <p><input type="checkbox" id="c"> <label for="c">ID기억하기</label></p>
                </div>
        </div>

    </div>
    </form>
	</c:otherwise>
	</c:choose>
	

</body>
</html>