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
<html>

<div class="Wrapper">
    <div class="container">

        <p style="color: darkblue; font-size: larger">User Exam History</p>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Exam Name</th>
                <th scope="col">Exam Topic</th>
                <th scope="col">Score</th>
                <th scope="col"><spring:message code="button.action"/></th>
            </tr>
            </thead>
            <c:forEach var="result" items="${resultList}">
                <tbody>
                <tr>
                    <td><c:out value="${result.exam.name}"/></td>
                    <td><c:out value="${result.exam.topic.name}"/></td>
                    <td><c:out value="${result.score}"/></td>
                    <td>
                        <c:url value="/showDetails" var="examUrl">
                            <c:param name="id" value="${result.exam.id}"/>
                        </c:url>
                        <c:if test='<%=session.getAttribute("role") == Helper.Role.STUDENT%>'>
                            <a href="${examUrl}">
                                <button type="button" class="btn btn-success">
                                    Show Details
                                </button>
                            </a>
                        </c:if>
                    </td>
                </tr>
                </tbody>
            </c:forEach>
        </table>

    </div>

    <script>
        let isAlert =
        <c:out value="${registered}"/>
        if (isAlert) {
            alert("Registered Successfully!!");
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
</html>

