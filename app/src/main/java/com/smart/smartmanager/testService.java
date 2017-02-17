package com.smart.smartmanager;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Admin on 10/20/2016.
 */
public class testService extends IntentService {
    private static final String TAG = "testService";


    public testService(){super(TAG);}

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, ">>>onCreate()");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG,"handle intent called");
    }
}
