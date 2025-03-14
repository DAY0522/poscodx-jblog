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
                <li class="selected">카테고리</li>
                <li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/write">글작성</a></li>
            </ul>
            <table class="admin-cat">
                <tr>
                    <th>번호</th>
                    <th>카테고리명</th>
                    <th>포스트 수</th>
                    <th>설명</th>
                    <th>삭제</th>
                </tr>
                <c:set var="categorySize" value="${fn:length(category)}"/>
                <c:forEach items="${category}" var="category" varStatus="status">
                    <tr>
                        <th>${categorySize - status.index}</th>
                        <th>${category.name}</th>
                        <th>${category.count}</th>
                        <th>${category.description}</th>
                        <td>
                            <c:if test="${category.count == 0}">
                                <a href="${pageContext.request.contextPath}/${authUser.id}/admin/category/delete/${category.id}">
                                    <img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <h4 class="n-c">새로운 카테고리 추가</h4>
            <form action="${pageContext.request.contextPath}/${authUser.id}/admin/category/write" method="post">
                <table id="admin-cat-add">
                    <tr>
                        <td class="t">카테고리명</td>
                        <td><input type="text" name="name"></td>
                    </tr>
                    <tr>
                        <td class="t">설명</td>
                        <td><input type="text" name="description"></td>
                    </tr>
                    <tr>
                        <td class="s">&nbsp;</td>
                        <td><input type="submit" value="카테고리 추가"></td>
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
