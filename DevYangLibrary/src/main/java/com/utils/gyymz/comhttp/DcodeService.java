package com.utils.gyymz.comhttp;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.util.Map;

import rx.Observable;

/**
 * @version 需要加密目前这种方法可行
 * @author: gyymz1993
 * 创建时间：2017/5/3 22:46
 **/
public class DcodeService {
    private static Context mContext;
    public static void initialize(Context context) {
        mContext = context;
    }

    private static ApiService getApiService() {
        if (mContext == null) {
            throw new NullPointerException("请先初始化 initialize");
        }
        return AppClient.getApiService(mContext);
    }

    /**
     * get网络请求入口  数据封装到url
     *
     * */
    public static Observable<String> getServiceData(String baseUrl,Map map) {
        if (TextUtils.isEmpty(baseUrl)) {
            throw new NullPointerException("请设置BaseUrl");
        }
        /**
         * 此处用post拼参数
         *  UrlUtils.spliceGetUrl(baseUrl, map);
          */
        String url=UrlUtils.encodesParameters(baseUrl, map);
        Log.e("UrlUtils----post>","没有加密的URL  post:"+baseUrl+url);
        return getApiService().getData(url);
    }

    /**
     *  post网络请求入口  数据封装到url
     * */
    public static Observable<String> postServiceData(String baseUrl,Map map) {
        if (TextUtils.isEmpty(baseUrl)) {
            throw new NullPointerException("请设置BaseUrl");
        }
        String url=UrlUtils.encodesParameters(baseUrl, map);
       // Log.e("UrlUtils----post>","没有加密的URL  post:"+url);
        return getApiService().postData(url);
    }

    /**
     *  post网络请求入口   表单方式请求
     * */
    public static Observable<String> postServiceDataForBody(String baseUrl,Map map) {
        if (TextUtils.isEmpty(baseUrl)) {
            throw new NullPointerException("请设置BaseUrl");
        }
        String url=UrlUtils.encodesParameters(baseUrl, map);
        //Log.e("UrlUtils----post>","没有加密的URL  post:"+url);
        return getApiService().postDataforBody(baseUrl,map);
    }


}
