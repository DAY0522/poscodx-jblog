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
        <jsp:include page="/WEB-INF/views/includes/blog-header.jsp"/>
    </div>
    <div id="wrapper">
        <div id="content">
            <div class="blog-content">
                <c:choose>
                    <c:when test="${map.isEmptyPost}">
                        <h4>작성된 게시글이 없습니다.</h4>
                        <p>
                            다른 카테고리를 클릭해주세요.
                        </p>
                    </c:when>
                    <c:otherwise>
                        <h4>${map.post.title}</h4>
                        <p>
                                ${map.post.contents}
                        </p>
                    </c:otherwise>
                </c:choose>
            </div>
            <ul class="blog-list">
                <c:forEach items="${map.postList}" var="post">
                    <li><a href="${pageContext.request.contextPath}/${blogId}/${post.categoryId}/${post.id}">${post.title}</a> <span>${post.regDate}</span></li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div id="extra">
        <div class="blog-logo">
            <img src="${pageContext.request.contextPath }${blog.profile}" style="width:170px">
        </div>
    </div>

    <div id="navigation">
        <h2>카테고리</h2>
        <ul>
            <c:forEach items="${map.category}" var="category">
                <li><a href="${pageContext.request.contextPath}/${blogId}/${category.id}">${category.name}</a></li>
            </c:forEach>
        </ul>
    </div>

    <div id="footer">
        <p>
            <strong>${blog.title}</strong> is powered by JBlog (c)2016
        </p>
    </div>
</div>
</body>
</html>
