<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<form action="viewLogin" method="post">
    <h1>LOGIN</h1>
    <p>Username:<input type="text" name="fullname" class="form-control"></p>
    <p>Password:<input type="password" name="password"class="form-control"></p>
    <button class="btn btn-primary">Đăng nhập</button>
</form>