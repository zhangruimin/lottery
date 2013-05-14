$(function () {

    $("#start").click(function () {
        var phone = $("#phoneNumber").val();
        if(phone.length!==11||!/^\d{11}$/.test(phone)){
            alert("手机号码格式不对!");
            return false;
        }
        var startWith = function(s, sub){
            return s.indexOf(sub) === 0
        }
        var  isCorrectNumberRange = function(phone){
            return startWith(phone, "133")||startWith(phone, "153")||
                startWith(phone, "180")||startWith(phone, "181")||
                startWith(phone, "189");
        }
        if(!isCorrectNumberRange(phone)) {
            alert("手机号段不正确!");
            return false;
        }
    });


});