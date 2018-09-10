package com.zinc.example.androidlab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * author： zhao zhongxin
 * date： 2018/9/10
 * description：
 */
public class RecyclerViewFooterAdapter extends RecyclerView.Adapter {

    private final int TYPE_NORMAL = 1;
    private final int TYPE_FOOTER = 2;

    private List<String> mDataList;
    private Context mContext;

    //加载状态
    public static final int STATUS_LOAD_COMPLETE = 1;//加载完成
    public static final int STATUS_LOADING = 2;//加载中...
    public static final int STATUS_LOAD_END = 3;//加载到底了
    private int mLoadingStatus = STATUS_LOAD_COMPLETE;

    RecyclerViewFooterAdapter(List<String> mDataList, Context mContext) {
        this.mDataList = mDataList;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        if(position + 1 == getItemCount()){
            return TYPE_FOOTER;
        }else {
            return TYPE_NORMAL;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_FOOTER){
            View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_item_footer_layout, parent, false);
            return  new FooterViewHolder(view);
        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item_normal_layout, parent, false);
            return  new NormalViewHolder(view);
        }
    }




    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof NormalViewHolder){
            NormalViewHolder normalViewHolder = (NormalViewHolder)holder;
            normalViewHolder.contentTv.setText(mDataList.get(position));
        }else if (holder instanceof FooterViewHolder){
            FooterViewHolder footerViewHolder = (FooterViewHolder)holder;
            switch (mLoadingStatus){
                case STATUS_LOAD_COMPLETE:
                    footerViewHolder.loadingHintView.setVisibility(View.GONE);
                    footerViewHolder.loadEndHintView.setVisibility(View.GONE);
                    break;

                case STATUS_LOADING :
                    footerViewHolder.loadingHintView.setVisibility(View.VISIBLE);
                    footerViewHolder.loadEndHintView.setVisibility(View.GONE);
                    break;

                case STATUS_LOAD_END:
                    footerViewHolder.loadingHintView.setVisibility(View.GONE);
                    footerViewHolder.loadEndHintView.setVisibility(View.VISIBLE);
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size() + 1;
    }

    public void setLoadingStatus(int status){
        mLoadingStatus = status;
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {

        LinearLayout loadingHintView;
        LinearLayout loadEndHintView;
        FooterViewHolder(View itemView) {
            super(itemView);
            loadingHintView = itemView.findViewById(R.id.loading_hint_ll);
            loadEndHintView = itemView.findViewById(R.id.load_end_hint_ll);
        }
    }

    private class NormalViewHolder extends RecyclerView.ViewHolder {
        TextView contentTv;
        NormalViewHolder(View itemView) {
            super(itemView);
            contentTv = itemView.findViewById(R.id.example_content_tv);
        }
    }
}
