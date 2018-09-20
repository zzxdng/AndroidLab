package com.zinc.example.androidlab.expandablelistdemo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.zinc.example.androidlab.expandablelistdemo.modle.ExpandAnswerBean;
import com.zinc.example.androidlab.R;

/**
 * author： zhao zhongxin
 * date： 2018/9/19
 * description：
 */
public class AnswerViewHolder extends ChildViewHolder{

    private TextView answerTv;
    private ImageView answerImg;

    AnswerViewHolder(View itemView) {
        super(itemView);
        answerTv = itemView.findViewById(R.id.expand_answer_tv);
        answerImg = itemView.findViewById(R.id.expand_answer_img);
    }

    public void onBind(ExpandAnswerBean answer, Context context){
        answerTv.setText(answer.getAnswerString());
        if(answer.getAnswerImgUrl() != null){
            answerImg.setVisibility(View.VISIBLE);
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.loading).error(R.drawable.net_error);
            Glide.with(context).load(answer.getAnswerImgUrl()).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    return false;
                }
            }).apply(options).into(answerImg);
        }
    }
}