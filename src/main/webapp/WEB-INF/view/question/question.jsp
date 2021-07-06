<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><spring:message code="header.question"/></title>
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
                <a class="nav-link" href="<c:url value="userList"/>"><spring:message code="header.user"/> </a>
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
    <div align="center">
        <form:form action="question" method="post" modelAttribute="question">

            <spring:message code="header.topic"/><span style="color: red"><spring:message
                code="prompt.required"/></span>
            <form:select path="topic" items="${topicList}" itemLabel="name" itemValue="id"/>
            <br><br>

            <form:label path="content"><spring:message code="header.question"/></form:label><span
                style="color: red"><spring:message code="prompt.required"/></span>
            <form:textarea path="content" cols="50" rows="10" cssClass="form-control"/>
            <form:errors path="content" cssClass="error" cssStyle="color: red"/><br><br>

            <c:forEach items="${question.optionList}" var="question" varStatus="status">
                <form:hidden path="optionList[${status.index}].id"/>
                <form:label path="optionList[${status.index}].content">
                    Option<c:out value="${status.index}"/>
                </form:label>
                <form:input path="optionList[${status.index}].content"
                            cssStyle="width: 100%; margin-top: 15px"/>
                <form:errors path="optionList[${status.index}].content" cssClass="error" cssStyle="color: red"/><br><br>
                <form:hidden path="optionList[${status.index}].correct"/>
            </c:forEach>

            <form:label path="correctOption"><spring:message code="question.correctOption"/></form:label><span
                style="color: red"><spring:message code="prompt.required"/></span>
            <form:select path="correctOption">
                <form:option value="0" label="0"/>
                <form:option value="1" label="1"/>
                <form:option value="2" label="2"/>
                <form:option value="3" label="3"/>
            </form:select><br><br>

            <form:label path="explanation"><spring:message code="question.explanation"/></form:label>
            <form:textarea path="explanation" cols="50" rows="10" cssClass="form-control"/>
            <form:errors path="explanation" cssClass="error" cssStyle="color: red"/><br><br>

            <form:hidden path="used" value="false"/>

            <button type="submit" class="btn btn-primary"><spring:message code="action.add"/></button>
        </form:form>
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
