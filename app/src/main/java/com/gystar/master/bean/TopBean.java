package com.gystar.master.bean;

import com.utils.gyymz.http.callback.DataBeanCallBack;

import java.util.List;

public class TopBean extends DataBeanCallBack<List<TopBean.DataBean>> {

    public static class DataBean {
        /**
         * id : 1
         * duration : 600
         * price : 1
         * discount : 9.5折
         */

        private int id;
        private int duration;
        private int price;
        private String discount;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }
    }
}

