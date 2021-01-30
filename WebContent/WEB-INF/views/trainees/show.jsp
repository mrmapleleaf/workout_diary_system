<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}" >
        <div id="flush_success">
            <c:out value="${flush}" />
        </div>
    </c:if>
        <c:choose>
            <c:when test="${trainee != null }">
                <h2>トレーニー詳細</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>ユーザー名</th>
                            <td><c:out value="${trainee.username}"/></td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td><fmt:formatDate value="${trainee.created_at}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        </tr>
                    </tbody>
                </table>
                <c:if test="${sessionScope.login_trainee.id == trainee.id }" >
                <p><a href="<c:url value='/trainees/edit?id=${trainee.id}' />">トレーニー情報を編集</a></p>
                </c:if>
            </c:when>

         <c:otherwise>
            <h2>お探しのデータは見つかりませんでした。</h2>
         </c:otherwise>
        </c:choose>
        <c:if test="${checkFollowedAlready == 0 and sessionScope.login_trainee.id != trainee.id}">
            <p><a href="<c:url value='/follows/create?id=${trainee.id}'/>">フォロー</a></p>
        </c:if>
        <p><a href="<c:url value='/trainees/index' />">トレーニー一覧に戻る</a></p>

    </c:param>
</c:import>