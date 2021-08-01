<%@ page import="net.therap.model.Question" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: masud.rana
  Date: 4/6/21
  Time: 5:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="title.mcq"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <style>
        <%@include file="/WEB-INF/css/authStyle-1.0.0.css" %>
    </style>
</head>
<body class="Wrapper">
<p align="center"><span style="color: red; font-weight:bold; font-size: xx-large" id="counter"></span></p>

<div class="container">
    <h2 style="color: blue">Questions of the exam are as follows: </h2>
    <div class="card" style="margin-top: 50px;">
        <div class="card-body">
            <a href="/openPDF/${mcqCommand.exam.instructionsFilePath}"
               style="margin-bottom: 30px">
                <button class="btn btn-primary">Instructions File for the exam
                </button>
            </a>
            <form:form action="/mcq" method="post" modelAttribute="mcqCommand" id="mcqForm" name="mcqForm">
                <h3>Exam Name: <c:out value="${mcqCommand.exam.name}"/></h3>
                <c:forEach items="${mcqCommand.questionCommandList}" var="questionCommand" varStatus="status">
                    <form:hidden path="questionCommandList[${status.index}].question.id"/>
                    <form:input path="questionCommandList[${status.index}].question.content" readonly="true"
                                cssStyle="width: 100%; margin-top: 15px"/>
                    <form:radiobutton path="questionCommandList[${status.index}].choosenOp"
                                      value="${questionCommand.question.optionList.get(0).content}"/><c:out
                        value="${questionCommand.question.optionList.get(0).content}"/> <br>
                    <form:radiobutton path="questionCommandList[${status.index}].choosenOp"
                                      value="${questionCommand.question.optionList.get(1).content}"/> <c:out
                        value="${questionCommand.question.optionList.get(1).content}"/><br>
                    <form:radiobutton path="questionCommandList[${status.index}].choosenOp"
                                      value="${questionCommand.question.optionList.get(2).content}"/><c:out
                        value="${questionCommand.question.optionList.get(2).content}"/> <br>
                    <form:radiobutton path="questionCommandList[${status.index}].choosenOp"
                                      value="${questionCommand.question.optionList.get(3).content}"/><c:out
                        value="${questionCommand.question.optionList.get(3).content}"/> <br>

                </c:forEach>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form:form>
        </div>
    </div>
</div>

<script>

    let day1 = new Date();
    let day2 = new Date(<c:out value="${date}"/>);
    let duration = Math.floor(day2 - day1) / 1000;
    const counter = document.getElementById('counter');
    let x = setInterval(updateCountDown, 1000);

    function updateCountDown() {
        seconds = Math.floor((duration) % 60);
        minutes = Math.floor((duration / (60)) % 60);
        hours = Math.floor((duration / (60 * 60)) % 24);

        hours = (hours < 10) ? "0" + hours : hours;
        minutes = (minutes < 10) ? "0" + minutes : minutes;
        seconds = (seconds < 10) ? "0" + seconds : seconds;
        counter.innerHTML = hours + ":" + minutes + ":" + seconds;
        duration--;
        if (duration <= 0) {
            clearInterval(x);
            counter.innerHTML = "Time is up. Exam Ended!";
            let Y = document.getElementById("mcqForm");
            console.log("Y: " + Y);
            //  document.mcqForm.submit();
            //  document.getElementById("mcqForm").submit();
        }
    }
</script>
</body>
</html>
