$(function(){

	$("#pullrefresh").css({"margin-top":($(".inpu").outerHeight(true)+$(".tops").outerHeight(true)+22*3.75)+"px"});

	var localStorage = window.localStorage;
	var user_id = localStorage.getItem("user_id");
	console.log(user_id)
    var phone1 = null;
    var nickname = null;
    var id1 = null;
    
    var page = 1;
    abc();
    
    function abc(){
    	mui.init({
            pullRefresh: {
                container: '#pullrefresh',
                down: {
                    callback: pulldownRefresh
                },
                up: {
                    contentrefresh: '正在加载...',
                    callback: pullupRefresh
                }
            }
        });
        
        /*
         * 下拉刷新具体业务实现
         */
        function pulldownRefresh() {
            var data = {
                "user_id": user_id,
                "currPage": 1
            }
            setTimeout(function () {
                $.ajax({
                    type: "get",
                    url: configIp + "/mobileterminal/recharge/myRechargeAccount",
                    dataType: 'jsonp',
                    data: data,
                    crossDomain: true,
                    async: "false",
                    success: function (data) {
                        data.configIp = imgConfigIp;
                        // var pullInteraction = template("pullInteraction", data);
                        // $(".mui-table-view").prepend(pullInteraction)
                    }
                });
                // ====++++
                mui('#pullrefresh').pullRefresh().endPulldownToRefresh(); //refresh completed
            }, 1500);
        }
        
        /*
         * 上拉加载具体业务实现
         */
        function pullupRefresh() {
            var data = {
                "page": page
            };
            data = {
                "user_id": user_id,
                "currPage": page
            }
            console.log(data)
            setTimeout(function () {
                $.ajax({
                    type: "get",
                    url: configIp + "/mobileterminal/recharge/myRechargeAccount",
                    dataType: 'jsonp',
                    data: data,
                    crossDomain: true,
                    async: "false",
                    success: function (data) {
                        console.log(data);
                        // var id = data.data.page;
                        // for(var i = 0 ;i<id.length;i++){
                        //     var id1 =id[i].id1.remark;
                        //     sessionStorage.setItem("rem",id1)
                        // }
                       
                        data.configIp = imgConfigIp;
                        console.log(data.configIp)
                        var qita = template("qita", data);
                        // console.log(areaInteraction)
                        $(".mui-table-view").append(qita)
                        var totalPage = Math.ceil(data.data.totalCount / data.data.pageSize)
                        console.log(totalPage)
                        page++;
                        mui('#pullrefresh').pullRefresh().endPullupToRefresh((page > totalPage)); //参数为true代表没有更多数据了。
                    }
                });
                // ====++++
            }, 1500);
        }

        if (mui.os.plus) {
            mui.plusReady(function () {
                setTimeout(function () {
                    mui('#pullrefresh').pullRefresh().pullupLoading();
                }, 1000);
            });
        } else {
            mui.ready(function () {
                mui('#pullrefresh').pullRefresh().pullupLoading();
            });
        }
        
        
    }
    
    
    
    $(".sousuo").on("click",function(){
    	if($("input").val() == ""){
    		mui.alert("内容不能为空");
			return false;
    	}
    	if (!(/^1[34578]\d{9}$/.test($('input').val()))) {
			mui.alert("手机号格式不正确");
			return false;
		}
    	$.ajax({
    		type: "get",
            url: configIp + "/mobileterminal/recharge/byCondition",
            data: {
                "keyword": $("input").val()
            },
            dataType: 'jsonp',
            crossDomain: true,
            async: "false",
            success: function (data){
            	if(data.code == '0'){
            		if(data.data.id == undefined || data.data.phone == undefined || data.data.nike_name == undefined){
            			mui.alert("手机号码不存在或未在本平台进行注册")
            		}else{
	            		console.log(data)
	            		var html = '<div class="list" lang="'+data.data.id+'">'+
								    	'<div class="left">'+
								    		'<p>充值帐号：'+data.data.phone+'</p>'+
								    		'<p>姓名：'+data.data.nike_name+'</p>'+
								    	'</div>'+
								    	'<div class="right">去充值</div>'+
								    '</div>';
						$(".wrap").html(html);
						
	            		$(".wrap").show();
	            		
	            		phone1 = data.data.phone;
	            		//去充值
	            		$(".wrap .right").on("click",function(){
		                	localStorage.setItem("phone1",data.data.phone)
			                localStorage.setItem("nickname",data.data.nike_name)
			                localStorage.setItem("id1",data.data.id)
	            			console.log(data.data.phone)
	            			console.log(data.data.nike_name)
	            			console.log(data.data.id)
	            			toPage('../pay/pay.html?id='+phone1);
	            		});
            		}
            		
            	}
            }
    	})
    })
})









