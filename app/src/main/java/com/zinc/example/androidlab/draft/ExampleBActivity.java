package com.zinc.example.androidlab.draft;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jakewharton.rxbinding.view.RxView;
import com.zinc.example.androidlab.R;

import rx.functions.Action1;

public class ExampleBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_b);
        RxView.clicks(findViewById(R.id.btn_finish_id)).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                finish();
            }
        });
    }
}
