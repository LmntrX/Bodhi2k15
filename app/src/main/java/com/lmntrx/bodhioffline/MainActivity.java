package com.lmntrx.bodhioffline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseAnalytics;


public class MainActivity extends Activity {

    //WebView
    private WebView mWebview;

    //Site Url
    String fileURL = "file:///android_asset/index";
    String mURL = "http://bodhiofficial.in/";

    private SensorManager mSensorManager;

    private ShakeEventListener mSensorListener;

    //Context Variable
    public static Context CON;

    boolean isConnected;


    Vibrator mVibrator;

    final Activity activity = this;

    boolean isInitialized;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CON = this;


        if(!isInitialized) {
            try {
                Parse.initialize(this, "ofmbO4plk6IVxuJMpDMc39ge1ROI9x0x9JjvsaRk", "3fte83olSs5lRSi026aVEUiXSje2EIjlri5ffDY3");
                isInitialized=true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (savedInstanceState == null) {
            Intent intent = new Intent(this, splashscreen.class);
            startActivity(intent);
        }


        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();

        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

            public void onShake() {
                if(!isConnected){
                    if (isNetworkAvailable(CON)){
                        mVibrator.vibrate(200);
                        Toast.makeText(CON, "Refreshing", Toast.LENGTH_SHORT).show();
                        activity.recreate();
                    }else {
                        mVibrator.vibrate(200);
                        Toast.makeText(CON, "Connect to a network and shake to go online", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(CON, "You are in online mode already", Toast.LENGTH_SHORT).show();
                    mVibrator.vibrate(200);
                }
            }
        });

        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        mWebview = new WebView(this);



        //Executing Async task
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    isConnected = new NetworkStatusCheck().execute().get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.run();

        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(true);
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        }

        mWebview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });
        if (isConnected) {
            mWebview.loadUrl(mURL);
            //Toast.makeText(CON,"App Powered By LmntrX\n    App in Online Mode",Toast.LENGTH_LONG).show();
        } else {
            mWebview.loadUrl(fileURL);
            if (savedInstanceState == null) {
                Toast.makeText(CON, "  App in Offline Mode\nContent maybe outdated", Toast.LENGTH_LONG).show();
                mVibrator.vibrate(200);
            }
        }

        setContentView(mWebview);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);


    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
