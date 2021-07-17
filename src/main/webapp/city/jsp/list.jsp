<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>HelloWorld</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/city/css/list.css"/>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>
            List Cities
        </h1>
        <div>
            <c:if test="${redirect == 'search'}">
                <a class="btn btn-success" href="/">All cities</a>
            </c:if>
            <a class="btn btn-success" href="/create">Add new city</a>
        </div>
    </div>

    <form class="form-inline d-flex justify-content-center md-form form-sm active-pink active-pink-2 mt-2"
          method="post" action="/search">
        <i class="fas fa-search" aria-hidden="true"></i>
        <input class="form-control form-control-sm ml-3 w-75" type="text" placeholder="Search" name="nation"
               aria-label="Search">
    </form>

    <div align="center">
        <table class="table table-bordered table-striped">
            <thead class="grey lighten-2">
            <tr>
                <th scope="col">
                    <div class="sort" style="position: relative; top: -5px">
                        <p>#</p>
                    </div>
                </th>
                <th scope="col">
                    <div class="sort">
                        <p>City</p>
                        <c:choose>
                            <c:when test="${sort}">
                                <p><a class="btn btn-sm btn-info" href="/?sort=false">unsort</a></p>
                            </c:when>
                            <c:otherwise>
                                <p><a class="btn btn-sm btn-info" href="/?sort=true">sort</a></p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </th>
                <th scope="col">
                    <div class="sort">
                        <p>Nation</p>
<%--                        <c:choose>--%>
<%--                            <c:when test="${sort}">--%>
<%--                                <p><a class="btn btn-sm btn-info" href="/?sort=${sort}">unsort</a></p>--%>
<%--                            </c:when>--%>
<%--                            <c:otherwise>--%>
<%--                                <p><a class="btn btn-sm btn-info" href="/?sort=${sort}">sort</a></p>--%>
<%--                            </c:otherwise>--%>
<%--                        </c:choose>--%>
                    </div>
                </th>
                <th scope="col">
                    <div class="sort" style="position: relative; top: -5px">
                        <p>Edit</p>
                    </div>
                </th>
                <th scope="col">
                    <div class="sort" style="position: relative; top: -5px">
                        <p>Delete</p>
                    </div>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="city" items="${cities}">
                <tr>
                    <td><c:out value="${city.id}"/></td>
                    <td><a href="/city?id=${city.id}"><c:out value="${city.name}"/></a></td>
                    <td><c:out value="${city.nation}"/></td>
                    <td><a href="/edit?id=${city.id}">Edit</a></td>
                    <td><a href="/delete?id=${city.id}">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <nav aria-label="Navigation for countries">
            <ul class="pagination">
                <c:if test="${currentPage != 1}">
                    <c:choose>
                        <c:when test="${sort}">
                            <li class="page-item"><a class="page-link" href="/?page=${currentPage-1}&sort=true">Previous</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="/?page=${currentPage-1}&sort=false">Previous</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:if>

                <c:set  var="start" scope="session" value="1"></c:set>
                <c:choose>
                    <c:when test="${noOfPages <= 3}">
                        <c:set  var="end" scope="session" value="${noOfPages}"></c:set>
                    </c:when>
                    <c:otherwise>
                        <c:set  var="end" scope="session" value="${3}"></c:set>
                    </c:otherwise>
                </c:choose>
                <c:if test="${currentPage > 3}">
                    <c:set  var="start" scope="session" value="${currentPage-2}"></c:set>
                    <c:set  var="end" scope="session" value="${currentPage}"></c:set>
                </c:if>
                <c:forEach begin="${start}" end="${end}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${sort}">
                                    <li class="page-item"><a class="page-link" href="/?page=${i}&sort=true">${i}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="/?page=${i}&sort=false">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <c:choose>
                        <c:when test="${sort}">
                            <li class="page-item"><a class="page-link" href="/?page=${currentPage+1}&sort=true">Next</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="/?page=${currentPage+1}&sort=false">Next</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>