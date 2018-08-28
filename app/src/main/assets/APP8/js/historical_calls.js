$(function () {
	var arr = ['修改', '保存'];
	var con;
	var localStorage = window.localStorage;
	var phone = localStorage.getItem("phone");
		console.log(phone)
	$(".list .modify").on("click", function () {
		//修改
		if ($(this).find("span").html() == arr[0]) {
			//把内容放进con里
			console.log(1)
			con = $(this).parents(".list").find("p").html();
			console.log(con)
			//样式改变
			$(this).parents(".list").find("p").css({
				"background": "#fff",
				"border": "1px solid #ddd",
				"border-radius": "5px"
			})
			//p可编辑
			$(this).parents(".list").find("p").attr("contenteditable", true)
			//按钮名称 改变
			$(this).find("span").html(arr[1])
			//保存
		} else if ($(this).find("span").html() == arr[1]) {
			console.log(2)
			//样式改变
			$(this).parents(".list").find("p").css({
				"background": "#EFEFF4",
				"border": "none"
			})
			//p不可编辑
			$(this).parents(".list").find("p").attr("contenteditable", false)
			//按钮名称 改变
			$(this).find("span").html(arr[0])
			//把con里的内容放进p里
			console.log(con)
		}

		// +++++++++++++++++++++++++++++++++
		

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
					"page": page
				};
				data = {
					"area": area,
					"size": 6,
					"page": page,
					"sex": sex,
					"classifyID": classifyID,
					"name": name
				}
				setTimeout(function () {
					$.ajax({
						type: "get",
						url: configIp + "/appService/loanAdviserList",
						dataType: 'jsonp',
						data: data,
						crossDomain: true,
						async: "false",
						success: function (data) {
							console.log(data.configIp)
							data.configIp = imgConfigIp;
							var pullInteraction = template("pullInteraction", data);
							$(".mui-table-view").prepend(pullInteraction)
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
					"area": area,
					"size": 6,
					"page": page,
					"sex": sex,
					"classifyID": classifyID,
					"name": name
				}
				console.log(data)
				setTimeout(function () {
					$.ajax({
						type: "get",
						url: configIp + "/appService/loanAdviserList",
						dataType: 'jsonp',
						data: data,
						crossDomain: true,
						async: "false",
						success: function (data) {
							data.configIp = imgConfigIp;
							var pullInteraction = template("pullInteraction", data);
							$(".mui-table-view").append(pullInteraction)
							page++;
							mui('#pullrefresh').pullRefresh().endPullupToRefresh((data.data.length == 0)); //参数为true代表没有更多数据了。
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











































})