$(function(){
    var orderNum1 = localStorage.getItem('orderNum1')
    var orderNum2 = localStorage.getItem('orderNum2')
    var duration = localStorage.getItem('duration')
    var zhifu= getUrlParam("zhifu")
    if(zhifu == null){
        $('.p1 span').html(duration)
        $('.p2 span').html(orderNum1)
    }else{
        $('.p1 span').html(duration)
        $('.p2 span').html(orderNum2)
    }
})