$(function () {
    //充值宝列表查询
    var orderId = null;
    var recid = null;
    $.ajax({
        type: "get",
        url: configIp + "/mobileterminal/recharge/rechargeShow",
        dataType: 'jsonp',
        crossDomain: true,
        success: function (data) {
            console.log(data)
            if (data.code == 0) {
                var pay = template('test', data);
                // console.log(pay)
                $('#changePay').append(pay)
                $("#changePay span").on("click", function (i){
                    duration=$(this).attr("data-duration");
                    recid=$(this).attr("lang");
                    console.log(duration)
                    localStorage.setItem("duration",duration)
                   /* var time = data.data;
                    console.log(time)
                    for(var i = 0 ; i<time.length;i++){
                        console.log(time[i].duration)
                    }*/
                    
                    orderId = $(this).attr("lang")
                    console.log($(this).attr("lang"));
                    $(this).addClass("active").siblings("span").removeClass("active")
                })
            }
        }
    })

    //登录用户名  手机号  用户id
    var nike_name = localStorage.getItem("nike_name");
    var phone = localStorage.getItem("phone");
    var user_id = localStorage.getItem("user_id");
    // console.log(user_id)
    // console.log(phone)
    // 其他账号用户名 手机号  用户id
    var phone1 =localStorage.getItem("phone1");
    var nickname = localStorage.getItem("nickname")
    // var id1 = sessionStorage.getItem("id1")
    // console.log(phone)
    // console.log(nike_name)
    // console.log(phone1)
    // console.log(id1)
    // console.log(nickname)
    var id = getUrlParam("id")
    console.log(id)
   	
   
    if(id==null){
        $('.phone').append(phone)
        $('.name').append(nike_name) 
        $("button").on("tap", function () {
        	alert(1)
            console.log(1222)
            console.log(orderId)
            /* 封装支付宝支付 域名，支付的金额，用户的id */
            mui.plusReady(function () {
                // 获取支付通道
                plus.payment.getChannels(function(channels){
                    for (var i in channels) {
                        if (channels[i].id == "wxpay") {
                            wxChannel = channels[i];
                        } else {
                            aliChannel = channels[i];
                        }
                    }
                }, function (e) {
                    alert("获取支付通道失败：" + e.message);
                });
            })
            /* 测试支付 */
            $.ajax({
                type: "get",
                url: configIp + "/mobileterminal/recharge/beginRecharge",
                dataType: 'jsonp',
                data: {
                    "user_id": user_id,
                    "recid": recid,
                    "recharge_phone":$(".phone").html(),
                    "recharge_name": $(".name").html(),
                    "state": 0
                },
                crossDomain: true,
                async: "false",
                success: function (data){
                    console.log(data)
                    if (data.code == 0) {
                       localStorage.setItem('orderNum1',data.data.orderNum) 
                        var abc = data.data.payParams;
                        pay('alipay'); //调用支付宝
                        // +++++++++++支付+++++++++++
                        mui.plusReady(function () {
                            // 获取支付通道
                            plus.payment.getChannels(
                                function (channels) {
                                    for (vari in channels) {
                                        if (
                                            channels[i].id == "wxpay") { //后期适配微信
                                            wxChannel = channels[i];
                                        } else {
                                            aliChannel = channels[i]; //支付宝的
                                        }
                                    }
                                },
                                function (e) {
                                    alert("获取支付通道失败：" + e.message);
                                });
                        })
                        var ALIPAYSERVER = "";
    
                        function pay(id) {
                            // 从服务器请求支付订单
                            var PAYSERVER = '';
                            if (id == 'alipay') { //支付宝通道
                                PAYSERVER = ALIPAYSERVER;
                                channel = aliChannel;
                            } else if (id == 'wxpay') { //后期适配微信
                                PAYSERVER = WXPAYSERVER;
                                channel = wxChannel;
                            } else {
                                plus.nativeUI.alert("不支持此支付通道！", null, "请电话联系工作人员");
                                return;
                            }
                            plus.payment.request(channel, abc, function (result) {
                                // plus.nativeUI.alert("光银网提醒您积分充值成功！", function () {
                                //     back();
                                // });
                                window.location.href="./pay_success.html"
                            },
                            function (error) {
                                // plus.nativeUI.alert("支付失败编码：" + error.code);
                                window.location.href="./pay_fail.html"
                            });
                        }
                    }
                },
                error: function (data) {
                    console.log(data)
                }
            })
        })
    }else{
        $('.phone').append(phone1)
        $('.name').append(nickname) 
        $("button").on("tap", function () {
            console.log(3333)
            console.log(orderId)
            /* 封装支付宝支付 域名，支付的金额，用户的id */
            mui.plusReady(function(){
                // 获取支付通道
                plus.payment.getChannels(function (channels) {
                    for (var i in channels) {
                        if (channels[i].id == "wxpay") {
                            wxChannel = channels[i];
                        } else {
                            aliChannel = channels[i];
                        }
                    }
                }, function (e) {
                    alert("获取支付通道失败：" + e.message);
                });
            });
            /* 测试支付 */
            $.ajax({
                type: "get",
                url: configIp + "/mobileterminal/recharge/beginRecharge",
                dataType: 'jsonp',
                data: {
                    "user_id": user_id,
                    "recid": recid,
                    "recharge_phone":$(".phone").html(),
                    "recharge_name": $(".name").html(),
                    "state": 1
                },
                crossDomain: true,
                async: "false",
                success: function (data) {
                    console.log(data)
                    if (data.code == 0) {
                        localStorage.setItem('orderNum2',data.data.orderNum)
                        var abc = data.data.payParams;
                        pay('alipay'); //调用支付宝
                        // +++++++++++支付+++++++++++
                        mui.plusReady(function () {
                            // 获取支付通道
                            plus.payment.getChannels(
                                function (channels) {
                                    for (vari in channels) {
                                        if (
                                            channels[i].id == "wxpay") { //后期适配微信
                                            wxChannel = channels[i];
                                        } else {
                                            aliChannel = channels[i]; //支付宝的
                                        }
                                    }
                                },
                                function (e) {
                                    alert("获取支付通道失败：" + e.message);
                                });
                        })
                        var ALIPAYSERVER = "";

                        function pay(id) {
                            // 从服务器请求支付订单
                            var PAYSERVER = '';
                            if (id == 'alipay') { //支付宝通道
                                PAYSERVER = ALIPAYSERVER;
                                channel = aliChannel;
                            } else if (id == 'wxpay') { //后期适配微信
                                PAYSERVER = WXPAYSERVER;
                                channel = wxChannel;
                            } else {
                                plus.nativeUI.alert("不支持此支付通道！", null, "请电话联系工作人员");
                                return;
                            }
                            plus.payment.request(channel, abc, function (result) {
                                    // plus.nativeUI.alert("光银网提醒您积分充值成功！", function () {
                                    //     back();
                                    // });
                                    window.location.href="./pay_success.html?zhifu=1"
                                },
                                function (error) {
                                    window.location.href="./pay_fail.html"
                                });
                        }
                    }
                },
                error: function (data) {
                    console.log(data)
                }
            })
        })
    }
   
   

    /* 点击调用支付宝支付 */
    // $("button").on("tap", function () {
    //     console.log(orderId)
    //     /* 封装支付宝支付 域名，支付的金额，用户的id */
    //     mui.plusReady(function () {
    //         // 获取支付通道
    //         plus.payment.getChannels(function (channels) {
    //             for (var i in channels) {
    //                 if (channels[i].id == "wxpay") {
    //                     wxChannel = channels[i];
    //                 } else {
    //                     aliChannel = channels[i];
    //                 }
    //             }
    //         }, function (e) {
    //             alert("获取支付通道失败：" + e.message);
    //         });
    //     })
    //     /* 测试支付 */
    //     $.ajax({
    //         type: "get",
    //         url: configIp + "/mobileterminal/recharge/beginRecharge",
    //         dataType: 'jsonp',
    //         data: {
    //             "user_id": user_id,
    //             "recid": orderId,
    //             "recharge_phone": phone,
    //             "recharge_name": nike_name,
    //             "state": 0
    //         },
    //         crossDomain: true,
    //         async: "false",
    //         success: function (data) {
    //             console.log(data)
    //             if (data.code == 0) {
    //                 var abc = data.data.payParams;
    //                 pay('alipay'); //调用支付宝
    //                 // +++++++++++支付+++++++++++
    //                 mui.plusReady(function () {
    //                     // 获取支付通道
    //                     plus.payment.getChannels(
    //                         function (channels) {
    //                             for (vari in channels) {
    //                                 if (
    //                                     channels[i].id == "wxpay") { //后期适配微信
    //                                     wxChannel = channels[i];
    //                                 } else {
    //                                     aliChannel = channels[i]; //支付宝的
    //                                 }
    //                             }
    //                         },
    //                         function (e) {
    //                             alert("获取支付通道失败：" + e.message);
    //                         });
    //                 })
    //                 var ALIPAYSERVER = "";

    //                 function pay(id) {
    //                     // 从服务器请求支付订单
    //                     var PAYSERVER = '';
    //                     if (id == 'alipay') { //支付宝通道
    //                         PAYSERVER = ALIPAYSERVER;
    //                         channel = aliChannel;
    //                     } else if (id == 'wxpay') { //后期适配微信
    //                         PAYSERVER = WXPAYSERVER;
    //                         channel = wxChannel;
    //                     } else {
    //                         plus.nativeUI.alert("不支持此支付通道！", null, "请电话联系工作人员");
    //                         return;
    //                     }
    //                     plus.payment.request(channel, abc, function (result) {
    //                             plus.nativeUI.alert("光银网提醒您积分充值成功！", function () {
    //                                 back();
    //                             });
    //                         },
    //                         function (error) {
    //                             plus.nativeUI.alert("支付失败编码：" + error.code);
    //                         });
    //                 }
    //             }
    //         },
    //         error: function (data) {
    //             console.log(data)
    //         }
    //     })
    // })
})