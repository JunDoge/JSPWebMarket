<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="../../AdminCss/detail.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
</head>

<body>

	<form action="/admin/update" method="post" name="frm">
		<div class="card-wrapper">
			<div class="card">
				<div class="product-title">
					제목 : <input type="text" value='${prd.title}' name="title" required="required">
				</div>
				<!-- card left -->
				<div class="product-imgs">
					<div class="img-display">
						<div class="img-showcase">
							<c:forEach items='${lstImg}' var='mainImg'>
								<c:set var="main" value="${fn:substring(mainImg.img_id,0,4)}" />
								<c:if test="${main eq 'main'}">
									<button =type="button"
										style="box-sizing: border-box; width: 100%; flex-shrink: 0; border: none;"
										onclick="changeImg('${mainImg.img_id}', 'main/')">
										<img
											src="https://webmarket-main.s3.ap-northeast-2.amazonaws.com/${mainImg.img_id}"
											alt="shoe image" />
									</button>
									<input type="file" id='${mainImg.img_id}'
										style="display: none;" />
								</c:if>
							</c:forEach>
						</div>
					</div>
					<div class="img-select">
						<c:forEach items='${lstImg}' var='mainImg' varStatus='status'>
							<c:set var="main" value="${fn:substring(mainImg.img_id,0,4)}" />
							<c:if test="${main eq 'main'}">
								<div class="img-item">
									<a href="#" data-id="${status.count}"> <img
										src="https://webmarket-main.s3.ap-northeast-2.amazonaws.com/${mainImg.img_id}"
										alt="shoe image" />
									</a>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
				<!-- card right -->
				<div class="product-price">
					<p class="new-price">
					<div>
						가격 : <input type="text" value='${prd.price}' name="price" required="required">
					</div>
					</p>
					<div class="purchase-info">
						<c:set var="Ssize" value="${fn:substring(prd.count,1,4)}" />
						<c:set var="Msize" value="${fn:substring(prd.count,5,8)}" />
						<c:set var="Lsize" value="${fn:substring(prd.count,9,12)}" />
						<div>
							S사이즈 갯수 : <input type="text" value='${Ssize}' name="Ssize" required="required" />
						</div>
						<div>
							M사이즈 갯수 : <input type="text" value='${Msize}' name="Msize" required="required" />
						</div>
						<div>
							L사이즈 갯수 : <input type="text" value='${Lsize}' name="Lsize" required="required" />
						</div>
						<button type="button" class="btn" onclick="validateForm()">수정</button>
						<button type="button" class="btn" onclick="prdDel(${prd.prd_id})">
							삭제 <i class="fas fa-shopping-cart"></i>
						</button>
					</div>
				</div>
				<div>
					<c:forEach items='${lstImg}' var='desImg'>
						<c:set var="des" value="${fn:substring(desImg.img_id,0,3)}" />
						<c:if test="${des eq 'des'}">
							<button type="button"
								onclick="changeImg('${desImg.img_id}', 'des/')">
								<img
									src="https://webmarket-main.s3.ap-northeast-2.amazonaws.com/${desImg.img_id}" />
							</button>
							<input type="file" id='${desImg.img_id}' style="display: none;" />
						</c:if>
					</c:forEach>
				</div>

			</div>
		</div>
		<input type="text" name="prd_id" value="${prd.prd_id}" style="display: none;" >
	</form>

	<!-- Bootstrap core JS-->
	<jsp:include page="/WEB-INF/admin/pwCheck.jsp" />
	<script type="text/javascript">
		function prdDel(prd_id) {
			if (!validatePwd) {
				alert("관리자 비밀번호로 인증해주세요")
			} else {
				location.href = "http://localhost:9123/admin/delete?prd_id="
						+ prd_id;
			}

		}

		function validateForm() {
			if (!validatePwd) {
				alert("관리자 비밀번호로 인증해주세요")
			} else {
				document.frm.Ssize.value = document.frm.Ssize.value.padStart(3,
						0);
				document.frm.Msize.value = document.frm.Msize.value.padStart(3,
						0);
				document.frm.Lsize.value = document.frm.Lsize.value.padStart(3,
						0);
				alert(document.frm.Ssize.value + document.frm.Msize.value
						+ document.frm.Lsize.value)
				document.frm.submit();
			}

		}

		function changeImg(img_id, folderNm) {
			if(confirm("수정하시겟습니까 ?")){
				if(!validatePwd){
			        alert("관리자 비밀번호로 인증해주세요")
			      }else{
			    	  let fileInput = document.getElementById(img_id);
					    fileInput.click();
					    fileInput.onchange = function() {
					      let file = fileInput.files[0];  
					      uploadFile(file, img_id,folderNm); 
			      }
				
			    }
			
			}
		}
		
		
		function uploadFile(file, img_id, folderNm) {
			  let formData = new FormData();
			  formData.append("file", file); 
			  formData.append("img_id", img_id);  
			  formData.append("folderNm", folderNm);  
			  axios.post('/admin/imgUpdate', formData, {
				    headers: {
				      'Content-Type': 'multipart/form-data'
				    }
				}).then(function (res) {
			    	if(res.data == 1){
			    		history.go(0);
			    	}
			  });
			  
			}
	</script>
	<script type="text/javascript" src="../../AdminJs/detail.js"></script>