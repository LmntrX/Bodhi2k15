package com.lmntrx.bodhioffline;

import android.app.Activity;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.lmntrx.bodhioffline.R;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class splashscreen extends Activity {

    private RelativeLayout splashLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);


        //----------------------------------------------------------------------------------------
        // Timered Splash Screen

        new Timer().schedule(new TimerTask() {
            public void run() {
                splashscreen.this.runOnUiThread(new Runnable() {
                    public void run() {
                        startActivity(new Intent(splashscreen.this, MainActivity.class));
                    }
                });
            }
        }, 500);  // Change this value to change timer


        //----------------------------------------------------------------------------------------

        //----------------------------------------------------------------------------------------

       /*   FOR ONCLICK SPLASH SCREEN

        splashLayout=(RelativeLayout)findViewById(R.id.splash);

        splashLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class) ;
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

        }

        });

        */
        //----------------------------------------------------------------------------------------

    }

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement


                return super.onOptionsItemSelected(item);
            }
}

