$(function(){
    var localStorage = window.localStorage;
	var phone = localStorage.getItem("phone");
	
    $.ajax({
        type: "get",
        url: configIp + "/mobileterminal/recharge/historyRec",
        data:{
            "phone": phone,
            "currPage": 1
        },
        dataType: 'jsonp',
        crossDomain: true,
        success: function (data) {
            console.log(data)
            var list = template('list', data);
            $(".wrap").append(list)
        }
    })
})