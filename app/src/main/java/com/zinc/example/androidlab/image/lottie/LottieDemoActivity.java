package com.zinc.example.androidlab.image.lottie;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.jakewharton.rxbinding.view.RxView;
import com.zinc.example.androidlab.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import rx.functions.Action1;

public class LottieDemoActivity extends AppCompatActivity {

    final String FOLDER_NAME = "lottie/preview/images";
    final String FILE_NAME = "lottie/preview/lottie.json";

    LottieAnimationView mLottieAnimationView;
    private LottieComposition mLottieComposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_demo);
        mLottieAnimationView = findViewById(R.id.lottie_test_id);

        RxView.clicks(mLottieAnimationView).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                playAnim();
            }
        });
    }

    private void playAnim(){
        if(mLottieComposition == null){
            mLottieAnimationView.setImageAssetsFolder(FOLDER_NAME);
            mLottieAnimationView.setAnimation(FILE_NAME);
            File file = new File("");
            try {
                InputStream inputStream = new FileInputStream(file);
                LottieComposition.Factory.fromInputStream(getApplicationContext(), inputStream, new OnCompositionLoadedListener() {
                    @Override
                    public void onCompositionLoaded(LottieComposition composition) {

                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            LottieComposition.Factory.fromAssetFileName(getApplicationContext(), FILE_NAME, new OnCompositionLoadedListener() {
                @Override
                public void onCompositionLoaded(LottieComposition composition) {
                    mLottieComposition = composition;
                    mLottieAnimationView.setComposition(mLottieComposition);
                    mLottieAnimationView.playAnimation();
                }
            });
        }else {
            mLottieAnimationView.setComposition(mLottieComposition);
            mLottieAnimationView.playAnimation();
        }
    }

    private String getFolderName(){
        return Environment.getExternalStorageDirectory().toString()+ File.separator+"lottie";
    }

}
