package com.zinc.example.androidlab.expandablelistdemo.contract;

import com.zinc.example.androidlab.expandablelistdemo.adapter.ExpandableQuestionGroup;

import java.util.List;

/**
 * author： zhao zhongxin
 * date： 2018/9/20
 * description：
 */
public class ExpandableListViewContract {

    public interface IView<IPresenter>{
        void setPresenter(IPresenter mPresenter);
        void showError();
        void showLoading();
        void dismissLoading();
        void showContent(List<ExpandableQuestionGroup> groups);
    }

    public interface IPresenter{
        void start();
        void getData();
        void retry();
    }

}
