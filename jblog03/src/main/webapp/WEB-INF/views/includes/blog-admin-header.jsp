<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<ul>
    <c:choose>
        <c:when test="${empty authUser}">
            <li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
        </c:otherwise>
    </c:choose>
    <li><a href="${pageContext.request.contextPath}/${authUser.id}">내블로그</a></li>
</ul>
