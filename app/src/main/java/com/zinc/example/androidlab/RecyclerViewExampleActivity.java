package com.zinc.example.androidlab;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewExampleActivity extends AppCompatActivity {

    private RecyclerViewItemNormalAdapter adapter;
    private RecyclerView recyclerView;
    private List<String> dataList;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);
        initView();
        initData();
        adapter = new RecyclerViewItemNormalAdapter(dataList, this);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        dataList = new ArrayList<>();
        for(int i = 0; i < 20 ; i++){
            dataList.add(i, "第"+ i +"项");
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

    private List<String> addBottomData(List<String> list){

        if(list != null){
            for(int i = 0; i < 5 ; i++){
                list.add("上拉刷新增加过后的 第"+ i +"项");
            }
        }
        return list;
    }

    private void initView() {
        recyclerView = findViewById(R.id.example_string_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);//选择使用竖直的布局
        //加上分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.addOnScrollListener(new EndlessOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                dataList = addBottomData(dataList);
                adapter.notifyDataSetChanged();

            }
        });
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dataList = addTopData(dataList);
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);//取消刷新状态
            }
        });


    }
}
