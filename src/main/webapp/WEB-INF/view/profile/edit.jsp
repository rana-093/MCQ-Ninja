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
    <h1><spring:message code="user.userEdit"/></h1>
    <div class="card">
        <div class="card-body">
            <form:form action="/editProfile" method="post" modelAttribute="student">
                <div class="form-group row">
                    <form:label path="name" class="col-sm-2 col-form-label">
                        <spring:message code="user.name"/>
                    </form:label>
                    <div class="col-sm-7">
                        <form:input cssClass="form-control" path="name"
                                    placeholder="Enter name"/>
                        <form:errors path="name" cssStyle="color: #ff0000;"/>
                    </div>
                </div>

                <div class="form-group row">
                    <form:label path="email" class="col-sm-2 col-form-label">
                        <spring:message code="user.email"/>
                    </form:label>
                    <div class="col-sm-7">
                        <form:input cssClass="form-control" path="email"
                                    placeholder="Enter email"/>
                        <form:errors path="email" cssStyle="color: #ff0000;"/>
                    </div>
                </div>
                <%--                <form:hidden path="password"/>--%>

                <%--                <div class="form-group row">--%>
                <%--                    <form:label path="active" class="col-sm-2 col-form-label">--%>
                <%--                        <spring:message code="user.active"/>--%>
                <%--                    </form:label>--%>
                <%--                    <div class="col-sm-7">--%>
                <%--                        <form:radiobutton path="active" value="true"/> <spring:message code="user.setActive"/>--%>
                <%--                        <form:radiobutton path="active" value="false"/> <spring:message code="user.setInactive"/>--%>
                <%--                    </div>--%>
                <%--                </div>--%>

                <div class="form-group row">
                    <form:label path="contactNo" class="col-sm-2 col-form-label">
                        Contact No:
                    </form:label>
                    <div class="col-sm-7">
                        <form:input cssClass="form-control" path="contactNo"
                                    placeholder="Enter contactNo"/>
                        <form:errors path="contactNo" cssStyle="color: #ff0000;"/>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary"><spring:message code="user.save"/></button>
            </form:form>
        </div>
    </div>

</div>
</body>
</html>
