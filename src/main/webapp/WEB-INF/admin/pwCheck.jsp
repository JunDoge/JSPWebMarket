<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 관리자 비밀번호를 입력하세요: <input type="password" id="pw">
<input type="button" onclick="pwCheck()" value="비밀번호 확인">
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script type="text/javascript">
 var validatePwd = false;
 function pwCheck() {
		let pw = document.getElementById("pw").value;
		axios.post('/admin/validatePwd', null, {
			params : {
				pw
			}
		  })
		  .then(function (response) {
			  if(response.data){
				  alert("인증되었습니다.")
				  validatePwd = response.data;  
			  }else{
				  alert("비밀번호를 틀렸습니다.")
			  }
			  
		  })
		 
	}
</script>
 
