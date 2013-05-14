<%@ page language="java" session="true" contentType="text/html;charset=UTF-8" %>
<%@include file="common/includes.jsp" %>
<html>
<head>
    <title>抽奖</title>
    <%@include file="common/staticRef.jsp" %>
    <script type="text/javascript" src="/lottery/js/lottery.js"></script>
</head>
<body>
<div class="container-outer">
    <%@include file="common/navbar.jsp" %>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span9 lottery_container">
                <form method="post" action="/lottery/lottery">
                    <dl>
                        <dt>请输入手机号：</dt>
                        <dd>
                            <input type="text" name="phone" id="phoneNumber">
                        </dd>
                    </dl>
                    <input id="start" type="submit" value="抽奖">
                </form>
                <marquee class="specialPrizeGot" behavior="scroll" direction="up" width="200" height="400"
                         scrollamount="2" scrolldelay="10">
                <div>
                    恭喜以下用户抽中特等奖：
                    <table id="table1" width="300" border="1" cellpadding="0"
                           cellspacing="0">
                        <tr>
                            <th>手机号</th>
                        </tr>
                        <c:forEach var="lottery" items="${specialPrizes}">
                            <tr>
                                <td>${lottery.phoneNumber}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                </marquee>
            </div>
            <!--/span-->
        </div>
        <!--/row-->
    </div>
    <%@include file="common/foot.jsp" %>
</div>
</body>
</html>