package com.gystar.master.bean;

import com.utils.gyymz.http.callback.DataBeanCallBack;

public class ByconditionBean extends DataBeanCallBack<ByconditionBean.DataBean> {

    public static class DataBean {
        /**
         * id : 17
         * nike_name : 111
         * phone : 13177008851
         */

        private int id;
        private String nike_name;
        private String phone;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }
}
