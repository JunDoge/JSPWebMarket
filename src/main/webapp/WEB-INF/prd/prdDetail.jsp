<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="ko_KR"/>
<link rel="stylesheet" href="../../PrdCss/detail.css">
<link rel="stylesheet" href="../../PrdCss/comment.css">
<div class="card-wrapper">
	<div class="card">
		<h1 class="cpc-product-title">${prd.title}</h1>
		<!-- card left -->
		<div class="product-imgs">
			<div class="img-display">
				<div class="img-showcase">
					<c:forEach items='${lstImg}' var='mainImg'>
						<c:set var="main" value="${fn:substring(mainImg.img_id,0,4)}" />
						<c:if test="${main eq 'main'}">
							<img
								src="https://webmarket-main.s3.ap-northeast-2.amazonaws.com/${mainImg.img_id}"
								alt="shoe image" />
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
			<div class="purchase-info">
				<c:set var="Ssize" value="${fn:substring(prd.count,1,4)}" />
				<c:set var="Msize" value="${fn:substring(prd.count,5,8)}" />
				<c:set var="Lsize" value="${fn:substring(prd.count,9,12)}" />
				<section class="cpc-size-section">
					<select id="size" class="cpc-product-size-select">
						<option value="0">----사이즈선택----</option>
						<c:choose>
							<c:when test="${Ssize == '000'}">
								<option value="-1">S사이즈품절</option>
							</c:when>
							<c:otherwise>
								<option value="S">s사이즈 선택</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${Msize == '000'}">
								<option value="-1">M사이즈품절</option>
							</c:when>
							<c:otherwise>
								<option value="M">M사이즈 선택</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${Lsize == '000'}">
								<option value="-1">L사이즈품절</option>
							</c:when>
							<c:otherwise>
								<option value="L">L사이즈 선택</option>
							</c:otherwise>
						</c:choose>
					</select>
				</section>
				<section class="cpc-color-section">
					<h2 class="cpc-product-desc">
						<fmt:formatNumber value="${prd.price}" type="currency"
							currencySymbol="￦" />
						원
					</h2>
				</section>

				<section class="cpc-atc-share-section">
					<button class="btn btn-outline-dark" type="button"
						onclick="insBasket(${prd.prd_id})">
						<i class="bi-cart-fill me-1"></i> 장바구니 담기 <span
							class="badge bg-dark text-white ms-1 rounded-pill">-></span>
					</button>
				</section>
			</div>
		</div>
		<div>
			<c:forEach items='${lstImg}' var='desImg'>
				<c:set var="des" value="${fn:substring(desImg.img_id,0,3)}" />
				<c:if test="${des eq 'des'}">
					<img
						src="https://webmarket-main.s3.ap-northeast-2.amazonaws.com/${desImg.img_id}" />
				</c:if>
			</c:forEach>
			<hr>

			<div class="Comment_input_box">
				<div class="box">
					<label> <input type="text" id="cmt_des"
						placeholder="댓글 추가..."> <i></i>
					</label>
				</div>
				<br> <br>
				<div>
					<button id="Comment_btn1" class="btn btn-warning">취소</button>
					<button id="Comment_btn2" class="btn btn-secondary"
						onclick="cmtInsert('${prd.prd_id}')">댓글</button>
				</div>
			</div>
			<div id="cmts" style="margin-top: 50px; text-align: end;">
				<c:choose>
					<c:when test="${fn:length(cmts) == 0}">
						<h2>문의글이 없습니다.</h2>
					</c:when>
					<c:otherwise>
						<c:forEach items="${cmts}" var="cmt">
							<div id="${cmt_id}">
								<p style="text-align: justify; font-size: 20px">
									${cmt.name} : <span id="comment-${cmt.cmt_id}">${cmt.c_des}</span>
								</p>
								<c:if test="${cmt.usr_id eq loginUser.usr_id }">
									<button class="btn btn-info" id="edit-${cmt.cmt_id}"
										onclick="changeToInput('${cmt.cmt_id}','${cmt.c_des}')">댓글수정</button>
									<button class="btn btn-danger"
										onclick="deleteCmt(${cmt.cmt_id})">댓글삭제</button>
								</c:if>
							</div>

						</c:forEach>

					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>


<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<script src="../../PrdJs/detail.js"></script>