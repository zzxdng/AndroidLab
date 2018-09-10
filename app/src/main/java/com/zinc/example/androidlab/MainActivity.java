package com.zinc.example.androidlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.recycler_btn_id).setOnClickListener(this);
        findViewById(R.id.recycler_footer_btn_id).setOnClickListener(this);
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

            default:
                break;
        }

        if(intent != null){
            startActivity(intent);
        }
    }
}
