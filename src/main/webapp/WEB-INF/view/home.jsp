<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="net.therap.util.Helper" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="title.head"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/css/authStyle-1.0.0.css" %>
    </style>
</head>
<body>
<c:choose>
    <c:when test='<%=session.getAttribute("role") == Helper.Role.ADMIN%>'>
        <nav class="navbar navbar-expand-lg navbar-light bg-warning">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav1">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="#"><spring:message code="header.home"/> <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="registerAdmin"/>">Add a new Admin</a>
                    </li>
                    <li class="nav-item" style="margin-left: auto">
                        <a class="nav-link" href="<c:url value="logOut"/>">LogOut</a>
                    </li>
                </ul>
            </div>
        </nav>
    </c:when>
    <c:otherwise>
        Welcome to the dashBoard!!!
    </c:otherwise>
</c:choose>


<div class="Wrapper">
    <div class="container">
        Welcome!!
    </div>

    <script>
        let isAlert =
        <c:out value="${alert}"/>
        if (isAlert) {
            alert("Exam is not started YET!");
        }
    </script>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>

