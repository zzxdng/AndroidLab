package com.zinc.example.androidlab.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.zinc.example.androidlab.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RxJavaDemoMainActivity extends AppCompatActivity {

    private static final String TAG = "RxJavaDemoMainActivity";
    private Button mRxJavaBtn;

    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo_main);

        boolean isPressBtn = false;


        final Subscription subscription =  Observable.timer(5, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.i(TAG, "subscription call: " + aLong);
            }
        });
        mSubscription = subscription;
        mRxJavaBtn = findViewById(R.id.btn_rxjava_timer);
        RxView.clicks(mRxJavaBtn)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Log.i(TAG, "RxView call: 点击了防抖动按钮");
                        Toast.makeText(RxJavaDemoMainActivity.this, "点击了防抖动按钮", Toast.LENGTH_SHORT).show();
                        unsubscribeAndDestroy();

                        Observable.create(new Observable.OnSubscribe<String>() {
                            @Override
                            public void call(Subscriber<? super String> subscriber) {

                            }
                        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(String s) {

                            }
                        });
                    }
                });


    }

    private void unsubscribeAndDestroy(){
        if(mSubscription != null){
            Log.i(TAG,"检查订阅事件");
            if(!mSubscription.isUnsubscribed()){
                Log.i(TAG,"取消订阅事件");
                mSubscription.unsubscribe();
            }
            mSubscription = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribeAndDestroy();

    }
}
