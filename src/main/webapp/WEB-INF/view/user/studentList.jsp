<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>

<div class="Wrapper">
    <div class="container">
        <table class="table table-striped table-bordered table-hover table-sm">
            <thead class="thead-dark">
            <tr>
                <th scope="col"><spring:message code="user.name"/></th>
                <th scope="col"><spring:message code="user.email"/></th>
                <th scope="col"><spring:message code="user.isActive"/></th>
                <th scope="col"><spring:message code="user.editAction"/></th>
            </tr>
            </thead>
            <c:forEach var="user" items="${studentList}">
                <tbody>
                <tr>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.active}"/></td>
                    <td>
                        <c:url value="/user" var="editUserUrl">
                            <c:param name="id" value="${user.id}"/>
                        </c:url>

                        <a href="${editUserUrl}">
                            <button type="button" class="btn btn-primary">
                                <spring:message code="action.edit"/>
                            </button>
                        </a>
                    </td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>

    <script>
        let isAlert =
        <c:out value="${alert}"/>
        if (isAlert) {
            alert("Exam is not started YET!");
        }
    </script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</div>
</html>

