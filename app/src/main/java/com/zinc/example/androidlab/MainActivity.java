package com.zinc.example.androidlab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;
import com.zinc.example.androidlab.database.realm.RealmDemoActivity;
import com.zinc.example.androidlab.draft.DraftActivity;
import com.zinc.example.androidlab.expandablelistdemo.view.ExpandableListViewDemoActivity;
import com.zinc.example.androidlab.glide_demo.GlideDemoActivity;
import com.zinc.example.androidlab.image.lottie.LottieDemoActivity;
import com.zinc.example.androidlab.intent_action_demo.IntentActionDemoActivity;
import com.zinc.example.androidlab.load_sir_demo.LoadSirDemoActivity;
import com.zinc.example.androidlab.myexpandablelistview.MyExpandableListView;
import com.zinc.example.androidlab.push_demo.NotificationActivity;
import com.zinc.example.androidlab.rxjavademo.RxJavaDemoMainActivity;
import com.zinc.example.androidlab.svga_play_demo.GIFAndSVGAPlayDemoActivity;
import com.zinc.example.androidlab.viewpagerdemo.ViewPagerDemoActivity;
import com.zinc.example.androidlab.webview_demo.WebViewDemoActivity;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        findViewById(R.id.recycler_btn_id).setOnClickListener(this);
        findViewById(R.id.recycler_footer_btn_id).setOnClickListener(this);
        findViewById(R.id.expandable_recycler_btn).setOnClickListener(this);
        findViewById(R.id.my_expandable_btn).setOnClickListener(this);
        findViewById(R.id.btn_rxjava_demo).setOnClickListener(this);

        RxView.clicks(findViewById(R.id.draft_btn_id)).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                startActivityFun(DraftActivity.class);
            }
        });

        RxView.clicks(findViewById(R.id.btn_lottie_demo_id)).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                startActivityFun(LottieDemoActivity.class);
            }
        });

        RxView.clicks(findViewById(R.id.btn_realm_demo_id)).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                startActivityFun(RealmDemoActivity.class);
            }
        });

        RxView.clicks(findViewById(R.id.btn_wv_id)).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                startActivityFun(WebViewDemoActivity.class);
            }
        });

        RxView.clicks(findViewById(R.id.btn_notification_id)).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                startActivityFun(NotificationActivity.class);
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.recycler_btn_id:
                intent = new Intent(MainActivity.this, RecyclerViewExampleActivity.class);
                break;

            case R.id.recycler_footer_btn_id:
                intent = new Intent(MainActivity.this, RecyclerViewFooterActivity.class);
                break;

            case R.id.expandable_recycler_btn:
                intent = new Intent(MainActivity.this, ExpandableListViewDemoActivity.class);
                break;

            case R.id.my_expandable_btn:
                intent = new Intent(MainActivity.this, MyExpandableListView.class);
                break;

            case R.id.btn_rxjava_demo:
                intent = new Intent(MainActivity.this, RxJavaDemoMainActivity.class);
                break;

            default:
                break;
        }

        if(intent != null){
            startActivity(intent);
        }
    }

    public void showLoadSirDemo(View view) {
        startActivityFun(LoadSirDemoActivity.class);
    }

    public void showViewpagerDemo(View view) {
        startActivityFun(ViewPagerDemoActivity.class);
    }

    private void startActivityFun(Class<?>  activityClass) {
        startActivity(new Intent(MainActivity.this,activityClass));
    }

    public void showGifAndSVAGDemo(View view) {
        startActivityFun(GIFAndSVGAPlayDemoActivity.class);
    }

    public void intentActionOpen(View view) {
        startActivityFun(IntentActionDemoActivity.class);
    }

    public void openGlideDemo(View view) {
        startActivityFun(GlideDemoActivity.class);
    }
}
