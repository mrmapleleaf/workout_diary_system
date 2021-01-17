<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${flush != null}">
    <div id="flush_error">
        <c:forEach var="error" items="${error}" varStatus="status">
            ・<c:out value="${error}" />
        </c:forEach>
    </div>
</c:if>

    <label for="report_date">日付</label><br />
    <input type="date" name="report_date" value="<fmt:formatDate value='${workoutreport.report_date}' pattern="yyyy-MM-dd"/>">
    <br /><br />

    <label for="body_part">部位</label><br />
    <select name="body_part">
        <option value="腕">腕</option>
        <option value="肩">肩</option>
        <option value="胸">胸</option>
        <option value="背中">背中</option>
        <option value="腹">腹</option>
        <option value="脚">脚</option>
    </select>
    <br /><br />

    <label for="menu">メニュー</label><br />
    <input type="text" name="menu" value="${workoutreport.menu}" />
    <br /><br />

    <label for="rep">レップ数</label><br />
    <input type="text" name="rep" value="${workoutreport.rep}" />
    <br /><br />

    <label for="sets">セット数</label><br />
    <input type="text" name="sets" value="${workoutreport.sets}" />
    <br /><br />

    <label for="intervals">インターバル</label><br />
    <input type="text" name="intervals" value="${workoutreport.intervals }" />秒
    <br /><br />

    <label for="review">感想</label><br />
    <textarea name="review" rows="5" cols="50">${workoutreport.review}</textarea>
    <br /><br />

    <input type="hidden" name="_token" value="${_token }" />
    <button type="submit">投稿</button>