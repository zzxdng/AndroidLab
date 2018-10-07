package com.zinc.example.androidlab.expandablelistdemo.adapter;

import com.zinc.example.androidlab.expandablelistdemo.model.ExpandAnswerBean;

import java.util.List;

/**
 * author： zhao zhongxin
 * date： 2018/9/19
 * description：
 */
public class ExpandableQuestionGroup extends com.thoughtbot.expandablerecyclerview.models.ExpandableGroup <ExpandAnswerBean>{

    public ExpandableQuestionGroup(String title, List<ExpandAnswerBean> items) {
        super(title, items);
    }
}
