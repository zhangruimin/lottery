<%@ page language="java" session="true" contentType="text/html;charset=UTF-8" %>
<%@include file="common/includes.jsp" %>
<html>
<head>
    <title>用户管理</title>
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
                        <h2>用户管理</h2>
                    </div>

                    <div id="content">
                        <h3>用户信息</h3>
                        <table id="table1" width="700" border="1" cellpadding="0"
                               cellspacing="0">
                            <tr>
                                <th>用户名</th>
                                <th>网点</th>
                                <th>密码</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td>${user.userName}</td>
                                    <td>${user.location}</td>
                                    <td>${user.password}</td>
                                    <td><a href="/lottery/admin/removeUser?id=${user.id}">删除</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                        <h3>添加用户</h3>

                        <div>
                            <form class="add_devices" method="post" action="/lottery/admin/addUser">
                                <div>用户名：<input type="text" name="userName"></div>
                                <div>密码：<input type="text" name="password"></div>
                                <div>网点：<input type="text" name="location"></div>
                                <input type="submit" value="添加">
                            </form>
                        </div>
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