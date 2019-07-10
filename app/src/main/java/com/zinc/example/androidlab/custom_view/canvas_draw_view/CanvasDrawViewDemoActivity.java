package com.zinc.example.androidlab.custom_view.canvas_draw_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.zinc.example.androidlab.R;

public class CanvasDrawViewDemoActivity extends AppCompatActivity {

    private FrameLayout canvasRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_draw_view_demo);
        canvasRootView = findViewById(R.id.canvas_root_view_id);
        CustomDrawView customDrawView = new CustomDrawView(this);
        canvasRootView.addView(customDrawView);
    }


}
