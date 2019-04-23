package com.zinc.example.androidlab.intent_action_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zinc.example.androidlab.R;

public class IntentActionDemoActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_action_demo);
    }

    public void openValidAction(View view) {
        intent = new Intent("com.android.calendar");
        openIntentAction();
    }

    public void openInvalidAction(View view) {
        intent = new Intent("com.android.invalid");
        openIntentAction();

    }

    private void openIntentAction() {
        if(intent.resolveActivity(getPackageManager()) != null){
            this.startActivity(intent);
        }else {
            toastNoActivity();
        }
    }

    public void toastNoActivity(){
        Toast.makeText(this, "没有找到这个应用的activity！", Toast.LENGTH_SHORT).show();
    }
}
