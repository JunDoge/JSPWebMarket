<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

<head>
    <title>회원정보 수정 페이지</title>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
   <link rel="stylesheet" href="../../UsrCss/usrUpdate.css">
    
</head>

<body>
    <div class="card col-6">
        <div class="card-body">
            <h2 class="text-center mb-4">회원정보 수정</h2>
            <div class="text-center">
                <input type="file" id="profile-upload" style="display: none;">
                <div id="profile-image" onclick="document.getElementById('profile-upload').click();"></div>
            </div>
            <form id="user-form">
                <div class="form-group">
                    <label for="email">이메일</label>
                    <input type="email" class="form-control" id="email" placeholder="${loginUser.email}" readonly>
                </div>
                <div class="input-group mb-3">
                    <input type="text" class="form-control" id="name" placeholder="${loginUser.name }">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button" onclick="updateUsr('name')">변경</button>
                    </div>
                </div>
                <div class="input-group mb-3" id="phone-group">
                    <input type="tel" pattern="[0-9]{3}-?[0-9]{4}-?[0-9]{4}" class="form-control" id="ph_nm" placeholder="${loginUser.ph_nm }">

                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button" id="phone-change">변경</button>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="text" class="form-control" id="addr" placeholder="${loginUser.addr}">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button" onclick="updateUsr('addr')">변경</button>
                    </div>
                </div>
                <div class="form-group d-flex align-items-center">
                    <label class="mb-0">이메일 수신하기</label>
                    <div class="tg-list-item">
                        <input class="tgl tgl-ios" id="cb2" type="checkbox"/>
                        <label class="tgl-btn" for="cb2"></label>
                    </div>
                </div>
            </form>
        </div>
    </div>

<script type="text/javascript" src="../../UsrJs/usrUpdate.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</body>

</html>