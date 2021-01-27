<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${workoutreport != null }">
                <h2>レポート詳細</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>ユーザー名</th>
                            <td><c:out value="${workoutreport.trainee.username}" /></td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${workoutreport.report_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>部位</th>
                            <td><c:out value="${workoutreport.body_part}" /></td>
                        </tr>
                        <tr>
                            <th>トレーニングメニュー</th>
                            <td><c:out value="${workoutreport.menu}" /></td>
                        </tr>
                        <tr>
                            <th>レップ数</th>
                            <td><c:out value="${workoutreport.rep}" />&nbsp;回</td>
                        </tr>
                        <tr>
                            <th>セット数</th>
                            <td><c:out value="${workoutreport.sets}" />&nbsp;回</td>
                        </tr>
                        <tr>
                            <th>インターバル</th>
                            <td><c:out value="${workoutreport.intervals}" />&nbsp;秒</td>
                        </tr>
                        <tr>
                            <th>感想</th>
                            <td><pre><c:out value="${workoutreport.review}" /></pre></td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td><fmt:formatDate value="${workoutreport.created_at}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td><fmt:formatDate value="${workoutreport.updated_at}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        </tr>
                        <tr>
                            <th>いいね数</th>
                            <c:choose>
                                <c:when test="${likesCount > 0}">
                                    <td><a href="<c:url value='/likes/index?id=${workoutreport.id}'/>"><c:out value="${likesCount}"/></a></td>
                                </c:when>
                                <c:otherwise>
                                    <td><c:out value="${likesCount}"/></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_trainee.id == workoutreport.trainee.id }" >
                    <p><a href="<c:url value='/workoutreports/edit?id=${workoutreport.id}' />">このレポートを編集</a><p>
                </c:if>
                <c:if test="${sessionScope.login_trainee.id != workoutreport.trainee.id && checkLikedAlready == 0}">
                    <p><a href="<c:url value='/likes/create?id=${workoutreport.id}' />">いいね！</a></p>
                </c:if>
            </c:when>

            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/workoutreports/index' />">レポート一覧に戻る</a></p>
    </c:param>
</c:import>