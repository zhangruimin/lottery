<%@ page language="java" session="true" contentType="text/html;charset=UTF-8" %>
<%@include file="common/includes.jsp" %>
<html>
<head>
    <title>中奖查询</title>
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
                        <h2>中奖查询</h2>
                    </div>

                    <div id="content">
                        <form class="add_devices" method="get" action="/lottery/admin/queryPrize">
                            <div>手机号：<input type="text" name="phone"></div>
                            <div>只查特等奖：<input type="checkbox" name="specialOnly" value="true" ></div>
                            <input type="submit" value="查询">
                        </form>
                        <h3>查询结果</h3>
                        <table id="table1" width="700" border="1" cellpadding="0"
                               cellspacing="0">
                            <tr>
                                <th>手机号</th>
                                <th>中奖情况</th>
                                <th>网点</th>
                                <th>网点用户名</th>
                            </tr>
                            <c:forEach var="lottery" items="${lotteryRecords}">
                                <tr>
                                    <td>${lottery.phoneNumber}</td>
                                    <td>${lottery.prizeState}</td>
                                    <td>${lottery.userLocation}</td>
                                    <td>${lottery.userName}</td>
                                </tr>
                            </c:forEach>
                        </table>
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