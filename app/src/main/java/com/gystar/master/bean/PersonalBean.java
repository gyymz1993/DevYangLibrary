package com.gystar.master.bean;

import com.utils.gyymz.http.callback.DataBeanCallBack;

public class PersonalBean extends DataBeanCallBack<PersonalBean.DataBean> {


    public static class DataBean {
        /**
         * id : 9
         * call_customer_num : 0
         * nike_name : 测试6
         * phone : 15090742405
         * call_time_average : 0
         * call_time_num : 0
         * call_customer_todayNum : 0
         * remainder_call_time : 2400
         */

        private String id;
        private String call_customer_num;
        private String nike_name;
        private String phone;
        private String call_time_average;
        private String call_time_num;
        private String call_customer_todayNum;
        private int remainder_call_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCall_customer_num() {
            return call_customer_num;
        }

        public void setCall_customer_num(String call_customer_num) {
            this.call_customer_num = call_customer_num;
        }

        public String getNike_name() {
            return nike_name;
        }

        public void setNike_name(String nike_name) {
            this.nike_name = nike_name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCall_time_average() {
            return call_time_average;
        }

        public void setCall_time_average(String call_time_average) {
            this.call_time_average = call_time_average;
        }

        public String getCall_time_num() {
            return call_time_num;
        }

        public void setCall_time_num(String call_time_num) {
            this.call_time_num = call_time_num;
        }

        public String getCall_customer_todayNum() {
            return call_customer_todayNum;
        }

        public void setCall_customer_todayNum(String call_customer_todayNum) {
            this.call_customer_todayNum = call_customer_todayNum;
        }

        public int getRemainder_call_time() {
            return remainder_call_time;
        }

        public void setRemainder_call_time(int remainder_call_time) {
            this.remainder_call_time = remainder_call_time;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", call_customer_num='" + call_customer_num + '\'' +
                    ", nike_name='" + nike_name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", call_time_average='" + call_time_average + '\'' +
                    ", call_time_num='" + call_time_num + '\'' +
                    ", call_customer_todayNum='" + call_customer_todayNum + '\'' +
                    ", remainder_call_time=" + remainder_call_time +
                    '}';
        }
    }


}
