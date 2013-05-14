$(function () {
    $("#start").click(function () {
        var phone = $("#phoneNumber").val();
        if(phone.length!==11||!/^\d{11}$/.test(phone)){
            alert("手机号码格式不对");
            return false;
        }
    });


});