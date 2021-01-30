<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
    <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}" />
            </div>
        </c:if>
    <h2>タイムライン</h2>
    <table>
        <tbody>
            <tr>
                <th>氏名</th>
                <th>日付</th>
                <th>部位</th>
                <th>操作</th>
            </tr>
            <c:forEach var="followreport" items="${followReportList}" varStatus="status">
                <tr class="row${status.count % 2}">
                    <c:choose>
                        <c:when test="${followreport.trainee.id == sessionScope.login_trainee.id}">
                        <td><a href="<c:url value='/mypage'/>"><c:out value='${followreport.trainee.username}' /></a></td>
                        </c:when>
                        <c:otherwise>
                        <td><a href="<c:url value='/trainees/show?id=${followreport.trainee.id}'/>"><c:out value='${followreport.trainee.username}' /></a></td>
                        </c:otherwise>
                    </c:choose>
                    <td><fmt:formatDate value="${followreport.report_date}" pattern="yyyy/MM/dd"/></td>
                    <td><c:out value="${followreport.body_part }" /></td>
                    <td><a href="<c:url value='/workoutreports/show?id=${followreport.id}' />">詳細を見る</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div id="pagenation">
        (全 ${followReportListCount } 件)<br />
        <c:forEach var="i" begin="1" end="${(((followReportListCount - 1)) / 15 ) + 1}" step="1">
            <c:choose>
                <c:when test="${i == page}">
                    <c:out value="${page}" />&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/timeline?page=${i}' />"><c:out value="${i}" /></a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>

    </c:param>
</c:import>