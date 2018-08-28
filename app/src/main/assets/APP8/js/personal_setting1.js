$(function(){
	//城市id
	var city_id = null;
	var localStorage = window.localStorage;
	var local_areaId = null;

	//进入个人设置1之后加载城市接口
	$.ajax({
		type: "get",
        url: configIp + "/mobileterminal/login/gainArea",
        dataType: 'jsonp',
        crossDomain: true,
        async: "false",
		success: function (data) {
            if (data.code == '0') {
	
            	console.log(data)
            	var chengshi = template("chengshi", data);
                $(".choose").html(chengshi);
                
                var abc = $(".select_business .choose span[lang='420100']").text()
                console.log(abc)
                //城市单选并赋值
				$(".choose span").on("click",function(){
					//城市赋值
					$(".citys").val($(this).html());
					//赋值id
					$(".citys").attr("lang",$(this).attr("lang"));
					//单个高亮
					$(this).addClass("active").siblings().removeClass("active")
				});
				
				//下一步
				$(".next1").on("click",function(){
					//传值
					$.ajax({
						type: "get",
				        url: configIp + "/mobileterminal/login/addUserMessage",
				        data:{
				        	"cityCode":$(".citys").attr("lang")
				        },
				        dataType: 'jsonp',
				        crossDomain: true,
				        async: "false",
						success: function (data){
							console.log(2)
			//	            if (data.code == '0') {
								//跳到个人设置2
								console.log(data)
								//获取城市id
								city_id = $(".citys").attr("lang");
								//存储所选区域名称
								localStorage.setItem("local_areaId",city_id);
								toPage('personal_setting2.html')
			//	            }
				        },
				        error: function () {
				            console.log('跨域请求失败');
				        }
					});
				})
				
            }
        },
        error: function () {
            console.log('跨域请求失败');
        }
	});
	console.log(city_id)
	//城市区域		高度适配
	$(".choose").height(($(window).height()-$(".tops").height()-$(".area").height()-$(".next1").height()-45*3.75)+"px");
	
	//跳过
	$(".skip1").on("click",function(){
		//跳到个人设置2
		localStorage.setItem("local_areaId",420100);
		toPage('personal_setting2.html')
	})
	
	
	
	
})
