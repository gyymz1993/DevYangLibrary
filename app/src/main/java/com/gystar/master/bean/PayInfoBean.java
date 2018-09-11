package com.gystar.master.bean;

/**
 * Created by onetouch on 2017/11/23.
 */

public class PayInfoBean {


    /**
     * orderFee : 1
     * paymentPlatform : 10
     * wechat : {"sign":"782ED9B7CDA551EAA1572062D9B4867B","nonceStr":"S7lhkxdyXTR5tU9wWSr1gwMnlBIhucIm","timeStamp":"1511427196","prepayId":"wx2017112316571073a0e07a8a0136840720"}
     * orderId : 24
     * orderNo : 6RL1501XQ3517
     */
    private int orderFee;
    private int paymentPlatform;
    private WechatBean wechat;
    private int orderId;
    private String orderNo;
    private AlipayBean alipay;

    public AlipayBean getAlipay() {
        return alipay;
    }

    public void setAlipay(AlipayBean alipay) {
        this.alipay = alipay;
    }

    public int getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(int orderFee) {
        this.orderFee = orderFee;
    }

    public int getPaymentPlatform() {
        return paymentPlatform;
    }

    public void setPaymentPlatform(int paymentPlatform) {
        this.paymentPlatform = paymentPlatform;
    }

    public WechatBean getWechat() {
        return wechat;
    }

    public void setWechat(WechatBean wechat) {
        this.wechat = wechat;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public static class AlipayBean {
        /**
         * orderStr : app_id=2018022402264996&biz_content=%7B%22body%22%3A%22%E4%BA%A4%E6%98%93%E6%8F%8F%E8%BF%B0%22%2C%22subject%22%3A%22%E4%B8%80%E9%94%AE%E7%A7%80%E6%B5%8B%E8%AF%95%E8%AE%A2%E5%8D%95%22%2C%22out_trade_no%22%3A%221523931796606%22%2C%22timeout_express%22%3A%221h%22%2C%22total_amount%22%3A%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22goods_type%22%3A%220%22%7D&charset=utf-8&format=JSON&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fwww.yijianxiu.com%2F&sign_type=RSA2&timestamp=2018-04-17%2010%3A23%3A16&version=1.0&sign=YIl3tXVYNDgx052%2FKnKGRchx5X14Mj%2B6hMRUXAuHs1msG%2Bu16HLeVErEj3cZ0HB%2FLitfV6S4lZi9IZ%2F63Q8edrxlRtzXelUnWD53rGbMBMrUcjsgQ05dHHojXCRs5QDjvxxIbafD%2FbFo802n%2FhU1jsMbHF6N%2FYqy%2FRQ4gIyDWJJFxPYPKBDw%2BICQwyJbqz1V2Gkn1OzqwbSYsOswUcZIIpTlGEgjiO5jH8QYzsJSLx4Gb5SVVdzzsdkGbq25c%2Bs2albKWH4qHLy6jjPY%2BCAulKR%2BxKHH7Beg%2B0XJfR1ozGN%2BIKEfMp4T8IZ5GzdDAcnyft%2Bz%2BsS4Y6pYlgI7iqjhCA%3D%3D
         */

        private String orderStr;

        public String getOrderStr() {
            return orderStr;
        }

        public void setOrderStr(String orderStr) {
            this.orderStr = orderStr;
        }
    }

    public static class WechatBean {
        /**
         * sign : 782ED9B7CDA551EAA1572062D9B4867B
         * nonceStr : S7lhkxdyXTR5tU9wWSr1gwMnlBIhucIm
         * timeStamp : 1511427196
         * prepayId : wx2017112316571073a0e07a8a0136840720
         */

        private String sign;
        private String nonceStr;
        private String timeStamp;
        private String prepayId;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getPrepayId() {
            return prepayId;
        }

        public void setPrepayId(String prepayId) {
            this.prepayId = prepayId;
        }
    }
}
