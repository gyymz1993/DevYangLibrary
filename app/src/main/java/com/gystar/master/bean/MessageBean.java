package com.gystar.master.bean;

import com.utils.gyymz.http.callback.DataBeanCallBack;

import java.util.List;

public class MessageBean extends DataBeanCallBack<List<MessageBean.DataBean>> {

    public static class DataBean {
        /**
         * id : 310100
         * name : 上海市
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
