$(function () {

    $("#start").click(function () {
        var phone = $("#phoneNumber").val();
        if(phone.length!==11||!/^\d{11}$/.test(phone)){
            alert("手机号码格式不对!");
            return false;
        }
        var  isCorrectNumberRange = function(phone){
            return phone.startsWith("133")||phone.startsWith("153")||
                phone.startsWith("180")||phone.startsWith("181")||
                phone.startsWith("189");
        }
        if(!isCorrectNumberRange(phone)) {
            alert("手机号段不正确!");
            return false;
        }
    });


});