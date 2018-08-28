$(function() {
	var localStorage = window.localStorage;
	var customer_id = null;
	var user_id = localStorage.getItem("user_id");
	console.log(user_id)
	
	//右上角消息提示
	$.ajax({
        type: "get",
        url: configIp + "/mobileterminal/home/queryInformation",
        dataType: 'jsonp',
        data: {
            "user_id": user_id
        },
        crossDomain: true,
        success: function (data) {
            if (data.code == 0) {
           		console.log(data)
           		console.log(data.data.length)
            	if(data.data.length == 0){
            		//当为0时，消息不显示
            		$("header .right").html("");
            		
            	}else{
            		//显示具体多少个消息
            		$("header .right").append('<i class="infor">'+data.data.length+'</i>')
            	}
            }
        }
    })
	
	$("header > .right").on("tap", function() {
		$(".myinfor").show();
		$.ajax({
	        type: "get",
	        url: configIp + "/mobileterminal/home/queryInformation",
	        dataType: 'jsonp',
	        data: {
	            "user_id": user_id
	        },
	        crossDomain: true,
	        success: function (data) {
	            console.log(data)
	            console.log(data.data.length)
	            if (data.code == 0) {
            		var imageFilename = template("imageFilename", data);
                	$("#content1").append(imageFilename);
	                
	                $("#content1 button").on("click", function () {
	                    console.log($(this).parents(".cell").attr("lang"))
	                    $.ajax({
	                        type: "get",
	                        url: configIp + "/mobileterminal/home/delInformation",
	                        dataType: 'jsonp',
	                        data: {
	                            "mid": $(this).parents(".cell").attr("lang")
	                        },
	                        crossDomain: true,
	                        success: function (data) {
	                            if (data.code == 0) {
	                                mui.toast("删除完成")
	                            }
	                        }
	                    })
	                })
	            }
	        }
	    })
	})
	//返回，消息不显示
	$(".tops img").on("tap", function() {
		//当为0时，消息不显示
        $("header .right").html("")
		$(".myinfor").hide();
	})
	
	
	
		/* 首页内容交互展示 */
	var array;
	var _index;
	$.ajax({
		type: "get",
		url: configIp + "/mobileterminal/home/homeShow",
		data: {
			"user_id": user_id
		},
		dataType: 'jsonp',
		crossDomain: true,
		async: "false",
		success: function(data) {
			console.log(data)
			if(data.code == 0) {
				console.log(data.data.phone)
				localStorage.setItem("phone", data.data.phone)
				localStorage.setItem("nike_name", data.data.nike_name)
				console.log(data.data.nike_name)
				var roleInteraction = template("roleInteraction", data);
				$("#content .personMsg .top").append(roleInteraction);
				var guwenInteraction = template("guwenInteraction", data);
				$("#content .client .cellTotal").append(guwenInteraction);
				/* 获得后台传给的时间 */
				
				array = data.data.customers;
				console.log(array)
				for(var index = 0; index < array.length; index++) {
					const element = array[index].can_call_time;
					console.log(element)
					var _index = index;
					var indexArr = [];
					if(element) {
						indexArr.push(_index)
						console.log(indexArr)
						for(var index1 = 0; index1 < indexArr.length; index1++) {
							var indexCell = indexArr[index1];
							$.leftTime(element, function(d) {
								if(d.status) {
									var $dateShow1 = $(".daojishi" + indexCell);
									$dateShow1.find(".d").html(d.d);
									$dateShow1.find(".h").html(d.h);
									$dateShow1.find(".m").html(d.m);
									$dateShow1.find(".s").html(d.s);
									if(d.d == 0 & d.h == 0 & d.m == 0 & d.s == 0) {
										console.log(123456)
										$(".daojishi" + indexCell).parents("i").html("---");
										/* 当时间到了后改变改变颜色，改变文字，增加contact类名，移除contacted类名 */
										$(".daojishi" + indexCell).parents(".newClient").find("button .contacted").css("backgroundColor", "#aaa").text("联系他").attr('disabled', true).addClass("contact").removeClass("contacted");
									}
								}
							});
						}
					}
				}
				
				
				/* 点击最高价格 */
				$("#content > .client > .cellTotal > .newClient .button > span:nth-of-type(2) button").on("click", function() {
					customer_id = $(this).parents(".newClient").attr("lang");
					$.ajax({
						type: "get",
						url: configIp + "/mobileterminal/home/topPriceBuy",
						data: {
							"user_id":user_id,
							"customer_id": customer_id
						},
						dataType: 'jsonp',
						crossDomain: true,
						async: "false",
						success: function(data) {
							//余额充足
							if(data.code == '0') {
								$(".mask4").show();
								$(this).parents(".newClient").hide();
							//余额不足
							}else{
								$(".mask3").show()
							}
						}
					})
				});
				
				//出价
				$(".chujia").on("click",function(){
					customer_id = $(this).parents(".newClient").attr("lang");
					$.ajax({
						type: "get",
						url: configIp + "/mobileterminal/home/auctionBid",
						data: {
							"user_id": user_id,
							"customer_id": customer_id,
							"price":1
						},
						dataType: 'jsonp',
						crossDomain: true,
						async: "false",
						success: function(data) {
							console.log(data)
							//联系过客户，可以直接出价
							if(data.code == '0'){
								$("#block-range-val").html("0");
								$("#block-range").val('0');
								//滑块
								$(".mask2").show()
							}
								
							//未联系过客户
							if(data.code == '-4'){
								//暂无资格
								$('.mask1').show();
							}
							
						}
					})
				})
			}
		}
	})
	
	
	
	//最高价购买
	$(".mask1 a").on("click",function(){
		$.ajax({
			type: "get",
			url: configIp + "/mobileterminal/home/topPriceBuy",
			data: {
				"user_id": user_id,
				"customer_id":customer_id
			},
			dataType: 'jsonp',
			crossDomain: true,
			async: "false",
			success: function(data){
				console.log(customer_id)
				if(data.code == '0'){
					//成功出价
					$(".mask4").show();
					$(".mask1").hide()
				}else{
					//余额不足
					$(".mask3").show();
					$(".mask1").hide()
				}
			}
		})
	})
	
	//选择出价 确认
	$(".mask2 .queding").on("click",function(){
		$.ajax({
			type: "get",
			url: configIp + "/mobileterminal/home/auctionBid",
			data: {
				"user_id": user_id,
				"customer_id":customer_id,
				"price":$("#block-range").val(),
			},
			dataType: 'jsonp',
			crossDomain: true,
			async: "false",
			success: function(data){
				//成功
				if(data.code == '0'){
					//滑块消失
					$(".mask2").hide();
					//成功出价 出现
					$(".mask4").show();
				//失败
				}else if(data.code == '-1'){
					//滑块消失
					$(".mask2").hide();
					//余额不足 出现
					$(".mask3").show();
				//竞拍人数已达限额
				}else{
					//滑块消失
					$(".mask2").hide();
					$(".mask5").show();
				}
			}
		})
	})
	//暂无资格  确认
	$(".mask1 .queding").on("click",function(){
		$(".mask1").hide();
		window.location.reload();
	})
	//选择出价 	取消
	$(".mask2 .quxiao").on("click",function(){
		$(".mask2").hide()
	})
	//滑块监听
	$('#block-range').on("input",function(){
		$("#block-range-val").html($(this).val())
	})
	//余额不足 	取消
	$(".mask3 .quxiao").on("click",function(){
		$(".mask3").hide()
	})
	//余额不足 	充值
	$(".mask3 .queding").on("click",function(){
		toPage('../pay/pay.html')
	})
	//成功出价 	确认
	$(".mask4 .queding").on("click",function(){
		$(".mask4").hide();
		window.location.reload();
	})
	
	$(".mask5 .queding").on("click",function(){
		$(".mask5").hide();
		window.location.reload();
	})

})