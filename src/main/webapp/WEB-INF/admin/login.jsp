<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../../AdminCss/login.css" rel="stylesheet">
</head>
<body>
<div class="login-box">
  <h2>Login</h2>
  <form action="/admin/login" method="post">
    <div class="user-box">
      <input type="text" name="id" required="id">
      <label>아이디</label>
    </div>
    <div class="user-box">
      <input type="password" name="pw" required="*******">
      <label>비밀번호</label>
    </div>
    <button type="submit" class="submit-button">
    <span></span>
    <span></span>
    <span></span>
    <span></span>
    Submit
  </button>
  </form>
</div>
</body>
</html>