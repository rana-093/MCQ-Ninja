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
        <table class="table">
            <thead>
            <tr>
                <th scope="col"><spring:message code="exam.header.startTime"/></th>
                <th scope="col"><spring:message code="exam.header.endTime"/></th>
                <th scope="col"><spring:message code="header.topic"/></th>
                <th scope="col"><spring:message code="button.action"/></th>
            </tr>
            </thead>
            <c:forEach var="exam" items="${runningExamList}">
                <tbody>
                <tr>
                    <td><c:out value="${exam.startTime}"/></td>
                    <td><c:out value="${exam.endTime}"/></td>
                    <td><c:out value="${exam.topic.name}"/></td>
                    <td>
                        <c:url value="/mcq" var="examUrl">
                            <c:param name="id" value="${exam.id}"/>
                        </c:url>
                        <c:if test='<%=session.getAttribute("role") == Helper.Role.STUDENT%>'>
                            <a href="${examUrl}">
                                <button type="button" class="btn btn-success">
                                    <spring:message code="exam.enterExam"/>
                                </button>
                            </a>
                        </c:if>
                    </td>
                </tr>
                </tbody>
            </c:forEach>
        </table>


        <p style="color: blue; font-size: larger"><spring:message code="exam.upcomingExam"/></p>
        <table class="table">
            <thead>
            <tr>
                <th scope="col"><spring:message code="exam.header.startTime"/></th>
                <th scope="col"><spring:message code="exam.header.endTime"/></th>
                <th scope="col"><spring:message code="header.topic"/></th>
                <th scope="col"><spring:message code="button.action"/></th>
            </tr>
            </thead>
            <c:forEach var="exam" items="${upcomingExamList}">
                <tbody>
                <tr>
                    <td><c:out value="${exam.startTime}"/></td>
                    <td><c:out value="${exam.endTime}"/></td>
                    <td><c:out value="${exam.topic.name}"/></td>
                    <td>
                        <c:url value="/deleteExam" var="deleteExamUrl">
                            <c:param name="id" value="${exam.id}"/>
                        </c:url>
                        <c:url value="/regExam" var="examUrl">
                            <c:param name="id" value="${exam.id}"/>
                        </c:url>

                        <c:choose>
                            <c:when test='<%=session.getAttribute("role") == Helper.Role.ADMIN%>'>
                                <a href="${deleteExamUrl}">
                                    <button type="button" class="btn btn-danger">
                                        <spring:message code="exam.delete"/>
                                    </button>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="${examUrl}">
                                    <button type="button" class="btn btn-success">
                                        Register
                                    </button>
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                </tbody>
            </c:forEach>
        </table>

        <%--        <c:choose>--%>
        <%--            <c:when test='<%=session.getAttribute("role") == Helper.Role.ADMIN%>'>--%>
        <p style="color: red; font-size: larger"><spring:message code="exam.pastExam"/></p>
        <table class="table">
            <thead>
            <tr>
                <th scope="col"><spring:message code="exam.header.id"/></th>
                <th scope="col"><spring:message code="exam.header.startTime"/></th>
                <th scope="col"><spring:message code="exam.header.endTime"/></th>
                <th scope="col"><spring:message code="header.topic"/></th>
                <th scope="col">Exam Name</th>
            </tr>
            </thead>
            <c:forEach var="exam" items="${pastExamList}">
                <tbody>
                <tr>
                    <th scope="row"><c:out value="${exam.id}"/></th>
                    <td><c:out value="${exam.startTime}"/></td>
                    <td><c:out value="${exam.endTime}"/></td>
                    <td><c:out value="${exam.topic.name}"/></td>
                    <td><c:out value="${exam.name}"/></td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
        <%--            </c:when>--%>
        <%--        </c:choose>--%>
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
</body>
</html>

