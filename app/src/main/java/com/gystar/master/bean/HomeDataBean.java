package com.gystar.master.bean;

import com.utils.gyymz.http.callback.DataBeanCallBack;

import java.util.List;

public class HomeDataBean extends DataBeanCallBack<HomeDataBean.DataBean> {

    public static class DataBean {
        /**
         * id : 9
         * call_customer_num : 0
         * nike_name : 测试6
         * phone : 15090742405
         * success_convert_rate : 0%
         * bid_convert_rate : 0%
         * bid_success_num : 0
         * attend_bid_num : 0
         * customers : [{"id":57,"can_call_time":"","phone":"15172398797","price":1000,"receive_call_status":0,"shelf_time":"2018/08/08","area_name":"武汉市","name":"魏胜滨"},{"id":56,"can_call_time":"","phone":"13080610491","price":1000,"receive_call_status":0,"shelf_time":"2018/08/08","area_name":"武汉市","name":"范振龙"},{"id":55,"can_call_time":"","phone":"13197300247","price":1000,"receive_call_status":0,"shelf_time":"2018/08/08","area_name":"武汉市","name":"杨剑雄"},{"id":52,"can_call_time":"","phone":"13667130077","price":1000,"receive_call_status":0,"shelf_time":"2018/08/08","area_name":"武汉市","name":"向友军"},{"id":53,"can_call_time":"","phone":"15377687870","price":1000,"receive_call_status":0,"shelf_time":"2018/08/08","area_name":"武汉市","name":"缪斯"},{"id":54,"can_call_time":"","phone":"18772811315","price":1000,"receive_call_status":0,"shelf_time":"2018/08/08","area_name":"武汉市","name":"孔新慧"}]
         * remainder_call_time : 2400
         */

        private int id;
        private int call_customer_num;
        private String nike_name;
        private String phone;
        private String success_convert_rate;
        private String bid_convert_rate;
        private int bid_success_num;
        private int attend_bid_num;
        private int remainder_call_time;
        private List<CustomersBean> customers;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCall_customer_num() {
            return call_customer_num;
        }

        public void setCall_customer_num(int call_customer_num) {
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

        public String getSuccess_convert_rate() {
            return success_convert_rate;
        }

        public void setSuccess_convert_rate(String success_convert_rate) {
            this.success_convert_rate = success_convert_rate;
        }

        public String getBid_convert_rate() {
            return bid_convert_rate;
        }

        public void setBid_convert_rate(String bid_convert_rate) {
            this.bid_convert_rate = bid_convert_rate;
        }

        public int getBid_success_num() {
            return bid_success_num;
        }

        public void setBid_success_num(int bid_success_num) {
            this.bid_success_num = bid_success_num;
        }

        public int getAttend_bid_num() {
            return attend_bid_num;
        }

        public void setAttend_bid_num(int attend_bid_num) {
            this.attend_bid_num = attend_bid_num;
        }

        public int getRemainder_call_time() {
            return remainder_call_time;
        }

        public void setRemainder_call_time(int remainder_call_time) {
            this.remainder_call_time = remainder_call_time;
        }

        public List<CustomersBean> getCustomers() {
            return customers;
        }

        public void setCustomers(List<CustomersBean> customers) {
            this.customers = customers;
        }

        public static class CustomersBean {
            /**
             * id : 57
             * can_call_time :
             * phone : 15172398797
             * price : 1000
             * receive_call_status : 0
             * shelf_time : 2018/08/08
             * area_name : 武汉市
             * name : 魏胜滨
             */

            private int id;
            private String can_call_time;
            private String phone;
            private int price;
            private int receive_call_status;
            private String shelf_time;
            private String area_name;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCan_call_time() {
                return can_call_time;
            }

            public void setCan_call_time(String can_call_time) {
                this.can_call_time = can_call_time;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getReceive_call_status() {
                return receive_call_status;
            }

            public void setReceive_call_status(int receive_call_status) {
                this.receive_call_status = receive_call_status;
            }

            public String getShelf_time() {
                return shelf_time;
            }

            public void setShelf_time(String shelf_time) {
                this.shelf_time = shelf_time;
            }

            public String getArea_name() {
                return area_name;
            }

            public void setArea_name(String area_name) {
                this.area_name = area_name;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
