<%@ page language="java" session="true"
	contentType="text/html;charset=UTF-8"%>
<%@include file="common/includes.jsp"%>
<html>
<head>
<title>注册</title>
<%@include file="common/staticRef.jsp"%>
</head>
<body>
	<div class="container-outer">
		<div class="navbar">
			<span class="logo logo-login"></span>
		</div>
		<div class="container-fluid user-info-form" id="register-form">
			<span id="register-form-bg"> </span> 
				<form id="register-left-bg" method="post" action="/lottery/register">
					<dl>
						<dt>用户名：</dt>
						<dd>
							<input type="text" name="userName" style="width:175px" >
						</dd>
						<dt>密码：</dt>
						<dd>
							<input type="password" name="password" style="width:175px">
						</dd>
						<dt>网点名称：</dt>
						<dd>
							<input type="text" name="phoneNumber" style="width:175px">
						</dd>
					</dl>
					<input id="register" type="submit" value="立即注册">
				</form>
		</div>
		<%@include file="common/foot.jsp"%>
	</div>
</body>
</html>