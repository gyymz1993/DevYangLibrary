package three.pay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import three.login.INFO;
import three.pay.bean.PayInfo;
import three.pay.instance.AliPayInstance;
import three.pay.instance.PayInstance;
import three.pay.instance.WxPayInstance;
import three.pay.listener.PayListener;


/**
 * Created by onetouch on 2017/11/22.
 */

public class ThirdPayUtils {

    private static ThirdPayUtils thirdPayUtils;
    public static final int TYPE = 699;
    private Activity activity;
    private int mPlatform;

    public PayInstance payInstance;
    private PayListener mPayListener;
    private PayInfo mPayInfo;

    public static ThirdPayUtils initialize(Activity context) {
        if (thirdPayUtils == null) {
            synchronized (ThirdPayUtils.class) {
                thirdPayUtils = new ThirdPayUtils(context);
            }
        }
        return thirdPayUtils;
    }


    private ThirdPayUtils(Activity activity) {
        this.activity = activity;
    }


    public void pay(@PayPlatform.Platform int platform, PayInfo payInfo, PayListener listener) {
        this.mPayInfo = payInfo;
        pay(activity, platform, listener);
    }

    private void pay(Context context, @PayPlatform.Platform int platform, PayListener listener) {
        mPlatform = platform;
        mPayListener = new PayListenerProxy(listener);
        action();
    }


    private void action() {
        switch (mPlatform) {
            case PayPlatform.WxPay:
                payInstance = new WxPayInstance(activity, mPayInfo, mPayListener);
                break;
            case PayPlatform.AliPay:
                payInstance = new AliPayInstance(activity, mPayInfo, mPayListener);
                break;
            default:
                mPayListener.payFailure(new Exception(INFO.UNKNOW_PLATFORM));
                activity.finish();
        }
        payInstance.doPay(activity, mPayListener, mPayInfo);
    }

    public void handleResult(int requestCode, int resultCode, Intent data) {
        if (payInstance != null) {
            payInstance.handleResult(requestCode, resultCode, data);
        }
    }


    private void recycle() {
        payInstance = null;
        mPayListener = null;
        mPlatform = 0;
    }


    public class PayListenerProxy implements PayListener {


        private PayListener mListener;

        public PayListenerProxy(PayListener listener) {
            mListener = listener;
        }

        @Override
        public void paySuccess() {
            mListener.paySuccess();
            recycle();
        }

        @Override
        public void payFailure(Exception e) {
            mListener.payFailure(e);
            recycle();
        }

        @Override
        public void userCancel() {
            mListener.userCancel();
            recycle();
        }
    }
}
