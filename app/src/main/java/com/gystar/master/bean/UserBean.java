package com.gystar.master.bean;

import com.utils.gyymz.http.callback.DataBeanCallBack;

public class UserBean extends DataBeanCallBack<UserBean.DataBean> {

    // private "state":0,"ID":22
    public static class DataBean {
        /**
         * ID : 17
         * state : 0
         */

        /**
         * 用户ID和  是否是新客户
         */
        public int ID;
        public int state;

       // "id":17,"nike_name":"111","phone":"13177008851"}}

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

    }
}
