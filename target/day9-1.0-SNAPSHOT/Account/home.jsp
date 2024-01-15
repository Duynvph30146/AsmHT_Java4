<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav style="background-color: rgb(13,11,255);height: 50px;line-height: 50px" class="d-flex">

    <div class="navbar-nav text-white" style="padding-left: 20px">
        <a href="loadVideo">Videos</a>
    </div>
    <div class="navbar-nav text-white" style="padding-left: 20px">
        <a href="favorite">Favorite</a>
    </div>

    <div class="d-flex text-white" style="padding-left: 1200px">
        <div>Xin chào ${sessionScope.fullname}</div>
        <div style="padding-left: 10px;color: white">
            <c:if test="${sessionScope.fullname==null}">
                <a href="viewLogin">Login</a>
            </c:if>
            <c:if test="${sessionScope.fullname != null}">
                <a href="logout">Logout</a>
            </c:if>
        </div>
    </div>
</nav>


<div class="container mt-3">
    <form action="loadVideo" method="GET">
        <div class="row">
            <div class="col-9">
                <input class="form-control" placeholder="Enter keyword to search" name="keyword"
                       value="${param.keyword}">
            </div>
            <div class="col-3">
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </div>
    </form>


    <ul class="list-unstyled row">
        <c:forEach items="${list}" var="vd">
            <li class="col-4">
                <a href="detail?id=${vd.id}" class="text-decoration-none">
                    <div class="card">
                        <iframe width="415" height="300" src="${vd.poster}" title="YouTube video player" frameborder="0"
                                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                                allowfullscreen></iframe>
                        <div class="card-body">
                            <h5 class="card-title">${vd.title}</h5>
                        </div>
                    </div>
                </a>
            </li>
        </c:forEach>
    </ul>
</div>
<c:if test="${list.size() == 0}">
    <div class="mt-2" style="padding-left: 560px">
        <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-search"
             viewBox="0 0 16 16">
            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
        </svg>
    </div>
    <div class="mt-2" style="padding-left: 500px">Không tìm thấy kết quả</div>
</c:if>