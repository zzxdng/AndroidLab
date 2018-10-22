package com.zinc.example.androidlab.app;

import android.app.Application;

import com.kingja.loadsir.core.LoadSir;
import com.zinc.example.androidlab.load_sir_demo.callback.ErrorCallback;
import com.zinc.example.androidlab.load_sir_demo.callback.LoadingCallback;

/**
 * author： zhao zhongxin
 * date： 2018/10/19
 * description：
 */
public class CusApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new LoadingCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();

    }
}
