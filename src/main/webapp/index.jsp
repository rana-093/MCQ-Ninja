<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/css/authStyle-1.0.0.css" %>
    </style>
</head>
<body class="Wrapper">
<div class="container">
    <h1>Welcome to the MCQ NINJA</h1>
    <br/>
    <c:url value="/login" var="logIn"/>
    <c:url value="/registerStudent" var="register"/>
    <div style="margin-top: 20px">
        <a href="${logIn}">
            <button type="button" class="btn btn-primary">
                Log IN
            </button>
        </a>
    </div>
    <div style="margin-top: 20px">
        <a href="${register}">
            <button type="button" class="btn btn-primary">
                Register
            </button>
        </a>
    </div>

</div>
</body>
</html>