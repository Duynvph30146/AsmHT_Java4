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
        <form method="post">
            <h1 class="text-center">ADD MOVIE</h1>
            <div>
                Id:<input name="id" class="form-control" value="${param.id}">
            </div>
            <div style="color: red">
                ${errid}
            </div>
            <div style="color: red">
                ${error}
            </div>
            <div>
                Title:<input name="title" class="form-control" value="${param.title}">
            </div>
            <div style="color: red">
                ${errtilte}
            </div>
            <div>
                Description:
                <textarea name="description" cols="30" rows="5" class="form-control" value="${param.description}"></textarea>
            </div>
            <div style="color: red">
                ${errdescription}
            </div>
            Active:<input name="active" type="checkbox">
            <p>
            <div>
                Image:<input name="poster" class="form-control" value="${param.poster}">
            </div>
            </p>
            <div style="color: red">
                ${errposter}
            </div>
            <button class="btn btn-primary">Save</button>
        </form>
    </div>
</div>





















