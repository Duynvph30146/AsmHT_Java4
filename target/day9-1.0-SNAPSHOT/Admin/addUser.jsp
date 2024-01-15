<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
    .bg-login {
        position: relative;
        width: 100%;
        min-height: auto;
        background-position: right 0px top 0px;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        background-size: cover;
        -o-background-size: cover;
    }

    .login-form {
        border: 1px solid #DDD;
        border-radius: 4px;
        max-width: 600px;
        padding: 20px;
        margin-top: 100px;
        margin-left: auto;
        margin-right: auto;
    }

</style>

<div class="bg-login">
    <div class="login-form">
        <h2 class="text-center">ADD USER</h2>
        <form method="post">
            <div>
                <label for="id">ID:</label>
                <input type="text" id="id" name="id"  class="form-control" value="${param.id}"><br>
            </div>
            <div style="color: red">
                ${errid}
            </div>
            <div style="color: red">
                ${error}
            </div>
            <div>
                <label>Email:</label>
                <input type="text" id="email" name="email"  class="form-control" value="${param.email}"><br>
            </div>
            <div style="color: red">
                ${erremail}
            </div>
            <div>
                <label>Password:</label>
                <input type="password" id="password" name="password"  class="form-control" value="${param.password}"><br>
            </div>
            <div style="color: red">
                ${errpassword}
            </div>
            <div>
                <label>Full Name:</label>
                <input type="text" id="fullname" name="fullname"  class="form-control" value="${param.fullname}"><br>
            </div>
            <div style="color: red">
                ${errfullname}
            </div>
         <p>
             <label>Admin:</label>
             <input type="checkbox" id="admin" name="admin" value="true" checked?><br>
         </p>
            <button class="btn btn-primary">Add</button>
        </form>
    </div>
</div>
