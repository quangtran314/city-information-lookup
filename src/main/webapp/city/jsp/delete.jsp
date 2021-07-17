<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/14/2021
  Time: 9:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/city/css/list.css"/>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>
            Delete City
        </h1>
    </div>

    <div>
        <p>Are you sure you want to delete ${city.name}</p>
    </div>
    <form method="post" action="/delete?id=${city.id}">
    <div class="footer">
        <button type="submit" class="btn btn-warning">Delete</button>
        <a class="btn btn-info" href="/">Exit</a>
    </div>
    </form>
</div>
</body>
</html>
