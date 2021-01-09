<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null }">
    <div id="flush_error">
        入力にエラーがあります<br />
        <c:forEach var="error" items="${errors}" varStatus="status">
            ・<c:out value="${error}"/><br />
        </c:forEach>
    </div>
</c:if>

    <label for="username">ユーザー名</label>
    <div class ="precaution">*英、数が使えます</div><br />
    <input type="text" name="username" value="${trainee.username}" />
    <br /><br />

    <label for="password">パスワード</label>
    <div class ="precaution">*英、数が使えます</div><br />
    <input type="password" name="password" />
    <br /><br />

    <input type="hidden" name="_token" value="${_token }" />
    <button type="submit">登録</button>