<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush}">
            <div id="flush_success">
                <c:out value="${flush}" />
            </div>
        </c:if>
        <h2>マイページ</h2>
        <table id="post_num">
            <tbody>
                <tr>
                    <th>投稿</th>
                    <td><c:out value="${myWorkoutReportsCount}"/></td>
                    <th>フォロー中</th>
                    <td><a href="<c:url value='/follows/followIndex' />"><c:out value="${myAllFollowCount}" /></a></td>
                    <th>フォロワー</th>
                    <td><a href="<c:url value='/follows/followedIndex' />"><c:out value="${myAllFollowerCount }"/></a></td>
                </tr>
            </tbody>
        </table>
        <br />

        <h3>投稿一覧</h3><br />
        <table id="report_list">
            <tbody>
                <tr>
                    <th>日付</th>
                    <th>部位</th>
                    <th>操作</th>
                </tr>
                    <c:forEach var="myWorkoutReport" items="${myWorkoutReports}" varStatus="status" >
                        <tr class="row${status.count % 2 }">
                            <td class="report_date"><fmt:formatDate value="${myWorkoutReport.report_date }"/></td>
                            <td class="report_body_part"><c:out value="${myWorkoutReport.body_part }" /></td>
                            <td class="report_action"><a href="<c:url value='/workoutreports/show?id=${myWorkoutReport.id }'/>">詳細</a></td>
                        </tr>
                    </c:forEach>
            </tbody>
        </table>

        <div id="pagenation">
            (全 ${myWorkoutReportsCount} 件)<br>
            <c:forEach var="i" begin="1" end="${((myWorkoutReportsCount - 1) / 15) + 1 }" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/mypage?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>