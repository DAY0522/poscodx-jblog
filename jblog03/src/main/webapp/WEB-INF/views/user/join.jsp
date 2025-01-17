<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JBlog</title>
    <Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
    <script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
    <script>
        $(function () {
            var el = $("#btn-checkemail");  // 아이디 선택자 수정
            el.click(function () {
                var id = $("#id").val();
                console.log(id);
                if (id == "") {
                    return;
                }

                $.ajax({
                    url: "${pageContext.request.contextPath}/api/user/checkemail/" + id,
                    type: "get",
                    dataType: "json",
                    success: function (response) {
                        if (response.result != "success") {
                            console.error(response.message);
                            return;
                        }

                        if (response.data.exist) {
                            alert("이메일이 존재합니다. 다른 이메일을 사용해 주세요.");
                            $("#id").val(""); // 기존 내용 지우기
                            $("#id").focus(); // 기존 폼으로 마우스 이동(키보드 바로 입력할 수 있도록)
                            return;
                        }

                        $("#img-checkemail").show();
                        $("#btn-checkemail").hide();
                    },
                    error: function (xhr, status, err) { // xhr: 통신하는 객체
                        console.error(err);
                    }
                });
            });
        });
    </script>
</head>
<body>
<div class="center-content">
    <h1 class="logo">JBlog</h1>
    <jsp:include page="/WEB-INF/views/includes/header.jsp"/>
    <form:form
            modelAttribute="user"
            class="join-form"
            id="join-form"
            method="post"
            action="${pageContext.request.contextPath}/user/join">
        <label class="block-label" for="name">이름</label>
        <form:input path="name" id="name"/>
        <br>
        <form:errors path="name" cssClass="error-message"/>

        <label class="block-label" for="id">아이디</label>
        <form:input path="id" id="id"/>
        <input id="btn-checkemail" type="button" value="id 중복체크">
        <img id="img-checkemail" style="display: none;"
             src="${pageContext.request.contextPath}/assets/images/check.png">
        <br>
        <form:errors path="id" cssClass="error-message"/>

        <label class="block-label" for="password">패스워드</label>
        <form:input path="password" id="password"/>
        <br>
        <form:errors path="password" cssClass="error-message"/>

        <fieldset>
            <legend>약관동의</legend>
            <input id="agree-prov" type="checkbox" name="agreeProv" value="y">
            <label class="l-float">서비스 약관에 동의합니다.</label>
        </fieldset>

        <input type="submit" value="가입하기">

    </form:form>
</div>
</body>
</html>
