<%--
  Created by IntelliJ IDEA.
  User: sadia.afroz
  Date: 6/9/21
  Time: 11:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="title.result"/></title>
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
<div class="Wrapper">
    <div class="container">
        <div class="card" style="margin-top: 50px;">
            <div class="card-body">
                <c:set var="cnt" value="1" scope="page"/>
                <c:forEach items="${resultCommandList}" var="resultCommand">

                    <c:out value="${resultCommand.question.content}"/> <br>

                    <input type="radio" id="optionA${cnt}" disabled="yes">
                    <label for='"optionA${cnt}"'>"${resultCommand.question.optionList.get(0).content}"</label> <br>
                    <c:choose>
                        <c:when test="${resultCommand.answer.choosenOption == resultCommand.question.optionList.get(0).content}">
                            <c:choose>
                                <c:when test="${resultCommand.answer.correct}">
                                    <p style="color: green"> Correct ✓</p>
                                </c:when>
                                <c:otherwise>
                                    <p style="color: red"> Wrong X</p>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                    </c:choose>

                    <input type="radio" id="optionB${cnt}" disabled="yes">
                    <label for='"optionB${cnt}"'>"${resultCommand.question.optionList.get(1).content}"</label> <br>
                    <c:choose>
                        <c:when test="${resultCommand.answer.choosenOption == resultCommand.question.optionList.get(1).content}">
                            <c:choose>
                                <c:when test="${resultCommand.answer.correct}">
                                    <p style="color: green"> Correct ✓</p>
                                </c:when>
                                <c:otherwise>
                                    <p style="color: red"> Wrong X</p>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                    </c:choose>

                    <input type="radio" id="optionC${cnt}" disabled="yes">
                    <label for='"optionC${cnt}"'>"${resultCommand.question.optionList.get(2).content}"</label> <br>
                    <c:choose>
                        <c:when test="${resultCommand.answer.choosenOption == resultCommand.question.optionList.get(2).content}">
                            <c:choose>
                                <c:when test="${resultCommand.answer.correct}">
                                    <p style="color: green"> Correct ✓</p>
                                </c:when>
                                <c:otherwise>
                                    <p style="color: red"> Wrong X</p>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                    </c:choose>

                    <input type="radio" id="optionD${cnt}" disabled="yes">
                    <label for='"optionD${cnt}"'>"${resultCommand.question.optionList.get(3).content}"</label> <br>
                    <c:choose>
                        <c:when test="${resultCommand.answer.choosenOption == resultCommand.question.optionList.get(3).content}">
                            <c:choose>
                                <c:when test="${resultCommand.answer.correct}">
                                    <p style="color: green"> Correct ✓</p>
                                </c:when>
                                <c:otherwise>
                                    <p style="color: red"> Wrong X</p>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                    </c:choose>

                    <input type="text " value="<c:out
        value="${resultCommand.question.explanation}"/>" style="width: 100%" readonly="true"/><br>

                    <c:set var="cnt" value="${cnt + 1}" scope="page"/>
                </c:forEach>
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
