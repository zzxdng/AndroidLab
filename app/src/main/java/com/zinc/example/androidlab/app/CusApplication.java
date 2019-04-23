package com.zinc.example.androidlab.app;

import android.app.Application;
import android.util.Log;

import com.kingja.loadsir.core.LoadSir;
import com.zinc.example.androidlab.database.realm.CustomMigration;
import com.zinc.example.androidlab.load_sir_demo.callback.ErrorCallback;
import com.zinc.example.androidlab.load_sir_demo.callback.LoadingCallback;

import java.io.File;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * author： zhao zhongxin
 * date： 2018/10/19
 * description：
 */
public class CusApplication extends Application {

    public static long SCHEMA_VERSION = 5;

    @Override
    public void onCreate() {
        super.onCreate();
        initLoadSir();
        initRealm();

    }

    private void initLoadSir() {
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new LoadingCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
        builder = builder.schemaVersion(SCHEMA_VERSION)
                .name("test.realm")
                .migration(new CustomMigration())//升级数据库
//                .deleteRealmIfMigrationNeeded()
        ;
        RealmConfiguration configuration = builder.build();
        DynamicRealm dynamicRealm = DynamicRealm.getInstance(configuration);
        dynamicRealm.getVersion(); // Returns version of Realm file on disk
        Log.i("zzx", "diskVersion: "+dynamicRealm.getVersion());
        if (dynamicRealm.getVersion() > SCHEMA_VERSION) {
            Log.i("zzx", "dynamicRealm.getVersion() < SCHEMA_VERSION: "+(dynamicRealm.getVersion() < SCHEMA_VERSION)+"diskVersion:"+dynamicRealm.getVersion()+" currentVersion:"+SCHEMA_VERSION);
            //当前Schema版本低于disk中的版本（Realm发生降低版本的行为）
            //添加上删除Realm数据库
            configuration = builder.deleteRealmIfMigrationNeeded().build();
            Realm.setDefaultConfiguration(configuration);
        } else {
            Realm.setDefaultConfiguration(configuration);
        }
        dynamicRealm.close();
    }
}
