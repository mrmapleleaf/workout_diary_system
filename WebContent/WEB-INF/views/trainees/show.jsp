<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
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

                <p><a href="<c:url value='/trainees/edit?id=${trainee.id}' />">トレーニー情報を編集</a></p>
            </c:when>

         <c:otherwise>
            <h2>お探しのデータは見つかりませんでした。</h2>
         </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/trainees/index' />">トレーニー一覧に戻る</a></p>

    </c:param>
</c:import>