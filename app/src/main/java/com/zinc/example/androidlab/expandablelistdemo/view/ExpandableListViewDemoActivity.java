package com.zinc.example.androidlab.expandablelistdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.zinc.example.androidlab.R;
import com.zinc.example.androidlab.expandablelistdemo.adapter.ExpandAdapter;
import com.zinc.example.androidlab.expandablelistdemo.contract.ExpandableListViewContract;
import com.zinc.example.androidlab.expandablelistdemo.presenter.ExpandPresenter;

import java.util.List;

public class ExpandableListViewDemoActivity extends AppCompatActivity implements ExpandableListViewContract.IView, View.OnClickListener {

    private ExpandableListViewContract.IPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private LinearLayout mErrorView;
    private LinearLayout mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view_demo);

        mRecyclerView = findViewById(R.id.faq_content_view);
        mLayoutManager = new LinearLayoutManager(this);
        mErrorView = findViewById(R.id.expand_error_view);
        mLoadingView = findViewById(R.id.expand_loading_view);

        mErrorView.setOnClickListener(this);
        new ExpandPresenter(this, this);
        mPresenter.start();
    }

    @Override
    public void setPresenter(Object mPresenter) {
        this.mPresenter = (ExpandableListViewContract.IPresenter) mPresenter;
    }

    @Override
    public void showError() {
        mRecyclerView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        mRecyclerView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoading() {
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showContent(List list) {
        mErrorView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        ExpandAdapter adapter = new ExpandAdapter(list, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        mPresenter.retry();
    }
}
