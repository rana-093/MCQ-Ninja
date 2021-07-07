<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>
        <spring:message code="exam.action.setExam"/>
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
                <a class="nav-link" href="<c:url value="examList"/>"><spring:message code="header.home"/> <span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="userList"/>"><spring:message code="header.user"/></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <spring:message code="header.question"/>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown1">
                    <a class="dropdown-item" href="<c:url value="question"/>"><spring:message
                            code="prompt.question"/> </a>
                    <a class="dropdown-item" href="<c:url value="questionList"/>"><spring:message
                            code="prompt.questiontList"/> </a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <spring:message code="header.subject"/>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
                    <a class="dropdown-item" href="<c:url value="subject"/>"><spring:message
                            code="prompt.subject"/> </a>
                    <a class="dropdown-item" href="<c:url value="subjectList"/>"><spring:message
                            code="prompt.subjectList"/> </a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <spring:message code="header.topic"/>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown3">
                    <a class="dropdown-item" href="<c:url value="topic"/>"><spring:message code="prompt.topic"/> </a>
                    <a class="dropdown-item" href="<c:url value="topicList"/>"><spring:message
                            code="prompt.topicList"/> </a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout"><spring:message code="header.logout"/> </a>
            </li>
        </ul>
    </div>
</nav>
<div class="Wrapper">
    <div class="container">
        <h1><spring:message code="exam.setExam"/></h1>
        <div class="card">
            <div class="card-body">
                <form:form action="/exam" method="post" modelAttribute="exam">

                    <%--                    <div class="form-group row">--%>
                    <%--                        <form:label path="startDate" class="col-sm-2 col-form-label">--%>
                    <%--                            <spring:message code="exam.startTime"/>--%>
                    <%--                        </form:label>--%>
                    <%--                        <div class="col-sm-7">--%>
                    <%--                            <form:input type="date" path="startDate" cssClass="form-control"--%>
                    <%--                                        placeholder="Enter Start Time(mm/dd/yyyy,hh:mm,AM/PM)"/>--%>
                    <%--                            <form:errors path="startDate" cssStyle="color: #ff0000;"/>--%>
                    <%--                        </div>--%>
                    <%--                    </div>--%>

                    <div class="form-group row">
                        <form:label path="startTime" class="col-sm-2 col-form-label">
                            <spring:message code="exam.startTime"/>
                        </form:label>
                        <div class="col-sm-7">
                            <form:input type="datetime-local" path="startTime" cssClass="form-control"
                                        placeholder="Enter Start Time(mm/dd/yyyy,hh:mm,AM/PM)"/>
                            <form:errors path="startTime" cssStyle="color: #ff0000;"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <form:label path="endTime" class="col-sm-2 col-form-label">
                            <spring:message code="exam.endTime"/>
                        </form:label>
                        <div class="col-sm-7">
                            <form:input type="datetime-local" path="endTime" cssClass="form-control"
                                        placeholder="Enter End Time(mm/dd/yyyy,hh:mm,AM/PM)"/>
                            <form:errors path="endTime" cssStyle="color: #ff0000;"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <form:label path="topic" class="col-sm-2 col-form-label">
                            <spring:message code="exam.topicPrompt"/>
                        </form:label>
                        <div class="col-sm-7">
                            <form:select path="topic" items="${topicList}" itemValue="id" itemLabel="name"/>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">NEXT</button>
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