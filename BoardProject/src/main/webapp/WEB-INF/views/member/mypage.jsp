<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>


<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


<script>
    		$(function(){
    			$("#modify").on("click", function(){
        			
        			$("input").removeAttr("readonly");//인풋 태그의 attr에서readonly 속성제거
        			$(".control").css("display", "block");
        			$("#toHome, #modify").css("display", "none"); //사라지게
        			$("#searchpost").css("display","block"); //우편주소 찾기 생기게
        			let btnModify = $("<button>");  //수정완료 버튼태그 생성 
        			btnModify.text("수정완료"); 
        			btnModify.css("margin-right","5px")
        			
        			let btnCancel = $("<button>");  //취소 버튼 태그
        			btnCancel.attr("type", "button")
        			btnCancel.text("취소");
        			
        			btnCancel.on("click", function(){
        				location.reload();  //f5와 동일, 새로고침
        			});
         			
        			$("#btns").append(btnModify);
        			$("#btns").append(btnCancel);
    		})
    	
    		})
    	
    	</script>

<style>
* {
	box-sizing: border-box;
}

div:not(.resultval) {
	border: 1px solid black;
}

input {
	width: 100%;
	height: 45px;
}

button {
	width: 100px;
	height: 45px;
}

.container {
	width: 800px;
	height: 100%;
	margin: auto;
}

.header {
	width: 100%;
	height: 70px;
	text-align: center;
	text-align: center;
	font-weight: 800;
}

.contents {
	width: 100%;
	height: 450px;
}

.row {
	width: 100%;
	height: 50px;
}

.row>div {
	float: left;
	height: 100%;
}

.title {
	width: 20%;
	line-height: 45px;
	text-align: right;
	padding-right: 20px;
}

.box1 {
	width: 40%;
}

.box2 {
	width: 40%;
	line-height: 45px;
	padding-left: 20px
}

.box3 {
	width: 80%;
	line-height: 45px;
}

.footer {
	width: 100%;
	height: 110px;
	text-align: center;
	line-height: 65px;
}

.resultval {
	float: right;
	width: 300px;
	height: 40px;
	font-size: 6px;
}

form>input {
	size: 10px;
}

form {
	text-align: center;
}

.resultval {
	color: red;
}

.profile {
	margin: auto;
	width: 300px;
	height: 100px;
	overflow: hidden;
}

.profile div {
	float: left;
	height: 100%;
}

.thumbnail {
	width: 30%;
	padding: 5px;
}

.thumbnail img {
	width: 100%;
	height: 100%;
}

.control {
	width: 70%;
	line-height: 100px;
}

.control input {
	width: 100%;
	height: 100%;
}
</style>

</head>

