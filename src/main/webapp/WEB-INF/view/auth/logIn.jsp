<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>
        <spring:message code="title.userLogInForm"/>
    </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/css/authStyle-1.0.0.css" %>
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-warning">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="#"><spring:message code="header.login"/> </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="register"><spring:message code="header.register"/> </a>
            </li>
        </ul>
    </div>
</nav>
<div class="Wrapper">
    <div class="container">
        <h1><spring:message code="title.userLogInForm"/></h1>
        <div class="card">
            <div class="card-body">
                <form:form action="/login" method="post" modelAttribute="user">
                    <form:hidden path="id"/>
                    <form:hidden path="name"/>
                    <div class="form-group row">
                        <form:label path="id" class="col-sm-2 col-form-label">
                            <spring:message code="column.userEmail"/>
                        </form:label>
                        <div class="col-sm-7">
                            <form:input cssClass="form-control" path="email"
                                        placeholder="Enter email"/>
                            <form:errors path="email" cssStyle="color: #ff0000;"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <form:label path="password" class="col-sm-2 col-form-label">
                            <spring:message code="user.password"/>
                        </form:label>
                        <div class="col-sm-7">
                            <form:input type="password" cssClass="form-control" path="password"
                                        placeholder="Enter password"/>
                            <form:errors path="password" cssStyle="color: #ff0000;"/>
                        </div>
                    </div>
                    <div>
                        <form:errors path="active" cssStyle="color: #ff0000;"/>
                    </div>
                    <button type="submit" class="btn btn-primary"><spring:message code="button.logIn"/></button>
                </form:form>
            </div>
        </div>
    </div>
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
