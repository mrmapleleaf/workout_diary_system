<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${errors != null }">
            <div id="flush_error">
            ユーザー名かパスワードにエラーがあります<br />
            <c:forEach var="error" items="${error}">
                ・<c:out value="${error}" />
            </c:forEach>
            </div>
        </c:if>
        <h2>筋トレポートへようこそ！</h2>
        <form method="POST" action="<c:url value='/trainees/create' />">
            <c:import url="_form.jsp" />
        </form>

        <p>トップページへ戻る</p>
    </c:param>
</c:import>