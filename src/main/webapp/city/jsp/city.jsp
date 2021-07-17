<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${city.name}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/city/css/list.css"/>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>
            ${city.name} city
        </h1>
        <a class="btn btn-success" href="/">All cities</a>
    </div>

    <div>
        <p>City: <span>${city.name}</span></p>
        <p>Nation: <span>${city.nation}</span></p>
        <p>Area: <span>${city.area}</span></p>
        <p>Population: <span>${city.population}</span></p>
        <p>GDP: <span>${city.GDP}</span></p>
        <p>Description: </p>
        <p>${city.description}<p>
    </div>
    <div class="footer">
        <a class="btn btn-info" href="/edit?id=${city.id}">Edit</a>
        <a class="btn btn-warning" href="/delete?id=${city.id}">Delete</a>
    </div>
</div>
</body>
</html>