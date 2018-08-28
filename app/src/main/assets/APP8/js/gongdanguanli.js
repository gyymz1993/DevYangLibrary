$(function () {

    // setTimeout(function(){
    //     $('.wrap2 i').on('tap',function(){
	// 		var id = $(this).attr("data-id");
	// 	 console.log(id)
	// 	 localStorage.setItem("id",id)
    //         location.href="./tianxiebeizhu.html";
    //     })
    // },3000)

    // $(".mui-table-view").on('tap','i',function(){
    //     var id = $(this).attr("data-id");
    //     console.log(id)
    //    localStorage.setItem("id",id)
    // })

    // $(".wrap2 i").on('tap',function(){
    //     var id = $(this).attr("data-id");
    //     console.log(id)
    //       sessionStorage.setItem("id",id)
    // })
    
    var page = 1;
    abc();
	var localStorage = window.localStorage;
	var user_id = localStorage.getItem("user_id");
	console.log(user_id)
	
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
                "user_id": user_id,
                "currPage": 1
            }
            setTimeout(function () {
                $.ajax({
                    type: "get",
                    url: configIp + "/mobileterminal/workOrder/workOrderShow",
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
                "user_id": 2,
                "currPage": page
            }
            console.log(data)
            setTimeout(function () {
                $.ajax({
                    type: "get",
                    url: configIp + "/mobileterminal/workOrder/workOrderShow",
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
                        var areaInteraction = template("areaInteraction", data);
                        // console.log(areaInteraction)
                        $(".mui-table-view").append(areaInteraction)
                        $(".mui-table-view").on('tap','i',function(){
                            var id = $(this).attr("data-id");
                            console.log(id)
                           localStorage.setItem("id",id)
                           location.href="./tianxiebeizhu.html";
                        })
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