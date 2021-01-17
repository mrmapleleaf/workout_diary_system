<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>筋トレポート</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="header_menu">
                    <h1>筋トレポート</h1>&nbsp;&nbsp;&nbsp;
                    <c:if test="${sessionScope.login_trainee != null}" >
                        <a href="<c:url value='/trainees/index' />">トレーニー一覧</a>&nbsp;
                        <a href="<c:url value='/workoutreports/index' />">レポート一覧</a>&nbsp;
                        <a href="<c:url value='/workoutreports/new' />">レポート投稿</a>
                    </c:if>
                </div>
                <div id="trainee_name">
                    <c:if test="${sessionScope.login_trainee != null}" >
                        <c:out value="${sessionScope.login_trainee.username }" />さん
                        <a href="<c:url value='/logout' />">ログアウト</a>
                    </c:if>
                </div>
            </div>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                Prod by Mr.Mapleleaf
            </div>
        </div>
    </body>
</html>