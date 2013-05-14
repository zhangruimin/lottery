<%@ page language="java" session="true" contentType="text/html;charset=UTF-8" %>
<%@include file="common/includes.jsp" %>
<html>
<head>
    <title>抽奖出错</title>
    <%@include file="common/staticRef.jsp" %>
</head>
<body>
<div class="container-outer">
    <%@include file="common/navbar.jsp" %>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span9 lottery_container">
                <div>${error}</div>
                <div><a href="/lottery/lottery">重新抽奖</a></div>
            </div>
        </div>
    </div>
    <%@include file="common/foot.jsp" %>
</div>
</body>
</html>