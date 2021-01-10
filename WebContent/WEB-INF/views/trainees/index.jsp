<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">

    <c:param name="content">
    <c:if test="${flush != null}" >
        <div id="flush_success">
            <c:out value="${flush}" />
        </div>
    </c:if>
        <h2>トレーニー一覧</h2>
        <table id ="trainee_list">
            <tbody>
                <tr>
                    <th>ユーザー名</th>
                </tr>
                <c:forEach var="trainee" items="${trainees}" varStatus="status">
                    <tr class="row${status.count % 2 }">
                        <td><a href="<c:url value='/trainees/show?id=${trainee.id}' />"><c:out value="${trainee.username }" /></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagenation">
            (全 ${trainees_count} 件)<br />
            <c:forEach var="i" begin="1" end="${((trainees_count - 1) / 15 ) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/trainees/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>