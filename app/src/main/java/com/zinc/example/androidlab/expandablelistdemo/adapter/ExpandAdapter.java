package com.zinc.example.androidlab.expandablelistdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.zinc.example.androidlab.expandablelistdemo.modle.ExpandAnswerBean;
import com.zinc.example.androidlab.R;

import java.util.List;

/**
 * author： zhao zhongxin
 * date： 2018/9/19
 * description：
 */
public class ExpandAdapter extends ExpandableRecyclerViewAdapter<QuestionViewHolder, AnswerViewHolder> {

    private Context mContext;

    public ExpandAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    public ExpandAdapter(List<? extends ExpandableGroup> groups, Context context) {
        super(groups);
        mContext = context;
    }

    @Override
    public QuestionViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.expand_item_title_layout,parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public AnswerViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.expand_item_answer_layout, parent, false);
        return new AnswerViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(AnswerViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final ExpandAnswerBean answerBean = ((ExpandAnswerBean) group.getItems().get(childIndex));
        holder.onBind(answerBean, mContext);
    }

    @Override
    public void onBindGroupViewHolder(QuestionViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setQuestionTitle(group);
    }
}
