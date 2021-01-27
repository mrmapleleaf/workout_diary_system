<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp" >
    <c:param name="content">
        <table>
            <tbody>
                <tr>
                    <th>ユーザー名</th>
                    <th>いいねした時間</th>
                </tr>
                <c:forEach var="getAllLike" items="${getAllLikes}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${getAllLike.trainee.username}" /></td>
                        <td><fmt:formatDate value="${getAllLike.created_at}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagenation">
            (全 ${getAllLikesCount} 件)<br />
            <c:forEach var="i" begin="1" end="${((getAllLikesCount - 1) / 15) + 1 }" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}"/>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/likes/index?page=${i}&&id=${wr.id}' />"><c:out value="${i}" /></a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <p><a href="<c:url value="/workoutreports/index"/>">レポート一覧に戻る</a></p>
    </c:param>
</c:import>