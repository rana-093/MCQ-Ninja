<%--
  Created by IntelliJ IDEA.
  User: masud.rana
  Date: 4/6/21
  Time: 5:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="net.therap.util.Helper" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title><spring:message code="title.examList"/></title>
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
                        <a class="nav-link" href="<c:url value="userList"/>"><spring:message code="header.user"/></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <spring:message code="header.question"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown1">
                            <a class="dropdown-item" href="<c:url value="question"/>"><spring:message
                                    code="prompt.question"/> </a>
                            <a class="dropdown-item" href="<c:url value="questionList"/>"><spring:message
                                    code="prompt.questiontList"/></a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <spring:message code="header.subject"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
                            <a class="dropdown-item" href="<c:url value="subject"/>"><spring:message
                                    code="prompt.subject"/></a>
                            <a class="dropdown-item" href="<c:url value="subjectList"/>"><spring:message
                                    code="prompt.subjectList"/></a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">

                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <spring:message code="header.topic"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown3">
                            <a class="dropdown-item" href="<c:url value="topic"/>"><spring:message
                                    code="prompt.topic"/></a>
                            <a class="dropdown-item" href="<c:url value="topicList"/>"><spring:message
                                    code="prompt.topicList"/></a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/logout"><spring:message code="header.logout"/></a>
                    </li>
                </ul>
            </div>
        </nav>
    </c:when>
    <c:otherwise>
        <nav class="navbar navbar-expand-lg navbar-light bg-warning">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav2">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="<c:url value="examList"/>"><spring:message code="header.home"/> <span
                                class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/userExamList"><spring:message code="header.pastExam"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/logout"><spring:message code="header.logout"/></a>
                    </li>
                </ul>
            </div>
        </nav>
    </c:otherwise>
</c:choose>


<div class="Wrapper">
    <div class="container">
        <c:choose>
            <c:when test='<%=session.getAttribute("role") == Helper.Role.ADMIN%>'>
                <a href="/exam">
                    <button type="button" class="btn btn-primary">
                        <spring:message code="exam.setExam"/>
                    </button>
                </a>
            </c:when>
        </c:choose>

        <p style="color: darkblue; font-size: larger"><spring:message code="exam.runningExam"/></p>
        <div class="card">
            <div class="card-body">
                <form:form action="/examTopic" method="post" modelAttribute="exam">
                    <c:out value="${exam.topic.name}"/>

                    <form:select multiple="true" path="questions" items="${questionList}"
                                 itemLabel="content" itemValue="id" cssStyle="width: 100%"/>
                    <button type="submit" class="btn btn-primary">GO!</button>
                </form:form>
            </div>
        </div>
    </div>
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

