package com.zinc.example.androidlab.myexpandablelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.zinc.example.androidlab.R;

import java.util.List;

/**
 * author： zhao zhongxin
 * date： 2018/10/7
 * description：处理子列表只有一项的特殊情况
 */
public class MyExpandableAdapter extends BaseExpandableListAdapter {

    private List<GroupBean> mGroupBeans;
    private List<ChildBean> mChildBeans;//子列表默认为1项处理
    private Context mContext;

    public MyExpandableAdapter(Context context, List<GroupBean> groupBeans, List<ChildBean> childBeans) {
        mContext = context;
        mGroupBeans = groupBeans;
        mChildBeans = childBeans;
    }

    @Override
    public int getGroupCount() {
        return mGroupBeans.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupBeans.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildBeans.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mGroupBeans.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mChildBeans.get(groupPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        MyGroupViewHolder groupViewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.my_expadable_item_title_layout, parent, false);
            groupViewHolder = new MyGroupViewHolder(convertView);
            groupViewHolder.onBindView(mGroupBeans.get(groupPosition));
            convertView.setTag(groupViewHolder);
        }else {
            groupViewHolder = (MyGroupViewHolder) convertView.getTag();
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.my_expand_item_answer_layout, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            childViewHolder.onBindView(mChildBeans.get(groupPosition));
            convertView.setTag(childViewHolder);
        }else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class MyGroupViewHolder {
        private TextView mIndexTv;
        private TextView mTitleTv;

        MyGroupViewHolder(View itemView){
            mIndexTv = itemView.findViewById(R.id.my_expand_title_index_tv);
            mTitleTv = itemView.findViewById(R.id.my_expand_title_context_tv);
        }

        private void onBindView(GroupBean groupBean){
            mIndexTv.setText(groupBean.getIndex()+"");
            mTitleTv.setText(groupBean.getTitle());
        }
    }

    private class ChildViewHolder{
        private TextView mAnswerTv;

        ChildViewHolder(View itemView){
            mAnswerTv = itemView.findViewById(R.id.my_expand_answer_context_tv);
        }

        private void onBindView(ChildBean childBean){
            mAnswerTv.setText(childBean.getContent());
        }
    }

}
