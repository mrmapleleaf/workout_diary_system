<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name ="content">
    <c:if test="${flush != null}" >
        <div id="flush_success">
            <c:out value="${flush}" />
        </div>
    </c:if>
        <div class="main">
            <div class="copy-container">
                <h1>GET <br />THE BODY <br />YOU WANT</h1>
                <div class="item">
                    <img src="./css/1726105.jpg" width="100" height="100" alt="ムキムキのアイコン"/>
                </div>
                <p>筋トレポートは、筋力トレーニングの記録するサービスです。<br />あなたがその日行ったトレーニングを記録して、<br />日々のモチベーションアップに繋げ、理想の体を手に入れましょう！</p>
            </div>

            <div class="contents">
                <h3 class="content-title">出来ること</h3>
                <div class="contents-items">
                    <div class="contents-item">
                        <img alt="日記のアイコン" src="./css/icon_152840_256.png"  width="170" height="150">
                        <p>トレーニングを記録</p>
                    </div>
                    <div class="contents-item">
                        <img alt="いいねのアイコン" src="./css/icon_106370_256.png"  width="170" height="150">
                        <p>いいね機能</p>
                    </div>
                    <div class="contents-item">
                        <img alt="繋がりのアイコン" src="./css/icon_144440_256.png"  width="170" height="150">
                        <p>フォロー機能</p>
                     </div>
                </div>
            </div>

            <div class="buttons">
                <a class="button1" href="<c:url value='/trainees/new' />">新規登録</a>
                <a class="button2" href="<c:url value='/login' />">ログイン</a>
            </div>
        </div>
    </c:param>
</c:import>