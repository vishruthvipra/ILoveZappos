package com.example.vishruthkrishnaprasad.ilovezappos;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.view.Window;
import android.widget.Toast;

import com.example.vishruthkrishnaprasad.ilovezappos.databinding.SplashBinding;

/**
 * Created by vishruthkrishnaprasad on 31/1/17.
 */

public class SplashScreen extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {

    Boolean isInternetPresent = false;
    ConnectionDetector cd;
    Intent intent;
    SplashBinding splashBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        splashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);

        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        int SPLASH_DISPLAY_LENGTH = 2000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isInternetPresent)
                    Toast.makeText(SplashScreen.this, "Not connected to Internet!!", Toast.LENGTH_LONG).show();
                intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}


