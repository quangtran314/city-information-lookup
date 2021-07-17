<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>city Management Application</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/city/css/list.css"/>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>
            Edit ${city.name} city
        </h1>
    </div>

    <div>
        <form method="post" action="/edit?id=${city.id}">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Name</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="name"
                           id="name" value="<c:out value='${city.name}' />" >
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
                    <input type="number" class="form-control" name="area"
                           id="area" value="<c:out value='${city.area}' />">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Population</label>
                <div class="col-sm-10">
                    <input type="number" class="form-control" name="population"
                           id="population" value="<c:out value='${city.population}' />">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">GDP</label>
                <div class="col-sm-10">
                    <input type="number" class="form-control" name="GDP"
                           id="GDP" value="<c:out value='${city.GDP}' />">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Description</label>
                <div class="col-sm-10">
                    <textarea name="description" placeholder="Write description here"><c:out value='${city.description}' /></textarea>
                </div>
            </div>

            <div class="form-group text-right">
                <button type="submit" class="btn btn-primary">Update</button>
                <a class="btn btn-warning" href="/">Exit</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>