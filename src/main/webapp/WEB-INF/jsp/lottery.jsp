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
                <c:choose>
                    <c:when test="${status}">
                        <form method="post" action="/lottery/lottery">
                            <dl>
                                <dt>请输入手机号：</dt>
                                <dd>
                                    <input type="text" name="phone" id="phoneNumber">
                                </dd>
                            </dl>
                            <input id="start_lottery" type="submit" value="">
                        </form>
                        <div class="lottery_board">
                            <marquee class="specialPrizeGot" behavior="scroll" direction="up" width="350" height="400"
                                     scrollamount="2" scrolldelay="10">
                                <div>
                                    <table id="table1" width="300" border="1" cellpadding="0"
                                           cellspacing="0">
                                        <tr>
                                            <th>手机号</th>
                                            <th>网点</th>
                                        </tr>
                                        <c:forEach var="lottery" items="${specialPrizes}">
                                            <tr>
                                                <td>${lottery.phoneNumber}</td>
                                                <td>${lottery.userLocation}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </marquee>
                        </div>
                    </c:when>

                    <c:otherwise>
                        还没有开始抽奖！
                    </c:otherwise>
                </c:choose>

            </div>
            <!--/span-->
        </div>
        <!--/row-->
    </div>
    <%@include file="common/foot.jsp" %>
</div>
</body>
</html>