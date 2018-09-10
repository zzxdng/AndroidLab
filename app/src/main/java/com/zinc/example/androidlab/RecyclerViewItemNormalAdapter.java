package com.zinc.example.androidlab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * author： zhao zhongxin
 * date： 2018/9/10
 * description：
 * RecyclerView 的基本操作方法
 */
public class RecyclerViewItemNormalAdapter extends RecyclerView.Adapter<RecyclerViewItemNormalAdapter.RecyclerViewHolder> {

    private List<String> dataList;
    private Context context;

    /**
     * 数据和视图的来源
     * @param dataList
     * @param context
     */
    RecyclerViewItemNormalAdapter(List<String> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }


    /**
     * 获取自定义holder，并将Item布局与holder绑定
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_normal_layout, parent, false));
    }

    /**
     * 装填Holder子项的内容，或者定义holder子项的事件
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {
        final String text = dataList.get(position);
        holder.contentTv.setText(dataList.get(position));
        holder.contentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击了"+text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * @return 加载的数据的大小
     */
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
     * 管理子项，实例化子项的地方
     */
    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView contentTv;
        RecyclerViewHolder(View itemView) {
            super(itemView);
            contentTv = itemView.findViewById(R.id.example_content_tv);
        }
    }

    /**添加一项数据**/
    public boolean addItem(int position, String msg){
        if(position < dataList.size() && position >= 0){
            dataList.add(position, msg);
            notifyItemInserted(position);
            return true;
        }
        return false;
    }

    /**删除一项数据**/
    public String removeItem(int positon){
        String temp = null;
        if(positon < dataList.size() && positon >= 0){
            temp = dataList.remove(positon);
            notifyItemRemoved(positon);
        }
        return temp;
    }

    /**清除所有数据**/
    public void clearAll(){
        dataList.clear();
        notifyDataSetChanged();
    }
}
