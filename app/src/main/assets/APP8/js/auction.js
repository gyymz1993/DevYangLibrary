$(function () {
	//安卓机加载数据
	setTimeout(function(){
		pullDownfresh1()
	},100)
	
	
	mui.init({
        swipeBack: false,
        keyEventBind: {
            backbutton: false
        },
        pullRefresh: [
        {
            container: '#content1',
            down: {
                // contentdown: "下拉可以刷新",
                contentover: "释放立即刷新",
                contentrefresh: "正在刷新...",
                callback: pullDownfresh1
            },
            up: {
                height: 50, //可选.默认50.触发上拉加载拖动距离
                // auto:true,
                contentrefresh: "正在加载...",
                contentnomore: '哼，我也是有底线的...',
                callback: pullupRefresh1
            }
        },
        {
            container: '#content2',
            down: {
                contentover: "释放立即刷新",
                contentrefresh: "正在刷新...",
                callback: pullDownfresh2
            },
            up: {
                height: 50, //可选.默认50.触发上拉加载拖动距离
                contentrefresh: "正在加载...",
                contentnomore: '哼，我也是有底线的...',
                callback: pullupRefresh2
            }
        }, 
        {
            container: '#content3',
            down: {
                contentdown: "下拉可以刷新",
                contentover: "释放立即刷新",
                contentrefresh: "正在刷新...",
                callback: pullDownfresh3
            },
            up: {
                height: 50, //可选.默认50.触发上拉加载拖动距离
                contentrefresh: "正在加载...",
                contentnomore: '哼，我也是有底线的...',
                callback: pullupRefresh3
            }
        },
        {
            container: '#content4',
            down: {
                contentdown: "下拉可以刷新",
                contentover: "释放立即刷新",
                contentrefresh: "正在刷新...",
                callback: pullDownfresh4
            },
            up: {
                height: 50, //可选.默认50.触发上拉加载拖动距离

                contentrefresh: "正在加载...",
                contentnomore: '没有更多数据了',
                callback: pullupRefresh4
            }
        }, 
        {
            container: '#content5',
            down: {
                contentdown: "下拉可以刷新",
                contentover: "释放立即刷新",
                contentrefresh: "正在刷新...",
                callback: pullDownfresh5
            },
            up: {
                height: 50, //可选.默认50.触发上拉加载拖动距离
                contentrefresh: "正在加载...",
                contentnomore: '哼，我也是有底线的...',
                callback: pullupRefresh5
            }
        }
        ]
    });
	
	var user_id,
		array1,
		array2,
		array3,
		array4;
    var index1 = 1;
    var dataIndex = 1, //总页数
        index2 = 1,
        index3 = 1,
        index4 = 1,
        index5 = 1;
	var localStorage = window.localStorage;
	var user_id = localStorage.getItem("user_id");
	console.log(user_id)
		
        function pullDownfresh1() {
            $("#content1 .mui-table-view").empty();
            index1 = 1;
				getBorrowList(user_id, index1);
	            mui('#content1').pullRefresh().endPulldownToRefresh();
	            mui('#content1').pullRefresh().refresh(true);
        }
    
    
        function pullupRefresh1() {
			index1++;
			getBorrowList(user_id, index1);
            if (dataIndex >= index1) {
                getBorrowList(user_id, index1);
                /* 表示还可以加载 */
                mui('#content1').pullRefresh().endPullupToRefresh(false);
            } else {
                /* 不能加载了 */
                mui('#content1').pullRefresh().endPullupToRefresh(true);
            }
		}
		
        /* 第二屏 */
        function pullDownfresh2() {
            $("#content2 .mui-table-view").empty();
            index2 = 1;
				getBorrowList1(user_id,0,index2);
	            mui('#content2').pullRefresh().endPulldownToRefresh();
	            mui('#content2').pullRefresh().refresh(true);
            
        }
    
        function pullupRefresh2() {
			index2++;
            if (dataIndex >= index2) {
                getBorrowList1(user_id,0,index2);
                mui('#content2').pullRefresh().endPullupToRefresh(false);
            } else {
                /* 不能加载了 */
                mui('#content2').pullRefresh().endPullupToRefresh(true);
            }
        }
        
        /* 第三屏 */
        function pullDownfresh3() {
            $("#content3 .mui-table-view").empty();
            index3 = 1;
				getBorrowList1(user_id,1,index3);
	            mui('#content3').pullRefresh().endPulldownToRefresh();
	            mui('#content3').pullRefresh().refresh(true);
        }
    
        function pullupRefresh3() {
            index3++;
            getBorrowList1(user_id,1,index3);
            if (dataIndex >= index3) {
                /* 表示还可以加载 */
                mui('#content3').pullRefresh().endPullupToRefresh(false);
            } else {
                /* 不能加载了 */
                mui('#content3').pullRefresh().endPullupToRefresh(true);
            }
        }
        /* 第四屏 */
        function pullDownfresh4() {
            $("#content4 .mui-table-view").empty();
            index4 = 1;
				getBorrowList1(user_id,2,index4);
	            mui('#content4').pullRefresh().endPulldownToRefresh();
	            mui('#content4').pullRefresh().refresh(true);
        }
    
        function pullupRefresh4() {
            index4++;
            getBorrowList1(user_id,2,index4);
            if (dataIndex >= index4) {
                /* 表示还可以加载 */
                mui('#content4').pullRefresh().endPullupToRefresh(false);
            } else {
                /* 不能加载了 */
                mui('#content4').pullRefresh().endPullupToRefresh(true);
            }
        }
        /* 第五屏 */
        function pullDownfresh5() {
            $("#content5 .mui-table-view").empty();
            index5 = 1;
				getBorrowList2(user_id,index5);
	            mui('#content5').pullRefresh().endPulldownToRefresh();
	            mui('#content5').pullRefresh().refresh(true);
        }
    
        function pullupRefresh5() {
            index5++;
            getBorrowList2(user_id, index5);
            if (dataIndex >= index5) {
                /* 表示还可以加载 */
                mui('#content5').pullRefresh().endPullupToRefresh(false);
            } else {
                /* 不能加载了 */
                mui('#content5').pullRefresh().endPullupToRefresh(true);
            }
        }
// 我的竞拍交互
        function  getBorrowList(user_id,index){
            var data = {
                'user_id':  user_id,
                'currPage': index
			}
            setTimeout(function(){
                $.ajax({
                    url: configIp + "/mobileterminal/bid/myCustomerList",
                    type : 'get',
                    dataType: 'jsonp',
					data : data,
					crossDomain: true,
                    async: "false",
                    success:function(data){
						var wrap1_List =  template("wrap1_List", data);
						$('#borrowList1').append(wrap1_List);
						dataIndex = Math.ceil(data.data.totalCount / data.data.pageSize);
						
						//最高价购
	 				$(".wrap1 .top_price").on("tap",function(){
	 					customer_id = $(this).parents(".wrap1_List").attr("lang");
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
	 							if(data.code == '0'){
	 								//成功出价
	 								$(".mask4").show()
	 							}else{
	 								//余额不足
	 								$(".mask3").show()
	 							}
	 						}
	 					})	
	 				})
						
                    }
                })
            },1100)
        }

		var customer_id;
		var price;
		var totals1 = 0;
		var totals2 = 0;
		var totals3 = 0;
		var totals4 = 0;
		var arrs1 = [];
		var arrs2 = [];
		var arrs3 = [];
		var arrs4 = [];

		//按条件查询竞拍列表(全部，进行中，未开始)交互
        function getBorrowList1(user_id,state,index){
            var data = {
                'user_id':user_id,
                'state' : state,
                'currPage' : index
			}
            setTimeout(function(){
                $.ajax({
                    url: configIp + "/mobileterminal/bid/onTermsCustomerList",
                    type : 'get',
                    dataType: 'jsonp',
					data : data,
					crossDomain: true,
                    async: "false",
                    success:function(data){
                    	if(data.code == '0'){
                    		var wrap1_List1 =  template("wrap1_List1", data);
                    		$('#borrowList2').append(wrap1_List1);
	
                    		var wrap2_list2=  template("wrap2_list2", data);
                    		$('#borrowList3').append(wrap2_list2);
	
                    		var wrap2_list3=  template("wrap2_list3", data);
                    		$('#borrowList4').append(wrap2_list3);					
                    		dataIndex = Math.ceil(data.data.totalCount / data.data.pageSize);
                    		
                    		array1 = data.data.page;
							for(var index = 0; index < array1.length; index++) {
								const element1 = array1[index].can_call_time;
								var _indexs1 = index;
								var indexArr = [];
								if(element1) {
									indexArr.push(_indexs1)
									for(var index1 = 0; index1 < indexArr.length; index1++) {
										var indexCell = indexArr[index1];
										$.leftTime(element1, function(d) {
											if(d.status) {
												var $dateShow1 = $(".daojishi1" + indexCell);
												$dateShow1.find(".d").html(d.d);
												$dateShow1.find(".h").html(d.h);
												$dateShow1.find(".m").html(d.m);
												$dateShow1.find(".s").html(d.s);
												if(d.d == 0 & d.h == 0 & d.m == 0 & d.s == 0) {
													$(".daojishi1" + indexCell).parents("i").html("---");
													/* 当时间到了后改变改变颜色，改变文字，增加contact类名，移除contacted类名 */
													$(".daojishi1" + indexCell).parents(".wrap2_list").find(".contacted").css("backgroundColor", "#fa7e2c").text("联系他").attr('disabled', true).addClass("contact").removeClass("contacted");
													/* 后台需要的数据 */
													$.ajax({
														type: "get",
														url: configIp + "/mobileterminal/home/updateCustomerState",
														data: {
															"cid": $(".daojishi1" + indexCell).parents(".wrap2_list").attr("lang")
														},
														dataType: 'jsonp',
														crossDomain: true,
														async: "false",
														success: function(data) {
			
														}
													})
												}
											}
										});
									}
								}
							}
					        
			 		        //最高价购
			 		       	$("#borrowList2 .top_price").on("tap",function(){
			 		       		customer_id = $(this).parents(".wrap2_list").attr("lang");
			 		       		$.ajax({
			 		       			type: "get",
			 						url: configIp + "/mobileterminal/home/topPriceBuy",
			 						data: {
			 							"user_id": user_id,
			 							"customer_id":customer_id,
			 						},
			 						dataType: 'jsonp',
			 						crossDomain: true,
			 						async: "false",
			 						success: function(data){
			 							if(data.code == '0'){
			 								//成功出价
			 								$(".mask4").show()
			 							}else{
			 								//余额不足
			 								$(".mask3").show()
			 							}
										
			 						}
			 		       		})	
			 		        })
					        
			 		        //出价
			 		        $("#borrowList2 .offer").on("tap",function(){
			 		       		customer_id = $(this).parents(".wrap2_list").attr("lang");
			 		       		$.ajax({
			 		       			type: "get",
			 						url: configIp + "/mobileterminal/home/ifMayBid",
			 						data: {
			 							"user_id": user_id,
			 							"customer_id":customer_id,
			 						},
			 						dataType: 'jsonp',
			 						crossDomain: true,
			 						async: "false",
			 						success: function(data){
			 							//联系过客户	可以直接出价
			 							if(data.code == '0'){
			 								//滑块出现
			 								$(".mask2").show();
											
			 							}
			 							//未联系过客户请先联系客户或最高价购买
			 							if(data.code == '-4'){
			 								//暂无资格
			 								$('.mask1').show();
			 							}
			 						}
			 		       		})	
			 		        })
                    		
                    		array2 = data.data.page;
							for(var index = 0; index < array2.length; index++) {
								const element2= array2[index].can_call_time;
								var _indexs2 = index;
								var indexArr = [];
								if(element2) {
									indexArr.push(_indexs2)
									for(var index1 = 0; index1 < indexArr.length; index1++) {
										var indexCell = indexArr[index1];
										$.leftTime(element2, function(d) {
											if(d.status) {
												var $dateShow1 = $(".daojishi2" + indexCell);
												$dateShow1.find(".d").html(d.d);
												$dateShow1.find(".h").html(d.h);
												$dateShow1.find(".m").html(d.m);
												$dateShow1.find(".s").html(d.s);
												
												if(d.d == 0 & d.h == 0 & d.m == 0 & d.s == 0) {
													$(".daojishi2" + indexCell).parents("i").html("---");
													/* 当时间到了后改变改变颜色，改变文字，增加contact类名，移除contacted类名 */
													$(".daojishi2" + indexCell).parents(".wrap2_list").find(".contacted").css("backgroundColor", "#fa7e2c").text("联系他").attr('disabled', true).addClass("contact").removeClass("contacted");
													/* 后台需要的数据 */
													$.ajax({
														type: "get",
														url: configIp + "/mobileterminal/home/updateCustomerState",
														data: {
															"cid": $(".daojishi2" + indexCell).parents(".wrap2_list").attr("lang")
														},
														dataType: 'jsonp',
														crossDomain: true,
														async: "false",
														success: function(data) {
			
														}
													})
												}
											}
										});
									}
								}
							}
					        
					        
			 		        //最高价购
			 		       	$("#borrowList3 .top_price").on("tap",function(){
			 		       		customer_id = $(this).parents(".wrap2_list").attr("lang");
			 		       		$.ajax({
			 		       			type: "get",
			 						url: configIp + "/mobileterminal/home/topPriceBuy",
			 						data: {
			 							"user_id": user_id,
			 							"customer_id":customer_id,
			 						},
			 						dataType: 'jsonp',
			 						crossDomain: true,
			 						async: "false",
			 						success: function(data){
			 							if(data.code == '0'){
			 								//成功出价
			 								$(".mask4").show()
			 							}else{
			 								//余额不足
			 								$(".mask3").show()
			 							}
										
			 						}
			 		       		})	
			 		        })
					       	
					       	
			 		       	//出价
			 		        $("#borrowList3 .offer").on("tap",function(){
			 		       		customer_id = $(this).parents(".wrap2_list").attr("lang");
			 		       		$.ajax({
			 		       			type: "get",
			 						url: configIp + "/mobileterminal/home/ifMayBid",
			 						data: {
			 							"user_id": user_id,
			 							"customer_id":customer_id,
			 						},
			 						dataType: 'jsonp',
			 						crossDomain: true,
			 						async: "false",
			 						success: function(data){
			 							//联系过客户	可以直接出价
			 							if(data.code == '0'){
			 								//滑块出现
			 								$(".mask2").show();
											
			 							}
			 							//未联系过客户请先联系客户或最高价购买
			 							if(data.code == '-4'){
											//暂无资格
			 								$('.mask1').show();
			 							}
			 						}
					       		})	
			 		        })
                    		
                    		array3 = data.data.page;
							for(var index = 0; index < array3.length; index++) {
								const element3 = array3[index].can_call_time;
								var _indexs3 = index;
								var indexArr = [];
								if(element3) {
									indexArr.push(_indexs3)
									for(var index1 = 0; index1 < indexArr.length; index1++) {
										var indexCell = indexArr[index1];
										$.leftTime(element3, function(d) {
											if(d.status) {
												var $dateShow1 = $(".daojishi3" + indexCell);
												$dateShow1.find(".d").html(d.d);
												$dateShow1.find(".h").html(d.h);
												$dateShow1.find(".m").html(d.m);
												$dateShow1.find(".s").html(d.s);
												if(d.d == 0 & d.h == 0 & d.m == 0 & d.s == 0) {
													$(".daojishi3" + indexCell).parents("i").html("---");
													/* 当时间到了后改变改变颜色，改变文字，增加contact类名，移除contacted类名 */
													$(".daojishi3" + indexCell).parents(".wrap2_list").find(".contacted").css("backgroundColor", "#fa7e2c").text("联系他").attr('disabled', true).addClass("contact").removeClass("contacted");
													/* 后台需要的数据 */
													$.ajax({
														type: "get",
														url: configIp + "/mobileterminal/home/updateCustomerState",
														data: {
															"cid": $(".daojishi3" + indexCell).parents(".wrap2_list").attr("lang")
														},
														dataType: 'jsonp',
														crossDomain: true,
														async: "false",
														success: function(data) {
			
														}
													})
												}
											}
										});
									}
								}
							}
					        
			 		        //最高价购
			 		       	$("#borrowList4 .top_price").on("tap",function(){
			 		       		customer_id = $(this).parents(".wrap2_list").attr("lang");
			 		       		$.ajax({
			 		       			type: "get",
			 						url: configIp + "/mobileterminal/home/topPriceBuy",
			 						data: {
			 							"user_id": user_id,
			 							"customer_id":customer_id,
			 						},
			 						dataType: 'jsonp',
			 						crossDomain: true,
			 						async: "false",
			 						success: function(data){
			 							if(data.code == '0'){
			 								//成功出价
			 								$(".mask4").show()
			 							}else{
			 								//余额不足
			 								$(".mask3").show()
			 							}
										
			 						}
			 		       		})	
			 		        })
					        
			 		        //出价
			 		        $("#borrowList4 .offer").on("tap",function(){
			 		       		customer_id = $(this).parents(".wrap2_list").attr("lang");
			 		       		$.ajax({
			 		       			type: "get",
			 						url: configIp + "/mobileterminal/home/ifMayBid",
			 						data: {
			 							"user_id": user_id,
			 							"customer_id":customer_id,
			 						},
			 						dataType: 'jsonp',
			 						crossDomain: true,
			 						async: "false",
			 						success: function(data){
			 							//联系过客户	可以直接出价
			 							if(data.code == '0'){
			 								//滑块出现
			 								$(".mask2").show();
											
			 							}
			 							//未联系过客户请先联系客户或最高价购买
			 							if(data.code == '-4'){
			 								//暂无资格
			 								$('.mask1').show();
			 							}
			 						}
			 		       		})	
			 		        })
                    		
                    		//初始化
							$(".left").hide();
							$(".left input").attr("checked",false);
							$(".all").html("一键购买");
							$(".goumai").hide();
							$(".goumai i").html(0);
							arrs1 = [];
							arrs2 = [];
							arrs3 = [];
                    		
                    		
                    	}
						
                    }
                })
            },1100)
        }
        

