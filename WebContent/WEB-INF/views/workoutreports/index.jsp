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
        <h2>レポート一覧</h2>
        <table id="report_list">
            <tr>
                <th>ユーザー名</th>
                <th>日付</th>
                <th>部位</th>
                <th>操作</th>
            </tr>
            <c:forEach var="workoutreport" items="${workoutreports}" varStatus="status">
                <tr class="row${status.count % 2}">
                    <c:choose>
                        <c:when test="${workoutreport.trainee.id == sessionScope.login_trainee.id}">
                        <td class="report_name"><a href="<c:url value='/mypage'/>"><c:out value='${workoutreport.trainee.username}' /></a></td>
                        </c:when>
                        <c:otherwise>
                        <td class="report_name"><a href="<c:url value='/trainees/show?id=${workoutreport.trainee.id}'/>"><c:out value='${workoutreport.trainee.username}' /></a></td>
                        </c:otherwise>
                    </c:choose>
                    <td class="report_date"><fmt:formatDate value='${workoutreport.report_date }' pattern='yyyy-MM-dd'/></td>
                    <td class="report_body_part">${workoutreport.body_part}</td>
                    <td class="report_action"><a href="<c:url value='/workoutreports/show?id=${workoutreport.id }' />">詳細を見る</a></td>
                </tr>
            </c:forEach>
        </table>

        <div id="pagination">
            (全 ${workoutreports_count} 件)<br />
            <c:forEach var="i" begin="1" end="${((workoutreports_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${page}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/workoutreports/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>