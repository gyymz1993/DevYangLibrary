package vaxsoft.com.vaxphone.MainAPP;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import vaxsoft.com.vaxphone.AccountLogin.AccountLoginActivity;
import vaxsoft.com.vaxphone.MainTab.MainTabActivity;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class SplashActivity extends AppCompatActivity
{
    private static final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    public void onCreate(Bundle icicle)
    {
        VaxPhoneSIP.StartService();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent objIntent;

                if (VaxPhoneSIP.IsOnline())
                    objIntent = new Intent(SplashActivity.this, MainTabActivity.class);
                else
                    objIntent = new Intent(SplashActivity.this, AccountLoginActivity.class);

                startActivity(objIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
