package com.zinc.example.testmodule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TestModuleMainActivity extends AppCompatActivity {

    private static final String TAG = "TestModuleMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_module_activity_main);
        Log.i(TAG, "testmodule onCreate: ");
    }
}
