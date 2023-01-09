<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>


<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>

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
	height: 70px;
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



	<form action="/member/signup" method="post" id="frm" enctype ="multipart/form-data">

		<div class="container">

			<div class="header">
				<h1>회원가입 정보 입력</h1>
			</div>


			<div class="profile">
				<div class="thumbnail">
					<img src="/images/no_profile.png" id="profile" >
				</div>
				<div class="control">
					<input type=file name="file" id="profile_img" accept=".png, .jpg, .jpg,.gif">
				</div>
			</div>

			<div class="contents">
				<div class="row">
					<div class="title">ID :</div>
					<div class="box1">
						<input type="text" name="id" id="inputId"
							placeholder="사용하실 id를 입력하세요">
					</div>
					<div class="box2">
						<button type="button" id="idcheck">중복확인</button>
						<span id="idresult"></span>
					</div>
				</div>
				<div class="row">
					<div class="title">패스워드 :</div>
					<div class="box1">
						<input type="text" name="pw" id="inputPw"
							placeholder="사용하실 pw를 입력하세요">
					</div>
					<div class="box2">
						<div class="resultval" id="pwcheck"></div>
					</div>
				</div>
				<div class="row">
					<div class="title">패스워드확인 :</div>
					<div class="box1">
						<input type="text" id="inputPw2" placeholder="사용하실 pw를 한번더 입력">
					</div>
					<div class="box2"></div>
				</div>
				<div class="row">
					<div class="title">이름 :</div>
					<div class="box1">
						<input type="text" name="name" id="inputName"
							placeholder="사용하실 이름을 입력하세요">
					</div>
					<div class="box2"></div>

				</div>
				<div class="row">
					<div class="title">전화번호 :</div>
					<div class="box1">
						<input type="text" name="phone" id="inputPhone"
							placeholder="사용하실 전화번호 입력하세요">
					</div>
					<div class="box2">
						<div class="resultval" id="phonecheck"></div>
					</div>

				</div>
				<div class="row">
					<div class="title">이메일 :</div>
					<div class="box1">
						<input type="text" name="email" id="inputem"
							placeholder="사용하실 이메일 입력하세요">
					</div>
					<div class="box2">
						<div class="resultval" id="emcheck"></div>
					</div>
				</div>
				<div class="row">
					<div class="title">우편번호 :</div>
					<div class="box1">
						<input type="text" name="zipcode" id="postcode">
					</div>
					<div class="box2" id="post">
						<button type="button" id="postsearch">찾기</button>
					</div>
				</div>
				<div class="row">
					<div class="title">주소1 :</div>
					<div class="box3">
						<input type="text" name="address1" id="address1">
					</div>
				</div>
				<div class="row">
					<div class="title">상세주소 :</div>
					<div class="box3">
						<input type="text" name="address2" id="address2">
					</div>
				</div>
			</div>

			<div class="footer">

				<button type="submit">회원가입</button>
				<button type="button" id="retype">다시입력</button>
				<a href="/"><button type="button">back</button></a>


				<!-- </form>
            <form  id="frm2" style="float: left;">
                <button id="retype">다시검색</button>
            </form> -->

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
				$("#profile").attr("src", "/images/no_profile.png");
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
	
        let inputID = document.getElementById("inputId");
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

        
        $("#inputId").on("input", function(){  //id입력칸 글씨 입력하면 false
        	idcheckk=false;
        }) //중복체크
        
	/*
        idcheck.onclick = function () {
        	let id = inputID.value;
        	
        	let regx = /[a-z0-9_]{8,14}/;

            let regxresult = regx.test(id);

               if (regxresult) {
                  alert("사용 가능한 id 형식입니다")
                  window.open("/idcheck.mem?id="+id,"","width=400, height=300");
               } else{
                  alert("id 형식이 아닙니다")
               }//   window.open("/idcheck.mem?id=" idcheck 앞에 슬러시 붙여줘야됨
               
            	//onclick을 통해서 서버에 (id)데이터를 전달하여 id 중복확인
            		   
            		   
               //회원가입 버튼이 submit이 걸려있어서 중복확인버튼도 같이 걸려 있는데
               //이통쨰로 걸려 있어서 기존대로 하면 회원가입버튼과 중복확인이 동일한 서브밋 처리가 되어서
               // id중복확인 버튼 서브밋 분리시키기 위해 window.open 사용하여 
               //idcheck.mem?id= 서블릿 id= 데이터
 
           
        }   //중복확인  
        */
        /*
   		   $("#idcheck").on("click",function(){
    		let id = $("#inputId").val();
    		
    		let regx = /[a-z0-9_]{8,14}/;

            let regxresult = regx.test(id);
            
    			if(id==""){
    			alert("아이디를 먼저 입력하세요.");
    			$("#inputId").focus();
    			return;
    		}else if(!regxresult){
    			alert("사용이 불가능한 id형식입니다")
    			$("#inputId").val("");
    			
    		}else {
    			$.ajax({
    				url:"/idcheck.mem",
    				data:{"id": id}
    			}).done(function(resp){
    				console.log(resp);
    				if(resp=="true"){  //아이디가 이미 존재하므로 사용할수 없는 경우
    					$("#idresult").html("이미 사용중인 ID입니다")
    				}else{ //아이디가 존재하지 않으므로사용 할수 있는 경우
    					$("#idresult").html("사용 가능한 ID입니다")
    				}
    				
    			})
    		}
    			
    			
    	});    
        */
    	//중복확인 버튼이 있을떄 ajax
        
    	
        $("#inputId").on("blur",function(){
    		let id = $("#inputId").val();
    		
    		let regx = /[a-z0-9_]{8,14}/;

            let regxresult = regx.test(id);
            
    			if(id==""){
    				$("#idresult").html("id 먼저 입력 하세요")
    				$("#inputId").focus();

    			return;
    		}else if(!regxresult){
    			$("#idresult").html("사용불가능한 id 형식")
    			
    			
    		}else {
    			$.ajax({
    				url:"/member/idcheck",
    				data:{"id": id}
    			}).done(function(resp){
    				console.log(resp);
    			
    				if(resp=="true"){  //아이디가 이미 존재하므로 사용할수 없는 경우
    					$("#idresult").html("이미 사용중인 ID입니다");
    					idcheckk = false;
    				
    				}else{ //아이디가 존재하지 않으므로사용 할수 있는 경우
    					$("#idresult").html("사용 가능한 ID입니다");
    					idcheckk = true;
    				}
    				
    			})
    		}

    	}); 
    	
         //중복확인 버튼이 없을떄 ajax방법, 트래픽 소모가 많다
        
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
        	
        	
        	console.log(idcheckk);
            let id = inputID.value;
           let name= inputName.value
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

            	if(id==""){
    				$("#idresult").html("id 먼저 입력 하세요")
    				$("#inputId").focus();

            		return false;
            	}
            	
            	if(!(idcheckk)){
                	alert("아이디 중복 체크를 먼저 수행해주세요") //중요
                	return false;
                } 
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
            
            
            if((regxN.test(name))&&regxP.test(phone)&&regem.test(email)&&resultregx&&resultregx2&&idcheckk){
                    alert("회원가입 성공");
                    
            }else if(id==""){
                alert("id를 입력해주세요")
                return false;
            }
            else if((pw2&&pw)==""){
                alert("pw를 입력해주세요")
                return false;
            }
            else if(name==""){
                    alert("이름을 입력해주세요")
                    return false;
            }else if(phone==""){
            	alert("핸드폰 번호를 입력해주세요")
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


        retype.onclick = function(){
            inputID.value = "";
            inputPw.value="";
              inputPw2.value = "";
            inputName.value="";
            inputPhone.value="";
            inputem.value="";
            address1.value="";
            address2.value="";
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