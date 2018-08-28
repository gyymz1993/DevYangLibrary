$(function () {
	//进入app必须登录才能进入
	//后台code = 1 为成功  跳转到新页面（赵孟景定）
	var localStorage = window.localStorage;
	var sessionStorage = window.sessionStorage;
	var clientid;
	mui.plusReady(function () {
		clientid = plus.push.getClientInfo().clientid;
	});
	//默认打勾
	$('input[type="checkbox"]').attr("checked", "checked");

	//适配	mask内容区自定高度
	$(".mask .cons").height(($(".mask .wraps").height() - $(".mask .tops").height() - $(".mask .bts").height()) + 'px')

	//遮罩关闭
	$(".mask .wraps .tops img").on("click", function () {
		$(".mask").fadeOut()
	})

	//点击阅读并同意
	$("#container span").on("click", function (event) {
		//组织默认事件
		event.preventDefault();
		$(".mask").fadeIn()
	})

	//遮罩里	同意按钮
	$(".mask .agree").on("click", function () {
		$('input[type="checkbox"]').attr("checked", "checked");
		$(".mask").fadeOut()
	})
	//遮罩里	不同意按钮
	$(".mask .disagree").on("click", function () {
		$('input[type="checkbox"]').attr("checked", false);
		$(".mask").fadeOut()
	})

	var times = 60,
		timer = null;
	//点击	获取验证码
	$(".login_obtain").on("click", function () {
		//手机号验证
		if ($('.login_phone').val() == "") {
			mui.alert("手机号不能为空")
			return;
		} else if ($('.login_phone').val() != "") {
			if (!(/^1[34578]\d{9}$/.test($('.login_phone').val()))) {
				mui.alert("手机号格式不正确");
				return false;
			}
		}
		// 发送短信--60s
		var that = this;
		var search = $(".login_obtain").val();
		//避免点击获取验证码之后1s延迟
		$(".login_obtain").val("60秒后重试");
		this.disabled = true;
		//禁用之后		获取验证码按钮变灰
		$(".login_obtain").css("background", "linear-gradient(to bottom,#C7C5C5,#9C9B9B)")
		timer = setInterval(function () {
			times--;
			that.value = times + "秒后重试";
			if (times <= 0) {
				that.disabled = false;
				//解除禁用		获取验证码按钮变蓝
				$(".login_obtain").css("background", "linear-gradient(to bottom,#079DF4,#007CC4)")
				that.value = "发送验证码";
				clearInterval(timer);
				times = 60;
			}
		}, 1000);

		// 短信发送验证码接口
		var phone = $('.login_phone').val();
		$.ajax({
			type: "get",
			url: configIp + "/mobileterminal/login/loginSMS",
			data: {
				"phone": phone
			},
			dataType: 'jsonp',
			crossDomain: true,
			async: "false",
			success: function (data) {
				// 当验证码获得成功后，
				console.log(1)
				if (data.code == '0') {
					console.log(2)
					console.log(data)
				}
			},
			error: function () {
				console.log(data.msg);
			}
		});
	})
	
	
	
	//提交
	/* 如果登录过 */
	if (sessionStorage.getItem("codeId") == 1) {
		toPage("../index/index.html");
	} else { //如果注册过但没有登录
		$(".login_sub").on("click", function () {
			//手机号验证

			if ($('.login_phone').val() == "") {
				mui.alert("手机号不能为空")
				return;
			} else if ($('.login_phone').val() != "") {
				if (!(/^1[34578]\d{9}$/.test($('.login_phone').val()))) {
					mui.alert("手机号格式不正确");
					return false;
				}
			}
			//输入验证码
			if ($(".login_verificationcode").val() == '') {
				mui.alert("验证码不能为空");
				return false;
			}
			//同意用户协议
			if ($('input[type="checkbox"]').attr("checked") != 'checked') {
				mui.alert("请阅读并同意<br>《光银网注册/登录用户协议》");
				return false;
			}
			
			//电话号码
			var phoneval = $.trim($(".login_phone").val());
			//验证码
			var yanzhengma = $.trim($(".login_verificationcode").val());

			//短信验证登陆接口
			$.ajax({
				type: "get",
				url: configIp + "/mobileterminal/login/loginVerfy",
				data: {
					//电话号码
					"phone": phoneval,
					//验证码
					"smscode": yanzhengma
				},
				dataType: 'jsonp',
				crossDomain: true,
				async: "false",
				success: function (data) {
					//成功
					console.log(data)
					if (data.code == '0') {
						//老用户
						localStorage.setItem("local_phone", phoneval);
						if (data.state == '0') {
							//成功登录到主页
							toPage("../index/index.html");
							console.log(data.ID)
							localStorage.setItem("user_id", data.ID);
							/* 判断老用户有没有登录过 */
							sessionStorage.setItem("codeId", 1);
							//新用户
						} else {
							toPage("./personal_setting1.html")
						}

					}else{
						mui.alert(data.msg)
					}
				},
				error: function () {
					console.log('跨域请求失败');
				}
			});
		})
	}














})