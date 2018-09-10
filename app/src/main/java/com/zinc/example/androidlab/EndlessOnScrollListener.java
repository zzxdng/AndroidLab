package com.zinc.example.androidlab;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * author： zhao zhongxin
 * date： 2018/9/10
 * description：上拉到底显示加载更多，这个功能还有待验证，bug很多
 */
public abstract class EndlessOnScrollListener extends RecyclerView.OnScrollListener {

    private static final String TAG = "EndlessOnScrollListener";

    private LinearLayoutManager linearLayoutManager;

    //当前所在页
    private int mCurrentPage = 0;

    //已经加载出来的item数
    private int totalItemCount = 0;

    //用来存储上一个totalItemCount
    private int previousTotal = 0;

    //屏幕可见的item数量
    private int visibleItemCount;

    //屏幕可见第一个Item的位置
    private int firstVisibleItem;

    private int mLastItemPositon;

    //是否执行加载数据
    private boolean isUpward = true;

    private int mThreashHoldItem = 3;

    EndlessOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        mLastItemPositon = linearLayoutManager.findLastCompletelyVisibleItemPosition();
        totalItemCount = linearLayoutManager.getItemCount();
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
        // 大于0表示正在向上滑动，小于等于0表示停止或向下滑动
        if(dy > 0){
            if(mLastItemPositon + mThreashHoldItem >= (totalItemCount - 1)){
                onLoadMore(mCurrentPage);
            }
        }
    }

    public abstract void onLoadMore(int currentPage);
}
