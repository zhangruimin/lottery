<%@ page language="java" session="true" contentType="text/html;charset=UTF-8" %>
<%@include file="common/includes.jsp" %>
<html>
<head>
    <title>用户登录</title>
    <%@include file="common/staticRef.jsp" %>
</head>
<body>
<div class="container-outer">
    <div class="navbar">
        <span class="logo logo-login"></span>
    </div>
    <div class="login-body">
        <span class="login-background"></span>

        <div class="login-form-outer">
            <div class="login-label">登录</div>
            <div class="login-form">
                <form method="post" action="/lottery/login">
                    <dl>
                        <dd><input type="text" name="userName" placeholder="用户名：" size="25"></dd>
                        <dd><input type="password" name="password" placeholder="密码："></dd>
                    </dl>
                    <input id="login-button" type="submit" value="登录">
                    <c:if test="${status}">
                        <span id="register-links">
                                <a href="/lottery/register">注册</a>
                         </span>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
    <%@include file="common/foot.jsp" %>
</div>
</body>
</html>