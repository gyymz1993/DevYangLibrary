$(function(){
	//选工作年限赋值
	$(".choose span").on("click",function(){
		var val = $(this).html()
		//工作年限赋值
		$(".year_val").val(val)
		//单个高亮
		$(this).addClass("active").siblings().removeClass("active")
		
	})
	
	//跳过
	$(".skip2").on("click",function(){
		//跳到主页
		toPage('../index/index.html')
	})

	
	//下一步
	var localStorage = window.localStorage;
	
	$(".next2").on("click",function(){
		if($('.name_val').val() == ''){
			mui.alert('请添加昵称');
			return false
		}
		if($('.name_val').val().length <= 1){
			mui.alert('昵称不能少于2个字');
			alert($('.name_val').val().length)
			return false
		}
		if($('.year_val').val() == ''){
			mui.alert('工作年限不能为空');
			return false
		}
		//获取本人手机号
		var local_phone = localStorage.getItem("local_phone");
		var local_areaId = localStorage.getItem("local_areaId");
		
		//传值
		$.ajax({
			type: "get",
	        url: configIp + "/mobileterminal/login/addUserMessage",
	        data:{
	        	"phone":local_phone,
	        	"cityCode":local_areaId,
	        	"nickName":$(".name_val").val(),
	        	"workYears":$(".year_val").val(),
	        },
	        dataType: 'jsonp',
	        crossDomain: true,
	        async: "false",
			success: function (data){
            	console.log(data);
            	//跳到主页
				if(data.code == '0'){
					toPage('../index/index.html')
				}
	        },
	        error: function () {
	            console.log('跨域请求失败');
	        }
		});
		
	})
})
