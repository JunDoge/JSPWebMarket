<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="../../PrdCss/basket.css">
<div class="container main-section">
	<div class="row">
		<div class="col-lg-12 pb-2">
			<h4>장바구니</h4>
		</div>
		<div class="col-lg-12 pl-3 pt-3">
			<table class="table table-hover border bg-white">
				<thead>
					<tr>
						<th><input class="form-check-input" type="checkbox"
							name='product' onclick='selectAll(this)' /></th>
						<th>상품</th>
						<th>가격</th>
						<th style="width: 10%;">개수</th>
						<th>총가격</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tbody>
					<form action="/prd/insPay" method="post">
						<c:forEach items='${lstPrd}' var='lstPrd' varStatus="status">
							<tr>
								<th><input class="form-check-input" type="checkbox"
									name='product' data-price="${lstPrd.price}"
									data-bk_id="${lstPrd.bk_id}"
									onclick="handleCheckboxClick(this, ${lstPrd.price},${lstPrd.bk_id})"></th>
								<td>
									<div class="row">
										<div class="col-lg-2 Product-img">
											<img
												src="https://webmarket-main.s3.ap-northeast-2.amazonaws.com/${lstImg[status.index].img_id}"
												class="img-responsive" />
										</div>
										<div class="col-lg-10"
											style="text-align: center; vertical-align: middle; margin: auto;">
											<h4 class="nomargin">${lstPrd.title}</h4>
										</div>
									</div>
								</td>
								<td>${lstPrd.price}</td>
								<td data-th="Quantity">
								
								<input type="number" id="${lstPrd.bk_id}"
									class="form-control text-center" value="1" min="1"
									onchange="calculateTotalPrice(${status.index}, ${lstPrd.price}, this.value)"></td>
								
								
								<td id="totalPrice${status.index}">${lstPrd.price}</td>
								
								<td class="actions" data-th="" style="width: 10%;">
									<button type="button" class="btn btn-danger"
										onclick="confirmDelete(${lstPrd.bk_id})">제거</button>
								</td>
							</tr>

						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<th></th>
							<td style="text-align: right;"><button class="pay" id="kakao" 
									type="button">카카오 페이</button>
								<button class="pay" id="toss" type="button">토스 페이
								</button></td>
							<td colspan="2" class="hidden-xs"></td>
							<td class="hidden-xs text-center" style="width: 10%;"></td>
							<td><button type="button" class="btn btn-primary text-white" onclick="payment()"
									style="width: 170px">
									<strong id="totalPrice"> 0</strong>결제하기
								</button></td>
	
						</tr>
					</tfoot>
				</form>
			</table>
			<a href="/prd/main" class="btn btn-info text-white">쇼핑 더하기</a>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script src="https://cdn.portone.io/v2/browser-sdk.js"></script>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript" src="../../PrdJs/basket.js">
