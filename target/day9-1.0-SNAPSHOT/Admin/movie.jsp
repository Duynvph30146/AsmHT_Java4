

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <style>
        /*.Menu > a {*/
        /*    font-family: Arial, Helvetica, sans-serif;*/
        /*}*/

        /*.Menu > a:hover {*/
        /*    background-color: rgb(11, 94, 215);*/
        /*    color: red;*/
        /*}*/
        .Menu {
            height: 100%;
            width: 220px;
            position: fixed;
            z-index: 1;
            background-color: rgb(0, 17, 36);
            padding-top: 10px;
            padding-left: 10px;
        }

        .Menu a {
            padding: 15px 10px;
            text-decoration: none;
            font-size: 18px;
            color: white;
            display: block;
            border: none;
            background: none;
        }

        .Menu a:hover {
            color: red;
        }
    </style>
</head>
<body>
<div class="container-fluid"style="background-color: rgb(235,235,235)">
    <div class="row">
        <div class="Menu col-lg-4">
            <h1 class="h4 text-center" style="color: white">Duynvph30146</h1>
            <a href="bai1">Quản lý User</a>
            <a href="bai2">Quản lý Video</a>
            <a href="#clients">Báo cáo thống kê</a>
        </div>
        <--->
        <div class="col-lg-8">
            <div style="padding-left: 1200px;width: 1500px;height: 50px;line-height: 50px;background-color: white" class="d-flex">
                <div>Xin chào ${sessionScope.fullname}</div>
                <div style="padding-left: 10px">
                    <c:if test="${sessionScope.fullname==null}">
                        <a href="viewLogin">Login</a>
                    </c:if>
                    <c:if test="${sessionScope.fullname != null}">
                        <a href="logout">Logout</a>
                    </c:if>
                </div>
            </div>
            <h1 class="text-center" style="margin-top: 20px">MOVIE LIST</h1>
            <table class="table table-hover table-bordered" style="margin-left: 200px;margin-top: 20px;width: 1200px;height: 300px">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Active</th>
                    <th>Image</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="movie" items="${listMovie}">
                <tr>
                    <td>${movie.id}</td>
                    <td>${movie.title}</td>
                    <td>${movie.description}</td>
                    <td>${movie.active ?"Yes":"No"}</td>
                    <td>
                        <iframe width="300" height="200" src="${movie.poster}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
                    </td>
                    <td>
                        <a href="updateVideo?id=${movie.id}" class="btn btn-warning">Update</a>
                        <a href="deleteVideo?id=${movie.id}" class="btn btn-danger mt-1" onclick="return confirm('Bạn có chắc muốn xóa movie này không?')">Delete</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
        <div style="margin-left: 300px"><a href="addVideo" class="btn btn-primary">New Movie</a></div>
    </div>
</div>
</body>
</html>






