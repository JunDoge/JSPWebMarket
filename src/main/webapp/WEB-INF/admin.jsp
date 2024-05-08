<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>상품메인페이지</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="../AdminJs/sidebar.js"></script>
<link href="../MainCss/sidebar.css" rel="stylesheet">
<link href="../MainCss/searchbar.css" rel="stylesheet">
</head>
<body>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="/admin/prdMain">관리자페이지</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">선택옵션</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="#!">선택옵션</a></li>
							<li><hr class="dropdown-divider" /></li>
							<li><a class="dropdown-item" href="/admin/upload">상품등록</a></li>
							<li><a class="dropdown-item" href="/admin/logout">로그아웃</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Header-->
	<header class="bg-dark py-5">
		<div class="container px-4 px-lg-5 my-5">
			<div class="text-center text-white">
				<div class="search-bar">
					<input type="text" placeholder="Search" onkeydown="search(event)">
				</div>
			</div>
			<div class="text-center text-white">
				<h1 class="display-4 fw-bolder">관리자페이지입니다.</h1>
			</div>
		</div>
	</header>

	<aside class="sidebar">
		<div id="leftside-navigation" class="nano">
			<ul class="nano-content">
				<li><a href="/admin/prdMain"><i class="fa fa-dashboard"></i><span>전체</span></a>
				</li>

				<c:forEach items='${cats}' var='lstCat' varStatus="status">
					<c:choose>


						<c:when test="${status.index eq 0}">
							<c:set var="cat_nm" value="${lstCat.cat_nm }" />
							<li class="sub-menu active"><a href="javascript:void(0);"><i
									class="fa fa-cogs"></i><span>${lstCat.cat_nm }</span><i
									class="arrow fa fa-angle-right pull-right"></i></a>
								<ul>
									<li><a
										href="http://localhost:9123/admin/prdMain?cat_id=${lstCat.cat_id}">${lstCat.cat_des }</a></li>
						</c:when>




						<c:when test="${cat_nm eq lstCat.cat_nm }">
							<li><a
								href="http://localhost:9123/admin/prdMain?cat_id=${lstCat.cat_id}">${lstCat.cat_des }</a></li>
						</c:when>



						<c:otherwise>
							<c:set var="cat_nm" value="${lstCat.cat_nm }" />
			</ul>
			<li class="sub-menu"><a href="javascript:void(0);"><i
					class="fa fa-cogs"></i><span>${lstCat.cat_nm }</span><i
					class="arrow fa fa-angle-right pull-right"></i></a>
				<ul>
					<li><a
						href="http://localhost:9123/admin/prdMain?cat_id=${lstCat.cat_id}">${lstCat.cat_des }</a></li>
					</c:otherwise>
					</c:choose>
					</c:forEach>
		</div>
		<button>
			<span class="sidebar-btn">OPEN</span>
		</button>
	</aside>
	<jsp:include page="/WEB-INF/${view}.jsp" />

	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">2조 관리자페이지입니다. Website 2023</p>
		</div>
	</footer>
	<!-- Bootstrap core JS-->
	<script  type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script type="text/javascript">
		function moveDetailPrd(prd_id) {
			location.href = "http://localhost:9123/admin/prdDetail?prd_id="
					+ prd_id;
		}
	</script>
	
</body>
</html>
