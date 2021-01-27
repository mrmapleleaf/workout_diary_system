<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>フォロー中</h2>
        <table>
            <tbody>
                <tr>
                    <th>ユーザー名</th>
                </tr>
                <c:forEach var="follow" items="${followList}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${follow.trainee2.username}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagenation">
            (全 ${followListCount} 件)<br />
            <c:forEach var="i" begin="1" end="${((followListCount - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page }]">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/follows/followIndex?page=${i}'/>"><c:out value="${i}"/></a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>