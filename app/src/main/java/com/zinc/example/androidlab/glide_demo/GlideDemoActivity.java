package com.zinc.example.androidlab.glide_demo;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zinc.example.androidlab.R;

import java.security.MessageDigest;

public class GlideDemoActivity extends AppCompatActivity {

    private String mImgUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541574730&di=e534d640472c0591a9160460cb900cc3&imgtype=jpg&er=1&src=http%3A%2F%2Fimg1.sc115.com%2Fuploads%2Fsc%2Fjpg%2FHD%2F33%2F3939.jpg";
    private String mImgUrl2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540980598545&di=bf4c4834e690393d8f9b7ce2871079d1&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201510%2F30%2F20151030120343_SUh2e.thumb.700_0.jpeg";
    private String mImgUrl3 = "http://www.yishujia.net/upload/2009_04/09042709065899.jpg";
    private ImageView mIvGlideDemo;
    private RoundedImageView mRoundedImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_demo);
        mIvGlideDemo = findViewById(R.id.iv_glide_demo_id);
        mRoundedImageView = findViewById(R.id.riv_test_id);
    }

    public void loadOnlineData(View view) {

        //设置图片圆角角度
        RoundedCorners roundedCorners= new RoundedCorners(12);
        //通过RequestOptions扩展功能
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).centerCrop();

        Glide.with(GlideDemoActivity.this).load(mImgUrl3).apply(options).listener(new RequestListener<Drawable>() {

            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(mIvGlideDemo);

    }

    public void loadRoundedImg(View view) {
        mRoundedImageView.setCornerRadius(12);
        Glide.with(GlideDemoActivity.this).load(mImgUrl3).listener(new RequestListener<Drawable>() {

            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(mRoundedImageView);
    }

    class CusBitmapTransfomation extends BitmapTransformation{

        @Override
        protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
            return null;
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }
    }
}
