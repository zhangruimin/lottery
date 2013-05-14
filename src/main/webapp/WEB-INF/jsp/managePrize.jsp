<%@ page language="java" session="true" contentType="text/html;charset=UTF-8" %>
<%@include file="common/includes.jsp" %>
<html>
<head>
    <title>奖券管理</title>
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
                        <h2>奖券管理</h2>
                    </div>

                    <div id="content">
                        <h3>奖券状态</h3>
                        <table id="table1" width="700" border="1" cellpadding="0"
                               cellspacing="0">
                            <tr>
                                <th>状态</th>
                                <th>总数</th>
                                <th>特等奖券</th>
                                <th>纪念奖券</th>
                                <th>空白奖券</th>
                            </tr>
                            <tr>
                                <td>全部</td>
                                <td>${ticketState.specialUsed+ticketState.normalUsed+ticketState.blankUsed+ticketState.specialNotUsed+ticketState.normalNotUsed+ticketState.blankNotUsed}</td>
                                <td>${ticketState.specialUsed+ticketState.specialNotUsed}</td>
                                <td>${ticketState.normalUsed+ticketState.normalNotUsed}</td>
                                <td>${ticketState.blankUsed+ticketState.blankNotUsed}</td>
                            </tr>
                            <tr>
                                <td>已使用</td>
                                <td>${ticketState.specialUsed+ticketState.normalUsed+ticketState.blankUsed}</td>
                                <td>${ticketState.specialUsed}</td>
                                <td>${ticketState.normalUsed}</td>
                                <td>${ticketState.blankUsed}</td>
                            </tr>
                            <tr>
                                <td>未使用</td>
                                <td>${ticketState.specialNotUsed+ticketState.normalNotUsed+ticketState.blankNotUsed}</td>
                                <td>${ticketState.specialNotUsed}</td>
                                <td>${ticketState.normalNotUsed}</td>
                                <td>${ticketState.blankNotUsed}</td>
                            </tr>
                        </table>
                        <h3>添加奖券</h3>

                        <div>
                            <form class="add_devices" method="post" action="/lottery/admin/addTickets">
                                <div>特等奖券：<input type="text" name="special"></div>
                                <div>纪念奖券：<input type="text" name="normal"></div>
                                <div>空白奖券：<input type="text" name="blank"></div>
                                <input type="submit" value="添加">
                            </form>
                        </div>
                        <h3>删除奖券</h3>

                        <div>
                            <form class="add_devices" method="post" action="/lottery/admin/removeTickets">
                                <div>特等奖券：<input type="text" name="special"></div>
                                <div>纪念奖券：<input type="text" name="normal"></div>
                                <div>空白奖券：<input type="text" name="blank"></div>
                                <input type="submit" value="删除">
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