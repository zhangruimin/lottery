<%@ page language="java" session="true" contentType="text/html;charset=UTF-8" %>
<c:set var="currentUser" value='<%=request.getSession().getAttribute("currentUser")%>'/>
<div class="navbar">
    <span class="logo"></span>

    <ul class="navbar-top">
        <li class="first">欢迎回来： <a href="#" class="navbar-link">${currentUser.location}</a></li>
        <%--<li><a href="/lottery/users/devices?userId=${currentUser.id}">设备管理</a>  </li>--%>
        <li><a href="/lottery/logout" class="navbar-link">退出</a>  </li>
    </ul>
</div>