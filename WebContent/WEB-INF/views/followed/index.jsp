<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>フォロワー</h2>
        <table>
            <tbody>
                <tr>
                    <th>ユーザー名</th>
                </tr>
                <c:forEach var="follower" items="${followerList}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${follower.trainee1.username}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagenation">
            (全 ${followerListCount} 件)<br />
            <c:forEach var="i" begin="1" end="${((followerListCount - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page }]">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/follows/followedIndex?page=${i}'/>"><c:out value="${i}"/></a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>