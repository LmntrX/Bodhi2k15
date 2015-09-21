package com.lmntrx.bodhioffline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.lmntrx.bodhioffline.R;

import java.util.Timer;
import java.util.TimerTask;

public class splashscreen extends Activity {

    private RelativeLayout splashLayout;
    Context Con;
    Activity splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Con=this;
        splash=this;

        splashLayout=(RelativeLayout)findViewById(R.id.splash);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                splash.finish();
            }
        }, 9000);

    }
}

