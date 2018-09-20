package com.zinc.example.androidlab.expandablelistdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zinc.example.androidlab.expandablelistdemo.adapter.ExpandAdapter;
import com.zinc.example.androidlab.expandablelistdemo.modle.ExpandAnswerBean;
import com.zinc.example.androidlab.expandablelistdemo.modle.ExpandableQuestionGroup;
import com.zinc.example.androidlab.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpandableListViewDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view_demo);

        List<ExpandableQuestionGroup> mQuestionGroups = getData();
        RecyclerView recyclerView = findViewById(R.id.faq_content_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //instantiate your adapter with the list of genres
        ExpandAdapter adapter = new ExpandAdapter(mQuestionGroups, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private List<ExpandableQuestionGroup> getData() {
        List<ExpandableQuestionGroup> questionGroups = new ArrayList<>();
        for(int i=0; i < 15; i++){
            ExpandAnswerBean answerBean;
            if(i%2 == 0){
                answerBean = new ExpandAnswerBean("回答第"+i+"项", "http://guolin.tech/book.png");
            }else {
                answerBean = new ExpandAnswerBean("回答第"+i+"项", null);
            }
            ExpandableQuestionGroup group = new ExpandableQuestionGroup("问题第"+i+"项",Arrays.asList(answerBean));
            questionGroups.add(group);
        }
        return questionGroups;
    }
}
