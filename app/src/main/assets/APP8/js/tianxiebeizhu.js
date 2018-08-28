$(function(){
	var html;
	var localStorage = window.localStorage;
	var user_id = localStorage.getItem("user_id");
    var id = localStorage.getItem("id")
	console.log(user_id)
	
	$("#pullrefresh").css({"margin-top": $(".beizhu").outerHeight(true)+"px"})
	
	$(".tianjia").on("tap",function(){
		var remark = $(".beizhu textarea").val();
		console.log(id)
		console.log(remark);
		
		//内容不能为点击添加备注
		if($(".beizhu textarea").val() == '点击添加备注'){
			mui.alert("内容不能为空！");
			return 
		}
		//内容不能为空
		if($(".beizhu textarea").val() == ''){
			mui.alert("内容不能为空！");
			return 
		}
		//内容不能全都是空格
		if($(".beizhu textarea").val().indexOf(" ") != -1){
			mui.alert('不能全都是空格！');
			return 
		}
		
		$.ajax({
			type: "get",
			url: configIp + "/mobileterminal/workOrder/addWorkOrderRemark",
			dataType: 'jsonp',
			data:{
				"work_id": id,
			    "remark" : remark
			},
			crossDomain: true,
			async: "false",
			success: function (data) {
                if(data.code == '0'){
	                window.location.href="./gongdanguanli.html";
                }
			}
		})
	})
	
	//textarea获取焦点
	$(".beizhu textarea").on("focus",function(){
		if($(this).val() == '点击添加备注'){
			$(this).val('')
		}
	})
	//textarea失去焦点
	$(".beizhu textarea").on("blur",function(){
		if($(this).val() == ''){
			$(this).val('点击添加备注')
		}
	})
	var page = 1;
    abc();

    function abc() {
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
				"work_id": id,
				"currPage" :1
            }
            setTimeout(function () {
                $.ajax({
                    type: "get",
                    url: configIp + "/mobileterminal/workOrder/historyRemark",
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
				"work_id": id,
				"currPage" :page
            }
            console.log(data)
            setTimeout(function () {
                $.ajax({
                    type: "get",
                    url:configIp + "/mobileterminal/workOrder/historyRemark",
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
                        var lishi = template("lishi", data);
                        // console.log(areaInteraction)
                        $(".mui-table-view").append(lishi)
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

	
	
	

})
