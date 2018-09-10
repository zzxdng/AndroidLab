package com.zinc.example.androidlab;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RecyclerViewFooterActivity extends AppCompatActivity {

    private RecyclerViewFooterAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<String> mDataList;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private boolean mIsLoadingData = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);
        initView();
        initData();
        mAdapter = new RecyclerViewFooterAdapter(mDataList, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mDataList = new ArrayList<>();
        for(int i = 0; i < 20 ; i++){
            mDataList.add(i, "第"+ i +"项");
        }
    }

    private List<String> addTopData(List<String> list){
        if(list != null){
            for(int i = 0; i < 5 ; i++){
                list.add(i, "下拉刷新增加过后的 第"+ i +"项");
            }
        }
        return list;
    }

    private int mAddCount = 0;
    private int addBottomData(List<String> list){
        if(list != null){
            for(int i = 0; i < 5; i++){
                mAddCount ++;
                if(mAddCount > 100){
                    return RecyclerViewFooterAdapter.STATUS_LOAD_END;
                }
                list.add("上拉刷新增加过后的 第"+ i +"项");
            }
        }
        return RecyclerViewFooterAdapter.STATUS_LOAD_COMPLETE;
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.example_string_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);//选择使用竖直的布局
        mRecyclerView.addOnScrollListener(new EndlessOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                mAdapter.setLoadingStatus(RecyclerViewFooterAdapter.STATUS_LOADING);
                if(!mIsLoadingData){
                    mIsLoadingData = true;
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mAdapter.setLoadingStatus(addBottomData(mDataList));
                                    mAdapter.notifyDataSetChanged();
                                    mIsLoadingData = false;
                                }
                            });
                        }
                    }, 1000);
                }
            }
        });
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mDataList = addTopData(mDataList);
                mAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);//取消刷新状态
            }
        });


    }

}
