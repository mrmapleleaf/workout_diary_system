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
        <table id="report_list">
            <tr>
                <th>ユーザー名</th>
                <th>日付</th>
                <th>部位</th>
                <th>操作</th>
            </tr>
            <c:forEach var="workoutreport" items="${workoutreports}" varStatus="status">
                <tr class="row${status.count % 2}">
                    <td class="report_name"><c:out value='${workoutreport.trainee.username}' /></td>
                    <td class="report_date"><fmt:formatDate value='${workoutreport.report_date }' pattern='yyyy-MM-dd'/></td>
                    <td class="report_body_part">${workoutreport.body_part}</td>
                    <td class="report_action"><a href="<c:url value='/workoutreports/index' />">詳細を見る</a></td>
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