//已联系竞拍列表交互
        function  getBorrowList2(user_id,index){
            var data = {
                'user_id' : user_id,
                'currPage' : index
            }
            setTimeout(function(){
                $.ajax({
                    url: configIp + "/mobileterminal/bid/relationCustomerList",
                    type : 'get',
                    dataType: 'jsonp',
					data : data,
					crossDomain: true,
                    async: "false",
                    success:function(data){
                    	if(data.code == '0'){
                    		var wrap2_list4=  template("wrap2_list4", data);
							$('#borrowList5').append(wrap2_list4);
							dataIndex = Math.ceil(data.data.totalCount / data.data.pageSize)
                    		
			 				//初始化
							$(".left").hide();
							$(".left input").attr("checked",false);
							$(".all").html("一键购买");
							$(".goumai").hide();
							$(".goumai i").html(0);
							arrs4 = [];
			 				
			 				
			 				array4 = data.data.page;
							for(var index = 0; index < array4.length; index++) {
								const element4 = array4[index].can_call_time;
								var _indexs4 = index;
								var indexArr = [];
								if(element4) {
									indexArr.push(_indexs4)
									for(var index1 = 0; index1 < indexArr.length; index1++) {
										var indexCell = indexArr[index1];
										$.leftTime(element4, function(d) {
											if(d.status) {
												var $dateShow1 = $(".daojishi4" + indexCell);
												$dateShow1.find(".d").html(d.d);
												$dateShow1.find(".h").html(d.h);
												$dateShow1.find(".m").html(d.m);
												$dateShow1.find(".s").html(d.s);
												if(d.d == 0 && d.h == 0 && d.m == 0 && d.s == 0) {
													$(".daojishi4" + indexCell).parents("i").html("---");
													/* 当时间到了后改变改变颜色，改变文字，增加contact类名，移除contacted类名 */
													$(".daojishi4" + indexCell).parents(".wrap2_list").find(".contacted").css("backgroundColor", "#fa7e2c").text("联系他").attr('disabled', true).addClass("contact").removeClass("contacted");
													/* 后台需要的数据 */
													$.ajax({
														type: "get",
														url: configIp + "/mobileterminal/home/updateCustomerState",
														data: {
															"cid": $(".daojishi4" + indexCell).parents(".wrap2_list").attr("lang")
														},
														dataType: 'jsonp',
														crossDomain: true,
														async: "false",
														success: function(data) {
			
														}
													})
												}
											}
										});
									}
								}
							}
					        
					        
			 		        //最高价购
			 		       	$("#borrowList5 .top_price").on("tap",function(){
			 		       		customer_id = $(this).parents(".wrap2_list").attr("lang");
			 		       		$.ajax({
			 		       			type: "get",
			 						url: configIp + "/mobileterminal/home/topPriceBuy",
			 						data: {
			 							"user_id": user_id,
			 							"customer_id":customer_id,
			 						},
			 						dataType: 'jsonp',
			 						crossDomain: true,
			 						async: "false",
			 						success: function(data){
			 							if(data.code == '0'){
			 								//成功出价
			 								$(".mask4").show()
			 							}else{
			 								//余额不足
			 								$(".mask3").show()
			 							}
										
			 						}
			 		       		})	
			 		        })
					       	
			 		        //出价
			 		        $("#borrowList5 .offer").on("tap",function(){
			 		       		customer_id = $(this).parents(".wrap2_list").attr("lang");
			 		       		$.ajax({
			 		       			type: "get",
			 						url: configIp + "/mobileterminal/home/ifMayBid",
			 						data: {
			 							"user_id": user_id,
			 							"customer_id":customer_id,
			 						},
			 						dataType: 'jsonp',
			 						crossDomain: true,
			 						async: "false",
			 						success: function(data){
			 							//联系过客户	可以直接出价
			 							if(data.code == '0'){
			 								//滑块出现
			 								$(".mask2").show();
			 							}
			 							//未联系过客户请先联系客户或最高价购买
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
            },1100)
        }

		
	$("body").on("click","#borrowList2 .iptSelect",function(){
		var nums1 = $(this).parents(".wrap2_list").find("i").html().substring(1)*1;
		if($(this).attr("checked")){
			arrs1.push($(this).parents(".wrap2_list").attr("lang"))
			totals1 += nums1;
		}else if(!($(this).attr("checked"))){
			arrs1.splice( arrs1.indexOf($(this).parents(".wrap2_list").attr("lang")), 1)
			totals1 -= nums1;
		}
		console.log(arrs1)
		$(".goumai .total i").html(totals1)
	})

	$("body").on("click","#borrowList3 .iptSelect",function(){
		var nums2 = $(this).parents(".wrap2_list").find("i").html().substring(1)*1;
		if($(this).attr("checked")){
			arrs2.push($(this).parents(".wrap2_list").attr("lang"))
			totals2 += nums2;
		}else if(!($(this).attr("checked"))){
			arrs2.splice( arrs2.indexOf($(this).parents(".wrap2_list").attr("lang")), 1)
			totals2 -= nums2;
		}
		console.log(arrs2)
		$(".goumai .total i").html(totals2)
	})

	$("body").on("click","#borrowList4 .iptSelect",function(){
		var nums3 = $(this).parents(".wrap2_list").find("i").html().substring(1)*1;
		if($(this).attr("checked")){
			arrs3.push($(this).parents(".wrap2_list").attr("lang"))
			totals3 += nums3;
		}else if(!($(this).attr("checked"))){
			arrs3.splice( arrs3.indexOf($(this).parents(".wrap2_list").attr("lang")), 1)
			totals3 -= nums3;
		}
		console.log(arrs3)
		$(".goumai .total i").html(totals3)
	})

	$("body").on("click","#borrowList5 .iptSelect",function(){
		var nums4 = $(this).parents(".wrap2_list").find("i").html().substring(1)*1;
		if($(this).attr("checked")){
			arrs4.push($(this).parents(".wrap2_list").attr("lang"))
			totals4 += nums4;
		}else if(!($(this).attr("checked"))){
			arrs4.splice( arrs4.indexOf($(this).parents(".wrap2_list").attr("lang")), 1)
			totals4 -= nums4;
		}
		console.log(arrs4)
		$(".goumai .total i").html(totals4)
	})
	
	
	//底部导航
	$(".nav div").on("tap", function () {
        var lang = $(this).attr("lang")
        toPage(lang);
    })
	
	//我的竞拍 切换
	$(".tops .s1").on("tap",function(){
		$(".tops span").css({"font-weight":"normal"});
		$(this).css({"font-weight":"bold"});
		$(".wrap1").show();
		$(".wrap2").hide();
		
		//全部客户初始化
		$(".navlist .s1").addClass("active").siblings("span").removeClass("active");
		$(".wrap2_con #fresh1").show().siblings().hide();
		
	})
	
	//全部客户	切换
	$(".tops .s2").on("tap",function(){
		$(".tops span").css({"font-weight":"normal"});
		$(this).css({"font-weight":"bold"});
		$(".wrap2").show();
		$(".wrap1").hide();
		
		//初始化
		$(".left").hide();
		$(".left input").attr("checked",false);
		$(".all").html("一键购买");
		$(".goumai").hide();
		$(".goumai i").html("0");
		arrs2 = [];
	})
	
	var codeNumber = 1;
	var flag11 = false;
	var flag22 = false;
	
	
	
	$('.tops span').on('tap',function(){
		codeNumber = $(this).data("codeNumber");
		$(".wrap" + codeNumber).removeClass("hide").siblings(".wap").addClass("hide");
		if (codeNumber == 1) {
			if(flag11 == false){
				flag11 = true;
				pullDownfresh1();
				setTimeout(function(){
					flag11 = false;
				},1200)
			}
		} else if (codeNumber == 2) {
			$(".navlist span").eq(0).addClass('active').siblings("span").removeClass("active");
			$(".content" + codeNumber).removeClass("hide").siblings(".content").addClass("hide");
			if(flag22 == false){
				flag22 = true;
				pullDownfresh2();
				setTimeout(function(){
					flag22 = false;
				},1200)
			}
		}
		console.log(codeNumber)
	})
	
	
	
	
	
	//全部客户4种状态切换		navlist
	var flag2 = false;
	var flag3 = false;
	var flag4 = false;
	var flag5 = false;
	$(".navlist span").on("tap",function(){
		var index = $(this).index();
		$(this).addClass('active').siblings("span").removeClass("active");
		codeNumber = $(this).data("codeNumber");
		
		if($(this).data("codeNumber") == 2){
			$("#content2").removeClass("hide").siblings(".content").addClass("hide");
			if(flag2 == false){
				flag2 = true;
				pullDownfresh2();
				setTimeout(function(){
					flag2 = false;
				},1200)
			}
		}
		else if($(this).data("codeNumber") == 3){
			$("#content3").removeClass("hide").siblings(".content").addClass("hide");
			if(flag3 == false){
				flag3 = true;
				pullDownfresh3();
				setTimeout(function(){
					flag3 = false;
				},1200)
			}
		}
		else if($(this).data("codeNumber") == 4){
			$("#content4").removeClass("hide").siblings(".content").addClass("hide");
			if(flag4 == false){
				flag4 = true;
				pullDownfresh4();
				setTimeout(function(){
					flag4 = false;
				},1200)
			}
		}
		else if($(this).data("codeNumber") == 5){
			$("#content5").removeClass("hide").siblings(".content").addClass("hide");
			if(flag5 == false){
				flag5 = true;
				pullDownfresh5();
				setTimeout(function(){
					flag5 = false;
				},1200)
			}
			
		}

		console.log(codeNumber)
		
		//初始化
		$(".left").hide();
		$(".left input").attr("checked",false);
		$(".all").html("一键购买");
		$(".goumai").hide();
		$(".goumai i").html(0);
		arrs2 = [];
	})
	
	var arr1 = ['一键购买','取消'];
	//全部客户(4个共用)		一键购买
	$(".all").on("tap",function(){
		//选中已联系
		console.log(codeNumber)
		if(codeNumber == codeNumber){
			//当为一键购买
			if($(this).html() == arr1[0]){
				//按钮变为	取消
				$(this).html(arr1[1]);
				//立即购买 显示
				$(".goumai").show();
				//合计清零
				$(".goumai i").html("0");
				totals1 = 0;
				totals2 = 0;
				totals3 = 0;
				totals4 = 0;
				//checkbox初始化
				$("#borrowList"+codeNumber+" .iptSelect").attr("checked",false);
				$("#borrowList"+codeNumber+" .s1").css({"width":"36vw"});
				$("#borrowList"+codeNumber+" .s2").css({"width":"80%"});
				$("#borrowList"+codeNumber+" .left").show();
			}else if($(this).html() == arr1[1]){
				$(this).html(arr1[0]);
				$("#borrowList"+codeNumber+" .s1").css({"width":"43vw"});
				$("#borrowList"+codeNumber+" .s2").css({"width":"100%"});
				$("#borrowList"+codeNumber+" .left").hide();
				$(".goumai").hide();
				//取消之后	存的订单id清空
				arrs1 = [];
				arrs2 = [];
				arrs3 = [];
				arrs4 = [];
			}
		}
	})
	

	
	//底部	立即购买
	$(".now_buy").on("tap",function(){
		$.ajax({
			type: "get",
			url:configIp + "/mobileterminal/home/priceBuyBatch",
			data: {
				"user_id": user_id,
				"customerIds": arrs2,
			},
			dataType: 'jsonp',
			crossDomain: true,
			async: "false",
			success: function(data){
				if(data.code == '0'){
					//成功购买
					$('.mask4').show()
				}else{
					//arrs2为订单id，当为空时，未选中
					if(arrs2 == ""){
						//未选中状态
						mui.alert("请选择订单！")
					}else{
						//余额不足
						$('.mask3').show()
					}
				}
			}
		})
	})
	
	
	//最高价购买
	$(".mask1 a").on("tap",function(){
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
				if(data.code == '0'){
					//成功出价
					$(".mask4").show();
					$(".mask1").hide();
				}else{
					//余额不足
					$(".mask3").show();
					$(".mask1").hide()
				}
			}
		})
	})
	//暂无资格  确认
	$(".mask1 .queding").on("tap",function(){
		$(".mask1").hide()
	})
	//提示  确认
	$(".mask5 .queding").on("tap",function(){
		$(".mask5").hide()
	})
	//选择出价 	取消
	$(".mask2 .quxiao").on("tap",function(){
		$(".mask2").hide()
	})
	
	//选择出价 确认
	$(".mask2 .queding").on("tap",function(){
		console.log(customer_id)
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
				console.log(data)
				//成功
				if(data.code == '0'){
					//滑块消失
					$(".mask2").hide();
					//成功出价 出现
					$(".mask4").show();
				//失败
				} else if(data.code == '-1'){
					//滑块消失
					$(".mask2").hide();
					//余额不足 出现
					$(".mask3").show();
				//竞拍人数已达限额
				}else{
					//滑块消失
					$(".mask2").hide();
					$(".mask6").show();
				}
			}
		})
	})
	
	
	//滑块监听
	$('#block-range').on("input",function(){
		$("#block-range-val").html($(this).val())
	})
	//余额不足 	取消
	$(".mask3 .quxiao").on("tap",function(){
		$(".mask3").hide()
	})
	//余额不足 	充值
	$(".mask3 .queding").on("tap",function(){
		toPage('../pay/pay.html')
	})
	//成功出价 	确认
	$(".mask4 .queding").on("tap",function(){
		$(".mask4").hide();
		window.location.reload();
	})
	
	$(".mask6 .queding").on("tap",function(){
		$(".mask6").hide();
	})
	
	
	
	
	
})