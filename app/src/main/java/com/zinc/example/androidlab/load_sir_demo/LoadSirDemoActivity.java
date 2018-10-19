package com.zinc.example.androidlab.load_sir_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.zinc.example.androidlab.R;
import com.zinc.example.androidlab.load_sir_demo.callback.ErrorCallback;
import com.zinc.example.androidlab.load_sir_demo.callback.LoadingCallback;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class LoadSirDemoActivity extends AppCompatActivity {

    private RelativeLayout mContainerView;
    private ImageView mTestIv;
    private LoadService mLoadService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_sir_demo);
        findView();
        initView();
        mTestIv = findViewById(R.id.iv_load_sir_test);
        // Your can change the callback on sub thread directly.
        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new ErrorCallback())
                .addCallback(new LoadingCallback())
                .setDefaultCallback(LoadingCallback.class)
                .build();

        mLoadService = loadSir.register(mTestIv, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                mLoadService.showCallback(LoadingCallback.class);
                //模拟网络加载
                Observable.timer(5, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mLoadService.showSuccess();
                        mTestIv.setImageDrawable(getDrawable(R.drawable.test_view));
                    }
                });
            }
        });

        Observable.timer(3, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                mLoadService.showCallback(ErrorCallback.class);
            }
        });
    }

    private void initView() {

    }

    private void findView() {
        mContainerView = findViewById(R.id.rl_demo_container_id);

    }
}