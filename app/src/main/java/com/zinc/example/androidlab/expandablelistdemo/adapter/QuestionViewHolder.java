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

    private TextView questionTitle;
    private ICollapseListener listener;

    QuestionViewHolder(View itemView) {
        super(itemView);
        questionTitle = itemView.findViewById(R.id.question_title_tv);
    }

    public static QuestionViewHolder getInstance(View itemView, ICollapseListener listener){
        QuestionViewHolder questionViewHolder = new QuestionViewHolder(itemView);
        questionViewHolder.listener = listener;
        return questionViewHolder;
    }

    public void setQuestionTitle(com.thoughtbot.expandablerecyclerview.models.ExpandableGroup expandableGroup){
        questionTitle.setText(expandableGroup.getTitle());
    }

    public void expand() {
        listener.expand();
    }

    public void collapse() {
        listener.collapse();
    }

    public interface ICollapseListener{
        void expand();
        void collapse();
    }

}
