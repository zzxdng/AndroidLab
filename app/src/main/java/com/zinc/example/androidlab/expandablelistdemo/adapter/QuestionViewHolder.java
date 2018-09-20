package com.zinc.example.androidlab.expandablelistdemo.adapter;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;
import com.zinc.example.androidlab.R;

/**
 * author： zhao zhongxin
 * date： 2018/9/19
 * description：
 */
public class QuestionViewHolder extends GroupViewHolder{

    private TextView qustionTitle;

    QuestionViewHolder(View itemView) {
        super(itemView);
        qustionTitle = itemView.findViewById(R.id.question_title_tv);
    }

    public void setQuestionTitle(com.thoughtbot.expandablerecyclerview.models.ExpandableGroup expandableGroup){
        qustionTitle.setText(expandableGroup.getTitle());
    }
}
