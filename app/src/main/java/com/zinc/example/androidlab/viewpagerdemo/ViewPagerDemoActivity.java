package com.zinc.example.androidlab.viewpagerdemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zinc.example.androidlab.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerDemoActivity extends AppCompatActivity {

    private List<String> mStringList;
    private GalleryViewPagerAdapter mAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_demo);
        mViewPager = findViewById(R.id.vp_demo_id);
        mStringList = new ArrayList<>();
        for(int i= 0; i < 5; i++){
            String s = "这是第"+i+"条数据！";
            mStringList.add(s);
        }
        mAdapter = new GalleryViewPagerAdapter(this, mStringList);
        mViewPager.setAdapter(mAdapter);

        mViewPager.setPageMargin(10);

    }
}
