package com.zinc.example.androidlab.svga_play_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.zinc.example.androidlab.R;

import java.io.IOException;

import pl.droidsonroids.gif.GifImageView;

/**
 * 用于对比SVGA播放与Glide播放GIF
 */
public class GIFAndSVGAPlayDemoActivity extends AppCompatActivity {

    private static final String TAG = "GIFAndSVGADemoActivity";

    private SVGAParser mParser;
    private SVGAImageView mSVGAImageView;
    private ImageView mImageView;
    private GifImageView mGifImageView;

    private pl.droidsonroids.gif.GifDrawable mGifDrawablerawable;

    private boolean mIsUseGlidePlay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_and_svga_play_demo);
        mSVGAImageView = findViewById(R.id.iv_svga_id);
        mImageView = findViewById(R.id.iv_gif_id);
        mGifImageView = findViewById(R.id.gif_img_id);
    }

    public void playGIF(View view) {
        if(mIsUseGlidePlay){
            playWithGlide();
        }else {
            playWithGifImageView();
        }
    }

    private void playWithGifImageView() {
        if(mSVGAImageView.isShown()){
            mSVGAImageView.stopAnimation();
            mSVGAImageView.setVisibility(View.GONE);
        }
        mGifImageView.setVisibility(View.VISIBLE);
        if(mGifDrawablerawable != null){
            if(mGifDrawablerawable.isPlaying()){
                mGifDrawablerawable.pause();
            }else {
                mGifDrawablerawable.start();

            }
            return;
        }

        //asset file
        try {
            mGifDrawablerawable = new pl.droidsonroids.gif.GifDrawable( getAssets(), "test.gif" );
            mGifImageView.setImageDrawable(mGifDrawablerawable);
            mGifDrawablerawable.setLoopCount(0);
            mGifDrawablerawable.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void playWithGlide() {
        if(mSVGAImageView.isShown()){
            mSVGAImageView.stopAnimation();
            mSVGAImageView.setVisibility(View.GONE);
        }
        try {
            mImageView.setVisibility(View.VISIBLE);
            Glide.with(this).asGif().load(getAssets().open("test.gif")).listener(new RequestListener<GifDrawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                    return false;
                }
            }).into(mImageView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playSvga(View view) {
        mImageView.setVisibility(View.GONE);
        mGifImageView.setVisibility(View.GONE);
        if(mSVGAImageView.isShown()){
            if(mSVGAImageView.isAnimating()){
                mSVGAImageView.pauseAnimation();
                return;
            }else {
                mSVGAImageView.startAnimation();
                return;
            }
        }
        mParser = new SVGAParser(this);
        try {
            mSVGAImageView.setVisibility(View.VISIBLE);
            mParser.parse("test.svga", new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(SVGAVideoEntity svgaVideoEntity) {
                    SVGADrawable drawable = new SVGADrawable(svgaVideoEntity);
                    mSVGAImageView.setImageDrawable(drawable);
                    mSVGAImageView.startAnimation();
                }

                @Override
                public void onError() {
                    Log.i(TAG, "onError: ");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playGifAndSVGA(View view) {

    }
}
