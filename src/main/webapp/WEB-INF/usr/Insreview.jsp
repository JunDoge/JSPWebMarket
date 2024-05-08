<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
    crossorigin="anonymous">
<link rel="stylesheet" href="../../UsrCss/insreview.css">
</head>
<body>
    <div class="modal fade" id="myModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="" method="post">
                    <div id="app">
                        <header>리뷰 작성</header>
                        <div class="info">
                            <img src="옷사진1.jpg" width="150px" height="200px">
                            <div class="text-container">
                                <div class="title-container">
                                    <h5>이쁜후드티이쁜후드티이쁜후드티이쁜후드티이쁜후드티이쁜후드티이쁜후드티이쁜후드티</h5>
                                </div>
                                <div class="title-container">
                                    <h5>100000</h5>
                                </div>
                                <div class="title-container">
                                    <h5>23.11.30</h5>
                                </div>
                            </div>
                            <div class="stars">
                                <button type="button" class="star1" onclick="score()">
                                    <i class="far fa-star starj1"></i>
                                </button>
                                <button type="button" class="star2" onclick="score()">
                                    <i class="far fa-star starj2"></i>
                                </button>
                                <button type="button" class="star3" onclick="score()">
                                    <i class="far fa-star starj3"></i>
                                </button>
                                <button type="button" class="star4" onclick="score()">
                                    <i class="far fa-star starj4"></i>
                                </button>
                                <button type="button" class="star5" onclick="score()">
                                    <i class="far fa-star starj5"></i>
                                </button>
                            </div>
                            <textarea name="content" v-model="content"></textarea>
                            <input type="submit" class="submit" value="작성하기">
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                data-bs-target="#my-modal" aria-label="Close"></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/850830ed04.js"
        crossorigin="anonymous"></script>
    <script src="../../UsrJs/insreview.js"></script>
    <script>
        var myModal = new bootstrap.Modal(document.getElementById('myModal'), {
            keyboard : false
        });
        myModal.show();
    </script>
</body>
</html>
