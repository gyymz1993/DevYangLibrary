package com.gystar.master.bean;

import com.utils.gyymz.http.callback.DataBeanCallBack;

public class BeginrechargeBean extends DataBeanCallBack<BeginrechargeBean.DataBean> {

    public static class DataBean {
        /**
         * orderNum : GYSTAR000020180831143549DV
         * payParams : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017110109658848&biz_content=%7B%22out_trade_no%22%3A%22GYSTAR000020180831143549DV%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%221.0%22%2C%22subject%22%3A%22GYSTAR_APP_ORDER%22%2C%22body%22%3A%22%E5%85%89%E9%93%B6%E7%BD%91%E5%85%85%E5%80%BC%E8%AF%9D%E8%B4%B9%E6%94%AF%E4%BB%98%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F59.175.150.29%3A9004%2Ffront%2Fhome%2FalipayGrabOrderCallBack&sign=YetzoZtvRDhQVSJl8BS8DSpOwUzrL1frCnLYtjzwXwKR5SebEqmmC%2BrepW8AZhQm7TD3AKz7b%2B37A61WmGA7aCR5XUEBbMayqFkQcDuQqThV%2FTaVP8oGKv%2BLXDqpkz%2BkOLPCEGyG6giUTWNeswWIbEuA586lpoPpWf1FfO0gDKkYhdn7BjG4GTK9KI4ooTMvGWh4JCXUF7F%2B78uqCdckDmKP6ayqlrYYto9PeNCyj0GtIhQg9YUEgw6mHD715etT%2BaWRIJbCVI9CB63oU9HpMqNx67lOr3rN3rjTnj0CKdjvsj2MrdivEvF76%2FWoLkjKKJ0jEU9qoaGMHixo%2FFlPaQ%3D%3D&sign_type=RSA2&timestamp=2018-08-31+14%3A35%3A49&version=1.0&sign=YetzoZtvRDhQVSJl8BS8DSpOwUzrL1frCnLYtjzwXwKR5SebEqmmC%2BrepW8AZhQm7TD3AKz7b%2B37A61WmGA7aCR5XUEBbMayqFkQcDuQqThV%2FTaVP8oGKv%2BLXDqpkz%2BkOLPCEGyG6giUTWNeswWIbEuA586lpoPpWf1FfO0gDKkYhdn7BjG4GTK9KI4ooTMvGWh4JCXUF7F%2B78uqCdckDmKP6ayqlrYYto9PeNCyj0GtIhQg9YUEgw6mHD715etT%2BaWRIJbCVI9CB63oU9HpMqNx67lOr3rN3rjTnj0CKdjvsj2MrdivEvF76%2FWoLkjKKJ0jEU9qoaGMHixo%2FFlPaQ%3D%3D
         */

        private String orderNum;
        private String payParams;

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public String getPayParams() {
            return payParams;
        }

        public void setPayParams(String payParams) {
            this.payParams = payParams;
        }
    }
}
