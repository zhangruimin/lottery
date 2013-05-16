$(function () {
    $("#register").click(function () {
        if(!($("#userName").val())){
            alert("用户名不能为空！");
            return false;
        }

        if(!($("#password").val())){
            alert("密码不能为空！");
            return false;
        }

        if(!($("#location").val())){
            alert("网点不能为空！");
            return false;
        }
    });

});