package com.zinc.example.androidlab.service_demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Author: zhao zhongxin
 * Create: 2019/11/15
 * Describe:
 */
public class CustomService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("zzx", "onCreate: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("zzx", "onDestroy: ");
    }


}
