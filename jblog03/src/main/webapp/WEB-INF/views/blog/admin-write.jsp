<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JBlog</title>
    <Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
<div id="container">
    <div id="header">
        <h1>${blog.title}</h1>
        <jsp:include page="/WEB-INF/views/includes/blog-admin-header.jsp"/>
    </div>
    <div id="wrapper">
        <div id="content" class="full-screen">
            <ul class="admin-menu">
                <li><a href="${pageContext.request.contextPath}/${authUser.id}/admin">기본설정</a></li>
                <li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
                <li class="selected">글작성</li>
            </ul>
            <form action="${pageContext.request.contextPath}/${authUser.id}/admin/write" method="post">
                <table class="admin-cat-write">
                    <tr>
                        <td class="t">제목</td>
                        <td>
                            <input type="text" size="60" name="title">
                            <select name="categoryId">
                                <c:forEach items="${category}" var="category">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="t">내용</td>
                        <td><textarea name="contents"></textarea></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td class="s"><input type="submit" value="포스트하기"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div id="footer">
        <p>
            <strong>${blog.title}</strong> is powered by JBlog (c)2016
        </p>
    </div>
</div>
</body>
</html>
