<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404 Not Found</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/css/authStyle-1.0.0.css" %>
    </style>
</head>
<body class="Wrapper">
<div class="container">
    <h1 style="color: red">404 Not Found!</h1>
    <h1 style="color: red">Requested Page Not Found! or Not Enough Data to process further request!</h1>
    <c:url value="/home" var="userHome"/>

    <a href="${userHome}">
        <button type="button" class="btn btn-primary">
            <spring:message code="header.home"/>
        </button>
    </a>
</div>
</body>
</html>
