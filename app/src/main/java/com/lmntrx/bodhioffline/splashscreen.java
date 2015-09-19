package com.lmntrx.bodhioffline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    ProgressBar mProgressBar;
    Context Con;
    Activity splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        Con=this;
        splash=this;
        mProgressBar=(ProgressBar)findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.VISIBLE);

        splashLayout=(RelativeLayout)findViewById(R.id.splash);

        //startCounting();

        /*splashLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                splash.finish();
            }
        });*/

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                splash.finish();
            }
        }, 9000);

    }
}

