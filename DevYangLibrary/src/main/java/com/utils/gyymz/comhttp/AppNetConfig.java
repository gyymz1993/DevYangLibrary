package com.utils.gyymz.comhttp;

/**
 * Created by Administrator on 2018/3/20.
 */

public class AppNetConfig {

    private static AppNetConfig appNetConfig = new AppNetConfig();

    public static AppNetConfig getInstance() {
        return appNetConfig;
    }

    private AppNetConfig() {
    }

    private Builder mbuider;

    public Builder getMbuider() {
        return mbuider;
    }

    public void setBuider(Builder buider) {
        mbuider = buider;
    }


    public static class Builder {
        private String baseUrl;

        public String getBaseUrl() {
            return baseUrl;
        }

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }


    }

}
