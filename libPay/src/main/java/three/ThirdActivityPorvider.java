package three;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import three.login.ThirdLoginUtils;

/**
 * @author onetouch
 * @date 2017/11/21
 */

public class ThirdActivityPorvider extends AppCompatActivity {

    private int mType;

    private boolean isNew;

    private static final String TYPE = "share_activity_type";

    public static Intent newInstance(Context context, int type) {
        Intent intent = new Intent(context, ThirdActivityPorvider.class);
        if (context instanceof Application) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra(TYPE, type);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isNew = true;

        // init data
        mType = getIntent().getIntExtra(TYPE, 0);
        if (mType == ThirdLoginUtils.TYPE) {
            // 登录
            ThirdLoginUtils.initialize().action(this);
        } else {
            Log.e("onCreate", getIntent().toString());
            // handle 微信回调
            ThirdLoginUtils.initialize().handleResult(-1, -1, getIntent());
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNew) {
            isNew = false;
        } else {
            finish();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 处理回调
        if (mType == ThirdLoginUtils.TYPE) {
            ThirdLoginUtils.initialize().handleResult(0, 0, intent);
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 处理回调
        if (mType == ThirdLoginUtils.TYPE) {
            ThirdLoginUtils.initialize().handleResult(requestCode, resultCode, data);
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
