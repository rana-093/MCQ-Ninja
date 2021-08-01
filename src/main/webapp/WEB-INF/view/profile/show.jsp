<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>
        <spring:message code="title.userRegisterForm"/>
    </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/css/authStyle-1.0.0.css" %>
    </style>
</head>
<body class="Wrapper">
<div class="container">
    <h1>User Info</h1>
    <div class="card">
        <div class="card-body">

            <div class="form-group row">
                <div class="col-sm-4">
                    User Name:
                </div>
                <div class="col-sm-7">
                    ${sessionScope.student.name}
                </div>
            </div>

            <div class="form-group row">
                <div class="col-sm-4">
                    User Email:
                </div>
                <div class="col-sm-7">
                    ${sessionScope.student.email}
                </div>
            </div>


            <div class="form-group row">
                <div class="col-sm-4">
                    Active Status:
                </div>
                <div class="col-sm-7">
                    ${sessionScope.student.active}
                </div>
            </div>

            <div class="form-group row">
                <div class="col-sm-4">
                    Contact No:
                </div>
                <div class="col-sm-7">
                    ${sessionScope.student.contactNo}
                </div>
            </div>

            <div class="form-group row">
                <div class="col-sm-4">
                    Image:
                </div>
                <div class="col-sm-7">
                    <img alt="img" src="data:image/png;base64,${sessionScope.student.base64image}"/>
                </div>
            </div>

            <a href="/editProfile">
                <button type="submit" class="btn btn-primary">Edit Profile</button>
            </a>
        </div>
    </div>

</div>
</body>
</html>
