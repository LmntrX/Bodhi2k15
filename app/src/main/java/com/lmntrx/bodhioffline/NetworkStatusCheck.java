package com.lmntrx.bodhioffline;

/**
 * Created by livin on 19/9/15.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkStatusCheck extends AsyncTask<Void, Void, Boolean> {

    //Self Documented :P
    boolean result=false;
    Context CON;

    @Override
    protected Boolean doInBackground(Void... params) {
        CON=MainActivity.CON;

        if (isNetworkAvailable(CON)) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (
                        new URL("http://www.google.com").openConnection()); //LmntrX :D
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(800);
                urlc.connect();
                result = (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                Log.e("No Connection", "Error checking internet connection", e);
                result=false;
            }
        } else {
            result = false;
        }

        return result;
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
