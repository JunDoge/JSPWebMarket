<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

        <!-- Navigation-->

        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                <c:forEach items='${lstPrd}' var='lstPrd' varStatus="status">
                  <!-- 여기서 부터 C:foreach--> 
                  <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="https://webmarket-main.s3.ap-northeast-2.amazonaws.com/${lstImg[status.index].img_id}" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">${lstPrd.title}</h5>
                                    <!-- Product price-->
                                    ${lstPrd.price}
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" onclick="moveDetailPrd(${lstPrd.prd_id})">View options</a></div>
                            </div>
                        </div>
                    </div>
                  </c:forEach>
                    <!-- 여기까지 반복-->
                </div>
            </div>
        </section>
        		<ul class="pagination justify-content-center">
			
			<c:choose>
				<c:when test="${paging.startRow + 4 >= paging.total_recode}">
					<c:set var="endWith" value="${paging.total_recode}" />
				</c:when>
				<c:otherwise>
					<c:set var="endWith" value="${paging.startRow + 4}" />
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${paging.cat_id eq null and  paging.search eq null}">
				<li class="page-item"><a class="page-link" href="http://localhost:9123/admin/prdMain?pageNum=1">Previous</a>
					</li>
					<c:forEach begin="${paging.startRow}" end="${endWith}"
						varStatus="status">
						<li class="page-item"><a class="page-link"
							href="http://localhost:9123/admin/prdMain?pageNum=${status.index}">${status.index}</a></li>
					</c:forEach>
					<li class="page-item"><a class="page-link" href="http://localhost:9123/admin/prdMain?pageNum=${endWith+1}">Next</a></li>
				</c:when>
				
				
				
				<c:when test="${paging.search != null }">
				<li class="page-item"><a class="page-link" href="http://localhost:9123/admin/prdMain?pageNum=1&search=${paging.search}">Previous</a>
					</li>
					<c:forEach begin="${paging.startRow}" end="${endWith}"
						varStatus="status">
						<li class="page-item"><a class="page-link"
							href="http://localhost:9123/admin/prdMain?pageNum=${status.index}&search=${paging.search}">${status.index}</a></li>
					</c:forEach>
					<li class="page-item"><a class="page-link" href="http://localhost:9123/admin/prdMain?pageNum=${endWith+1}&search=${paging.search}">Next</a></li>
				</c:when>
				
				
				
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="http://localhost:9123/admin/prdMain?pageNum=1&cat_id=${paging.cat_id}">Previous</a>
					</li>
					<c:forEach begin="${paging.startRow}" end="${endWith}"
						varStatus="status">
						<li class="page-item">
						<a class="page-link"
							href="http://localhost:9123/admin/prdMain?pageNum=${status.index}&cat_id=${paging.cat_id}">${status.index}</a></li>
					</c:forEach>
					<li class="page-item"><a class="page-link" href="http://localhost:9123/admin/prdMain?pageNum=${endWith+1}&cat_id=${paging.cat_id}">Next</a></li>	
				</c:otherwise>
			</c:choose>
		</ul>
        <!-- Footer-->
        <!-- Bootstrap core JS-->
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <script type="text/javascript">
          function moveDetailPrd(prd_id) {
              location.href = "http://localhost:9123/admin/prdDetail?prd_id=" + prd_id;
          }
      </script>
    </body>
</html>
