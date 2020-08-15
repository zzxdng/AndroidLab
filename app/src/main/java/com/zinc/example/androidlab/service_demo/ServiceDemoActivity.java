package com.zinc.example.androidlab.service_demo;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jakewharton.rxbinding.view.RxView;
import com.zinc.example.androidlab.R;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

public class ServiceDemoActivity extends AppCompatActivity {

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);
        initView();
    }

    private void initView() {
        RxView.clicks(findViewById(R.id.btn_create_service_id))
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void o) {
                        startService();
                    }
                });

        RxView.clicks(findViewById(R.id.btn_destroy_service_id))
                .throttleFirst( 500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        destroyService();
                    }
                });
    }

    private void destroyService() {
        if(null == mIntent){
            return;
        }
        stopService(mIntent);
    }

    private void startService() {
        mIntent = new Intent();
        mIntent.setPackage("com.zinc.example.androidlab");
        mIntent.setAction("com.zinc.example.CustomService");
        //显示调用
//        ComponentName componentName = startService(new Intent(this, CustomService.class));
        //隐式调用
        ComponentName componentName = startService(mIntent);
        if (null == componentName){
            Log.i("zzx", "startService: componentName is null!");
        }else {
            Log.i("zzx", "startService componentName: "+componentName.toString());
        }
    }
}
