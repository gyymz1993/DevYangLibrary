package vaxsoft.com.vaxphone.MainAPP;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gystar.master.Config.netApi.AppConfig;
import com.utils.gyymz.BaseApplicationCompat;
import com.utils.gyymz.comhttp.AppNetConfig;
import com.utils.gyymz.http.subscriber.ApiFactory;
import com.tencent.smtt.sdk.QbSdk;
import com.utils.gyymz.utils.SpUtils;
import com.utils.gyymz.utils.T_;


public class VaxPhoneAPP extends Application {
    private static Context m_objContext = null;

    public void onCreate() {
        super.onCreate();
        VaxPhoneAPP.m_objContext = getApplicationContext();
        BaseApplicationCompat.instance().initialize(this);
        initNetWork();

    }

    QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
        @Override
        public void onViewInitFinished(boolean arg0) {
            //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
            Log.d("app", " onViewInitFinished is " + arg0);
        }

        @Override
        public void onCoreInitFinished() {
        }
    };

    public static Context getAppContext() {
        return VaxPhoneAPP.m_objContext;
    }

    public void initNetWork() {
        registerActivityLifecycleCallbacks(new SwitchBackgroundCallbacks());
        ApiFactory.getFactory().add(AppConfig.HTTP_ENCRYPT_BASE);
        AppNetConfig.getInstance().setBuider(new AppNetConfig.Builder()
                .setBaseUrl(AppConfig.HTTP_ENCRYPT_BASE));
    }


    public static String getUserId() {
        String userId = SpUtils.getInstance().getString(AppConfig.USER_ID);
        if (userId == null) {
            try {
                T_.showToastReal("你还没有登陆哟");
                throw new NullPointerException("你还没有登陆哦");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userId;
    }



    private class SwitchBackgroundCallbacks implements Application.ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
        }
    }


}
