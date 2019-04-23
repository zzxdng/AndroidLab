package com.zinc.example.androidlab.draft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jakewharton.rxbinding.view.RxView;
import com.zinc.example.androidlab.R;

import rx.functions.Action1;

public class ExampleAActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_a);
        RxView.clicks(findViewById(R.id.btn_start_activity_id)).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                startActivity(new Intent(ExampleAActivity.this, ExampleBActivity.class));
            }
        });

        RxView.clicks(findViewById(R.id.btn_finish_id)).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                setResult(0);
                finish();
            }
        });
    }
}
