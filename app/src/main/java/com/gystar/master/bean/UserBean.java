package com.gystar.master.bean;

import com.utils.gyymz.http.callback.DataBeanCallBack;

public class UserBean extends DataBeanCallBack {
   // private "state":0,"ID":22

    /**
     * 用户ID和  是否是新客户
     */
    public String ID;
    public String state;


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "ID='" + ID + '\'' +
                ", state='" + state + '\'' +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
