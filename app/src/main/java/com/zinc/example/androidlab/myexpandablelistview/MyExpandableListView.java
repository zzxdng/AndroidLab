package com.zinc.example.androidlab.myexpandablelistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.zinc.example.androidlab.R;

import java.util.ArrayList;
import java.util.List;

public class MyExpandableListView extends AppCompatActivity {

    private ExpandableListView listView;
    private MyExpandableAdapter mAdapter;
    private List<GroupBean> mGroupBeans = new ArrayList<>();
    private List<ChildBean> mChildBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_expandable_list_view);
        listView = findViewById(R.id.my_expandable_list_view);

        createData();
        loadData();

    }

    private void loadData() {
        mAdapter = new MyExpandableAdapter(this, mGroupBeans, mChildBeans);
        listView.setAdapter(mAdapter);
    }

    private void createData() {
        int count = mGroupBeans.size();
        int i = count;
        for(; i < 10+count; i++){
            GroupBean groupBean = new GroupBean();
            groupBean.setId(i);
            groupBean.setIndex(i);
            groupBean.setTitle("这是第"+ i +"项标题");
            mGroupBeans.add(groupBean);

            ChildBean childBean = new ChildBean();
            childBean.setId(i);
            childBean.setContent("详解第"+i+"项回答");
            mChildBeans.add(childBean);
        }
    }
}
