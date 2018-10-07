package com.zinc.example.androidlab.expandablelistdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author： zhao zhongxin
 * date： 2018/9/19
 * description：
 */
public class ExpandAnswerBean implements Parcelable {

    private String answerString;
    private String answerImgUrl;

    public ExpandAnswerBean(String answerString, String answerImgUrl) {
        this.answerString = answerString;
        this.answerImgUrl = answerImgUrl;
    }

    private ExpandAnswerBean(Parcel in) {
        answerString = in.readString();
        answerImgUrl = in.readString();
    }

    public static final Creator<ExpandAnswerBean> CREATOR = new Creator<ExpandAnswerBean>() {
        @Override
        public ExpandAnswerBean createFromParcel(Parcel in) {
            return new ExpandAnswerBean(in);
        }

        @Override
        public ExpandAnswerBean[] newArray(int size) {
            return new ExpandAnswerBean[size];
        }
    };

    public String getAnswerString() {
        return answerString;
    }

    public void setAnswerString(String answerString) {
        this.answerString = answerString;
    }

    public String getAnswerImgUrl() {
        return answerImgUrl;
    }

    public void setAnswerImgUrl(String answerImgUrl) {
        this.answerImgUrl = answerImgUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(answerString);
        dest.writeString(answerImgUrl);
    }
}
