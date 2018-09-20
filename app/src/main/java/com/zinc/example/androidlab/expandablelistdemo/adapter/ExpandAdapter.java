package com.zinc.example.androidlab.expandablelistdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.zinc.example.androidlab.R;
import com.zinc.example.androidlab.expandablelistdemo.modle.ExpandAnswerBean;

import java.util.List;

/**
 * author： zhao zhongxin
 * date： 2018/9/19
 * description：
 */
public class ExpandAdapter extends ExpandableRecyclerViewAdapter<QuestionViewHolder, AnswerViewHolder> {

    private Context mContext;
    private QuestionViewHolder mQuestionViewHolder;

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
        mQuestionViewHolder = QuestionViewHolder.getInstance(view, new QuestionViewHolder.ICollapseListener() {
            @Override
            public void expand() {
                Toast.makeText(mContext,"展开", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void collapse() {
                Toast.makeText(mContext,"收缩", Toast.LENGTH_SHORT).show();
            }
        });
        return mQuestionViewHolder;
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

    public interface onCollapsedListener{
        boolean isCollapsed();
    }
}
