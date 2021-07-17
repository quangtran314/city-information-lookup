<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/city/css/list.css"/>
</head>
<body>
<div class="main">
    <c:if test="${errors != null}">
        <div class="alert alert-warning alert-dismissible fade show" role="alert">
            ${errors}
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
        </div>
    </c:if>
    <div class="header">
        <h1>
            Add new city
        </h1>
    </div>

    <div>
        <form method="post" action="/create">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Name</label>
                <div class="col-sm-10">
                    <c:choose>
                        <c:when test="${filled}">
                            <input type="text" class="form-control" name="name"
                                   id="name" value="${city.name}">
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" name="name" id="name">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Nation</label>
                <div class="col-sm-10">
                    <select name="nation" class="form-control" >
                        <c:forEach var="nation" items="${nations}">
                            <c:choose>
                                <c:when test="${city.nation == nation.nationName}">
                                    <option value="${nation.nationName}" selected>${nation.nationName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${nation.nationName}">${nation.nationName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Area</label>
                <div class="col-sm-10">
                    <c:choose>
                        <c:when test="${filled}">
                            <input type="number" class="form-control" name="area"
                                   id="area" value="${city.area}">
                        </c:when>
                        <c:otherwise>
                            <input type="number" class="form-control" name="area" id="area">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Population</label>
                <div class="col-sm-10">
                    <c:choose>
                        <c:when test="${filled}">
                            <input type="number" class="form-control" name="population"
                                   id="population" value="${city.population}">
                        </c:when>
                        <c:otherwise>
                            <input type="number" class="form-control" name="population" id="population">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">GDP</label>
                <div class="col-sm-10">
                    <c:choose>
                        <c:when test="${filled}">
                            <input type="number" class="form-control" name="GDP"
                                   id="GDP" value="${city.GDP}">
                        </c:when>
                        <c:otherwise>
                            <input type="number" class="form-control" name="GDP" id="GDP">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Description</label>
                <div class="col-sm-10">
                    <c:choose>
                        <c:when test="${filled}">
                            <textarea name="description" placeholder="Write description here">${city.description}</textarea>
                        </c:when>
                        <c:otherwise>
                            <textarea name="description" placeholder="Write description here"></textarea>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="form-group text-right">
                <button type="submit" class="btn btn-primary">Add</button>
                <a class="btn btn-warning" href="/">Exit</a>
            </div>
        </form>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</body>
</html>