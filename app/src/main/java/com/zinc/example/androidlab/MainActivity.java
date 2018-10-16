package com.zinc.example.androidlab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zinc.example.androidlab.expandablelistdemo.view.ExpandableListViewDemoActivity;
import com.zinc.example.androidlab.myexpandablelistview.MyExpandableListView;
import com.zinc.example.androidlab.rxjavademo.RxJavaDemoMainActivity;

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
}
