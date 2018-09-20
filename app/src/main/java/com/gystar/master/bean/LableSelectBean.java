package com.gystar.master.bean;

import com.utils.gyymz.http.callback.DataBeanCallBack;

public class LableSelectBean extends DataBeanCallBack{

    private String lableName;
    private boolean isChecked;

    public LableSelectBean(String lableName) {
        this.lableName = lableName;
    }

    public String getLableName() {
        return lableName;
    }

    public void setLableName(String lableName) {
        this.lableName = lableName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
