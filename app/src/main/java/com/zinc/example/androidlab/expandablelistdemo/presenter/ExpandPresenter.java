package com.zinc.example.androidlab.expandablelistdemo.presenter;

import android.app.Activity;
import android.content.Context;

import com.zinc.example.androidlab.expandablelistdemo.adapter.ExpandableQuestionGroup;
import com.zinc.example.androidlab.expandablelistdemo.contract.ExpandableListViewContract;
import com.zinc.example.androidlab.expandablelistdemo.model.ExpandAnswerBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * author： zhao zhongxin
 * date： 2018/9/20
 * description：
 */
public class ExpandPresenter implements ExpandableListViewContract.IPresenter {

    private ExpandableListViewContract.IView view;
    private Context mContext;

    public ExpandPresenter(ExpandableListViewContract.IView view, Context context) {
        this.view = view;
        this.mContext = context;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        view.showLoading();
        getData();
    }

    @Override
    public void getData() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                final List<ExpandableQuestionGroup> groups;
                groups = requestData();
                ((Activity)mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.dismissLoading();
                        if(groups == null){
                            view.showError();
                        }else {
                            view.showContent(groups);
                        }
                    }
                });

            }
        },2000);
    }

    @Override
    public void retry() {
        view.showLoading();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                final List<ExpandableQuestionGroup> groups;
                groups = requestData();
                ((Activity)mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.dismissLoading();
                        if(groups == null){
                            view.showError();
                        }else {
                            view.showContent(groups);
                        }
                    }
                });
            }
        },2000);
    }


    private List<ExpandableQuestionGroup> requestData() {
        List<ExpandableQuestionGroup> questionGroups = new ArrayList<>();
        for(int i=0; i < 15; i++){
            ExpandAnswerBean answerBean;
            if(i%2 == 0){
                answerBean = new ExpandAnswerBean("回答第"+i+"项", "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1537429196&di=5f63de3a1ed924c5902643bc73e168c2&src=http://file2.zhituad.com/thumb/201108/04/201108040307200387tKP2R_priv.jpg");
            }else {
                answerBean = new ExpandAnswerBean("回答第"+i+"项", null);
            }
            ExpandableQuestionGroup group = new ExpandableQuestionGroup("问题第"+i+"项", Arrays.asList(answerBean));
            questionGroups.add(group);
        }

        Random random = new Random();
        int flag = random.nextInt(10);
        if(flag < 5){
            return null;
        }

        return questionGroups;
    }
}
