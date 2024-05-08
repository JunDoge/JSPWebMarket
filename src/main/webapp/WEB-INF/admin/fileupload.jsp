<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록페이지</title>

<link href="../../AdminCss/upload.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<div class="login-box">
		<h2>상품등록</h2>
		<form action="/admin/upload" enctype="multipart/form-data"
			method="post" name="frm">
			<div class="user-box">
				<input type="text" name="title" required="상품제목"> <label>제목</label>
			</div>
			<div class="user-box">
				<input type="text" name="price" required="상품가격"> <label>상품가격</label>
			</div>
			<div class="user-box">
				<input type="text" name="Ssize" required="S사이즈"> <label>S사이즈
					수량</label>
			</div>
			<div class="user-box">
				<input type="text" name="Msize" required="M사이즈"> <label>M사이즈
					수량</label>
			</div>
			<div class="user-box" style="height: 50px;">
				<input type="text" name="Lsize" required="L사이즈"> <label>L사이즈
					수량 </label>
			</div>
			<div class="block"
				style="position: absolute; top: 0px; left: 420px; min-width: 200px; max-width: 460px; background: rgba(0, 0, 0, .5); box-sizing: border-box; box-shadow: 0 15px 25px rgba(0, 0, 0, .6); border-radius: 10px;">
				<div style="text-align: center;">
					<label class="block" for="mainFile">메인사진</label>
					<input id="mainFile" type="file" name="mainFile" multiple hidden>
				</div>

				<div id="file-container">
					<ul id="fileList"
						style="padding: 0px; display: flex; flex-wrap: wrap; width: 430px">
					</ul>

				</div>
				<input type="hidden" id="fileArray">
			</div>


			<div class="block"
				style="position: absolute; top: 0px; left: 900px; min-width: 200px; max-width: 460px; background: rgba(0, 0, 0, .5); box-sizing: border-box; box-shadow: 0 15px 25px rgba(0, 0, 0, .6); border-radius: 10px;">
				<div style="text-align: center;">
					<label class="block" for="desFile">내용파일</label>
					<input id="desFile" type="file" name="desFile" multiple hidden>
				</div>

				<div id="file-container">
					<ul id="fileList2"
						style="padding: 0px; display: flex; flex-wrap: wrap; width: 430px">
					</ul>

				</div>
				<input type="hidden" id="fileArray2">
			</div>
			<label style="color:#fff; ">카테고리</label>
			<div class="select" style="display:flex; margin-bottom: 30px; height: 40px">
				<select class="select-text" required id="category"
					onchange="showOptions()" style="color: #FFF;">
					<option value="0" disabled selected>메인태그</option>
					<option value="상의">상의</option>
					<option value="아우터">아우터</option>
					<option value="하의">하의</option>
					<option value="신발">신발</option>
					<option value="모자">모자</option>
				</select> <select class="select-text" id="options" name="cat_id" style="color:#fff;" required>
					<option value="0">서브카테고리</option>
				</select>
			</div>
			<div class="user-box"
				style="margin-top: 30px; border-top: 1px solid #fff;">
				<input type="password" id="pw"
					style="font-size: 30px; text-align: center;"
					placeholder="***********">
			</div>
			<div class="user-box">
				<input type="button" onclick="pwCheck()" value="비밀번호 확인">
			</div>
			<button onclick="validateForm()" class="submit-button">
				<span></span> <span></span> <span></span> <span></span> UPLOAD
			</button>
		</form>
	</div>
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
	<script src="../../AdminJs/upload.js">
</script>

</body>
</html>