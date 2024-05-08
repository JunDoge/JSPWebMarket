<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
    crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../../UsrCss/pay.css">
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script type="text/javascript" src="../../UsrJs/pay.js">
</script>
<!-- 리뷰 작성 모달을 위한 빈 div -->
<div id="reviewModal"></div>
<div class="container main-section">
    <div class="row">
        <div class="col-lg-12 pb-2">
            <h4>결제내역페이지</h4>
        </div>
        <div class="col-lg-12 pl-3 pt-3">
            <c:set var="p_dt" value="" />
            <c:set var="endpoint" value="" />
            <c:forEach items="${pays}" var="lstPays" varStatus="status">
                <c:if test="${lstPays.p_dt ne p_dt}">
                    <c:set var="p_dt" value="${lstPays.p_dt}" />
                    <table class="table table-hover border bg-white">
                        <thead>
                            <tr>
                                <th>결제날짜: ${lstPays.p_dt}</th>
                                <th class="mid">몰랑</th>
                                <th class="mid">사이즈</th>
                                <th class="mid" style="width: 10%;">개수</th>
                                <th class="mid">가격</th>
                                <th style="text-align: right;"><button type="button" onclick="delPayList(${payhis[status.index].pay_id})">X</button></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${prds}" var="lstPrd" begin="${status.index}" varStatus="prdStatus">
                                <c:choose>
                                    <c:when test="${(payhis[prdStatus.index].pay_id eq endpoint) or (status.index eq prdStatus.index)}">
                                        <c:set var="endpoint" value="${payhis[prdStatus.index].pay_id}" />
                                        <tr>
                                            <th class="mid">
                                                <div class="col-lg-2 Product-img">
                                                    <img
                                                        src="https://webmarket-main.s3.ap-northeast-2.amazonaws.com/${imgs[prdStatus.index].img_id}"
                                                        class="img-responsive" />
                                                </div>
                                                <td class="mid">
                                                    <div class="row">
                                                        <div class="col-lg-10">
                                                            <h4 class="nomargin">${payhis[prdStatus.index].size}</h4>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td class="mid">
                                                    <div>
                                                        <h4 class="nomargin">${payhis[prdStatus.index].total_count}</h4>
                                                    </div>
                                                </td>
                                                <td class="mid">
                                                    <h4 class="nomargin">${pays[prdStatus.index].total_price}</h4>
                                                </td>
                                                <td class="mid" id="totalPrice${prdStatus.index}"><h4
                                                        class="nomargin">${lstPrd.count}</h4></td>
                                                <td class="mid" data-th="" style="width: 10%;">
                                                    <button type="button" class="btn btn-info" onclick="openReviewWriteModal(${lstPrd.prd_id})">리뷰작성</button>
                                                    <button type="button" class="btn btn-danger" onclick="moveDetailPrd(${lstPrd.prd_id})"
                                                        style="margin-top: 20px; width: 90px;">재구매</button>
                                                </td>
                                        </tr>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>

<script>
function openReviewWriteModal(id) {
    $('#reviewModal').load('/usr/insreview', function() {
        var myModal = new bootstrap.Modal(document.getElementById('reviewModal'), {keyboard: false});
        myModal.show();
    });
}
</script>
