<%@ page language="java" session="true" contentType="text/html;charset=UTF-8" %>
<%@include file="common/includes.jsp" %>
<html>
<head>
    <title>控制注册</title>
    <%@include file="common/staticRef.jsp" %>
</head>
<body>
<div class="container-outer">
    <%@include file="common/navbar.jsp" %>
    <div class="container-fluid">
        <div class="row-fluid">
            <%@include file="common/sidebar.jsp" %>

            <div class="span9">
                <div class="hero-unit">
                    <div id="header">
                        <h2>控制注册</h2>
                    </div>

                    <div id="content">
                       <div>当前状态：${status}</div>
                       <div><a href="/lottery/admin/manageRegister?status=false">关闭</a></div>
                       <div><a href="/lottery/admin/manageRegister?status=true">开启</a></div>
                    </div>
                </div>
            </div>
            <hr>
        </div>
    </div>
    <%@include file="common/foot.jsp" %>
</div>
</body>
</html>