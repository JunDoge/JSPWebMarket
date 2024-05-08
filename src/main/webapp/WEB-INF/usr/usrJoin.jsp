<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지</title>
<link rel="stylesheet" href="../../UsrCss/join.css">
</head>
<body>
	<div class="overlay">
		<!-- LOGN IN FORM by Omar Dsoky -->
		<form action="/usr/join" method="post">
			<!--   con = Container  for items in the form-->
			<div class="con">
				<!--     Start  header Content  -->
				<header class="head-form">
					<h2>환영합니다 !</h2>
				</header>
				<br>
				<div class="field-set">
					<span class="input-item"> <i class="fa fa-user-circle"></i>
					</span>
					<!--  닉네임-->
					<input class="form-input" id="txt-input" type="text"
						value='${usr.name}' name="name" readonly="readonly"> <br>
					<span class="input-item"> <i class="fa fa-user-circle"></i>
					</span>
					<!-- 이메일-->
					<input class="form-input" id="txt-input" type="text"
						value='${usr.email}' name="email" readonly="readonly">
					<!--   핸드폰 번호-->
					<input class="form-input" id="phone" type="text" name="ph_nm"
						placeholder="핸드폰번호" required>
					<button type="button" value="인증코드받기" onclick="validatePhone()">
						<span>인증하기</span>
					</button>
					<!--   인증코드-->
					<span id="code_id"> <input class="form-input" id="code" type="text"
						name="code" placeholder="인증코드입력" required>
					
					<button type="button" onclick="validateCode()">
						<span>인증확인</span>
					</button>
					</span>
					<!--   주소-->
					<input class="form-input" id="addr1" type="text" name="pst_addr"
						maxlength="20" placeholder="주소" readonly>
					<button type="button" id="chk_adress" class="btn_position">
						<span>주소찾기</span>
					</button>
					<!--   도로명-->
					<input class="form-input" id="addr2" type="text" name="rd_addr"
						placeholder="도로명 주소" /> <br> <span class="input-item">
						<i class="fa fa-user-circle"></i>
					</span>
					<!--   상세주소-->
					<input class="form-input" id="txt-input" id="addr3" type="text"
						name="det_addr" placeholder="상세 주소" /> <br>
					<br>
					<br>
					<button type="submit" id="submit" class="log-in">회원가입</button>
				</div>
		</form>
	</div>
	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script>
document.getElementById("code_id").style.display = 'none';
function validatePhone() {
	var ph_nm = document.getElementById("phone").value;
	axios.post('/usr/validatePhone', null, {
		params : {
			ph_nm
		}
	  })
	  .then(function (response) {
	    if(response.data.length != 0){
	       document.getElementById('phone').readOnly = true;
	       document.getElementById("code_id").style.display = 'block';  // hide	
	    }else{
	    	alert("핸드폰 번호를 다시 확인해주세요.")
	    }
	  
	  })
	 
}


function validateCode() {
	var ph_nm = document.getElementById("phone").value;
	var ph_code = document.getElementById("code").value
	axios.post('/usr/validateCode', null, {
		params : {
			ph_nm,
			ph_code
		}
	  })
	  .then(function (response) {
	    if(response.data == 1){
	    	alert("인증 되었습니다.")
	    }else{
	    	alert("인증번호를 다시 확인 해주세요.")
	    }
	   
	  })
}


chk_adress.addEventListener("click", function(){
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 도로명 조합형 주소 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }
            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
            if(fullRoadAddr !== ''){
                fullRoadAddr += extraRoadAddr;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            console.log(data.zonecode);
            console.log(fullRoadAddr);


            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('addr1').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('addr2').value = fullRoadAddr;

            /* document.getElementById('signUpUserPostNo').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
            document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */
        }
    }).open();
});
</script>
</body>
</html>