<body>
	<form action="/member/update" method="post" id="frm" enctype ="multipart/form-data">
		<div class="container">
			<div class="header">
				<h1>마이페이지</h1>
			</div>

			<div class="profile">
				<div class="thumbnail">
					<c:if test="${list.profile_img ==null}">
						<img src="/profile/no_profile.png" id="profile" >
					</c:if>
					<c:if test="${list.profile_img !=null}">
						<img src="/profile/${list.profile_img }" id="profile">
					</c:if>

				</div>

				<div class="control" style=" display:none";>
					<input type=file name="file" id="profile_img"
						accept=".png, .jpg, .jpg,.gif">
				</div>
			</div>


			<div class="contents">
				<div class="row">
					<div class="title">ID :</div>
					<div class="box1">
						<input type="text" id="inputId" disabled value="${list.id}">
						<input type="hidden" name="id" value="${list.id }">
					</div>

					<div class="box2"></div>
				</div>
				<div class="row">
					<div class="title">패스워드 :</div>
					<div class="box1">
						<input type="text" name="pw" id="inputPw"
							placeholder="input your pw" readonly>
					</div>
					<div class="box2">
						<div class="resultval" id="pwcheck"></div>
					</div>
				</div>
				<div class="row">
					<div class="title">패스워드확인 :</div>
					<div class="box1">
						<input type="text" id="inputPw2" placeholder="사용하실 pw를 한번더 입력"
							readonly>
					</div>
					<div class="box2"></div>
				</div>
				<div class="row">
					<div class="title">이름 :</div>

					<div class="box1">
						<input type="text" name="name" id="inputName"
							placeholder="input your name" value="${list.name}" readonly>
					</div>

					<div class="box2"></div>

				</div>
				<div class="row">
					<div class="title">전화번호 :</div>
					<div class="box1">
						<input type="text" name="phone" id="inputPhone"
							placeholder="사용하실 전화번호 입력하세요" value="${list.phone}" readonly>
					</div>
					<div class="box2">
						<div class="resultval" id="phonecheck"></div>
					</div>

				</div>
				<div class="row">
					<div class="title">이메일 :</div>
					<div class="box1">
						<input type="text" name="email" id="inputem"
							placeholder="사용하실 이메일 입력하세요" value="${list.email}" readonly>
					</div>
					<div class="box2">
						<div class="resultval" id="emcheck"></div>
					</div>
				</div>
				<div class="row">
					<div class="title">우편번호 :</div>
					<div class="box1">
						<input type="text" name="zipcode" id="postcode"
							value="${list.zipcode}" readonly>
					</div>
					<div class="box2" style="display: none" id="searchpost">
						<button type="button" id="postsearch">찾기</button>
					</div>
				</div>
				<div class="row">
					<div class="title">주소1 :</div>
					<div class="box3">
						<input type="text" name="address1" id="address1"
							value="${list.address1}" readonly>
					</div>
				</div>
				<div class="row">
					<div class="title">상세주소 :</div>
					<div class="box3">
						<input type="text" name="address2" id="address2"
							value="${list.address2}" readonly>
					</div>
				</div>

				<div class="row">
					<div class="title">회원가입 날짜</div>
					<div class="box3">
						<fmt:formatDate pattern="yyyy년 MM월 dd일 가입"
							value="${list.signup_date}" />
					</div>
				</div>
			</div>

			<div class="footer" id="btns">
				<button type="button" id="modify">정보수정</button>
				<a href="/"><button type="button" id="toHome">홈으로</button></a>

				<!-- 	<input type="button" value="정보 수정" id="modify"> 
					<a href="/index.jsp"><input type="button" value="홈으로" id="toHome"></a>
					
					 -->
			</div>
		</div>
	</form>

	<script>
	
	function fileToBase64(file){
        const reader = new FileReader();
         reader.readAsDataURL(file)
         reader.onload = () => {
            console.dir(reader.result)   // base64
            $("#profile").attr("src", reader.result);
         }
     }
	$("#profile_img").on("change", function(){
		if($("#profile_img").val()==""){
			$("#profile").attr("src", "/profile/no_profile.png");
			return;
		}
		console.log($("#profile_img").val());
		let ext=$("#profile_img").val().split(".").pop().toLowerCase();
		
		let accept=["png", "jpg", "jpeg", "gif"];
	
		let result=	$.inArray(ext, accept); //첫번쨰 인자값이 두번쨰 인자 배열 안에 존재한다면 배열 인덱스 반환(0이상값 반환),  존재하지않으면 -1 반환
		console.log(result);
		
	
		
		if(result==-1){
			alert("이미지만 사용 가능합니다")
			$("#profile_img").val("");//비워주기
			$("#profile").attr("src", "/images/no_profile.png")//다른 사진들어올떄 #profile 도 비워주기
			

		} else{
			fileToBase64(document.getElementById("profile_img").files[0]);
		}
	});

         
            let idcheck = document.getElementById("idcheck");
            let inputPw = document.getElementById("inputPw");
            let inputPw2 = document.getElementById("inputPw2");

            let inputName = document.getElementById("inputName")
            let inputPhone = document.getElementById("inputPhone")
            let inputem = document.getElementById("inputem")

            let retype = document.getElementById("retype");

            let address1= document.getElementById("address1")
            let address2 = document.getElementById("address2")
         
            // let regxN = /[가-힇]{2,5}$/g
            // let regxP = /^01\d-?\d{3,4}-?\d{4}$/
            // let regem = /[^@.]{5,15}@[^@.]{4,10}\.((com)|(net))$/

            
        	let pwcheckk = false;
            
            inputPw2.onkeyup = function () {
    			
                let pw = inputPw.value;
                let pw2 = inputPw2.value;

                let pwcheck = document.getElementById("pwcheck");

                let regx = /[A-Za-z0-9!@#$%]{8,20}/;

                // let resultregx = regx.test(pw);
                // let resultregx2 = regx.test(pw2);
               	if(!regx.test(pw)&&pw!==""){
                 pwcheck.innerHTML = "대문자, 소문자, 숫자, 특수문자(!, @, #, $, %) 8~20글자로 입력"
                   }else if(pw== pw2 && pw!== "" && pw2!== ""){
                	   pwcheck.innerHTML = "패스워드가 일치합니다.";
                	  pwcheckk = true;
                   }else{
                	   pwcheck.innerHTML="패스워드가 다릅니다";
                	   pwcheckk=false;
                   }
     
                
            }

            inputPhone.onkeyup = function(){
                
                let inputp = inputPhone.value;
                let phonecheck = document.getElementById("phonecheck");

                  let regxPh = /^01\d-?\d{3,4}-?\d{4}$/

                //   let resultreg = regxPh.test(inputp);

                  if(regxPh.test(inputp)){
                        phonecheck.innerHTML = "적합한 번호 형식입니다"
                  } else {
                    phonecheck.innerHTML = "적합하지 않은 번호 형식입니다."
                  }
            }

            inputem.onkeyup = function(){
                let inputema = inputem.value;
                let emcheck = document.getElementById("emcheck");

                let regxem = /[^@.]{5,15}@[^@.]{4,10}\.((com)|(net))$/

                let resultregx = regxem.test(inputema)

                if(resultregx){
                    emcheck.innerHTML = "적합한 이메일입니다"
                }else {
                    emcheck.innerHTML = "적합하지 않은 이메일 형식입니다"
                }
            }
            
            //중복확인이 제대로 되었는가
            //패스워드가 일치하는가?
            //각종 필드 유효성 검사

            frm.onsubmit = function () {
   
    
               let name= inputName.value
               console.log(name);
               let phone = inputPhone.value
               let email= inputem.value;

                let pw = inputPw.value;
                let pw2 = inputPw2.value;
                 let pwcheck = document.getElementById("pwcheck");

                let regxpw = /[A-Za-z0-9!@#$%]{8,20}/;
                 let resultregx = regxpw.test(pw);
                let resultregx2 = regxpw.test(pw2);

                let regxN = /[가-힇]{2,5}$/
                let regxP = /^01\d-?\d{3,4}-?\d{4}$/
                let regem = /[^@.]{5,15}@[^@.]{4,10}\.((com)|(net))$/

              
                	if(!(pwcheckk)){
                		alert("두패스워드가 일치하지 않습니다")
                		return false;
                	}
                	
                	
               	 if(phone!=""){
               		 if(!(regxP.test(phone))){
               				alert("전화 번호 형식을 확인해주세요")
               				return false;
               		 }
               				
               	 }
                
                
                if((regxN.test(name))&&regxP.test(phone)&&regem.test(email)&&resultregx&&resultregx2){
                        alert("수정완료");
                        
                }
                else if((pw2&&pw)==""){
                    alert("pw를 입력해주세요")
                    return false;
                }
                else if(name==""){
                        alert("이름을 입력해주세요")
                        return false;
                }else if(!(regxN.test(name))){
                	alert("이름 형식이 올바르지 않습니다 ")
                	return false;
                }
                
                else{
                        alert("회원가입 실패");
                        return false;
                }
            }


       


             document.getElementById("postsearch").onclick = function () {
                    new daum.Postcode({
                        oncomplete: function (data) {
                            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                            // 우편번호와 주소 정보를 해당 필드에 넣는다.
                            document.getElementById('postcode').value = data.zonecode;
                            document.getElementById("address1").value = data.jibunAddress; //roadAddress 도로명 주소
                        }
                    }).open();

                }
                




            </script>
</body>
</html>