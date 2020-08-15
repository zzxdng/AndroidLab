package com.zinc.example.androidlab.image.lottie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.ImageAssetDelegate;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.jakewharton.rxbinding.view.RxView;
import com.zinc.example.androidlab.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import rx.functions.Action1;

public class LottieDemoActivity extends AppCompatActivity {

    final String FILE_MIDDLE = "preview";
    final String FOLDER_NAME = "lottie/"+FILE_MIDDLE+"/images";
    final String FILE_NAME = "lottie/"+FILE_MIDDLE+"/lottie.json";
    final String FILE_NAME_SMALL = "lottie/"+FILE_MIDDLE+"/lottie_small.json";


    final String SMALL_FILE_MIDDLE = "small_demo";
    final String SMALL_FOLDER_NAME = "lottie/"+SMALL_FILE_MIDDLE+"/images";
    final String SMALL_FILE_NAME = "lottie/"+SMALL_FILE_MIDDLE+"/lottie.json";

    LottieAnimationView mLottieAnimationView;
    LottieAnimationView mSmallLottieAnimationView;
    private LottieComposition mLottieComposition;
    private LottieComposition mBigLottieComposition;
    private LottieComposition mSmallLottieComposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_demo);
        mLottieAnimationView = findViewById(R.id.lottie_test_id);
        mSmallLottieAnimationView = findViewById(R.id.lottie_test_small_id);

        RxView.clicks(mLottieAnimationView).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                playAnim();
            }
        });

        RxView.clicks(findViewById(R.id.btn_lottie_play_id)).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                playAnim();
            }
        });

        RxView.clicks(findViewById(R.id.btn_lottie_play_origin_id)).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                playOriginAnim();
            }
        });

        RxView.clicks(findViewById(R.id.btn_lottie_play_small_id)).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                playSmallAnim();
            }
        });
    }

    private void playAnim(){
        setDelegate(2);
        if(mLottieComposition == null){
            mLottieAnimationView.setImageAssetsFolder(FOLDER_NAME);
            mLottieAnimationView.setAnimation(FILE_NAME_SMALL);
            LottieComposition.Factory.fromAssetFileName(getApplicationContext(), FILE_NAME_SMALL, new OnCompositionLoadedListener() {
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

    private void setDelegate(final int inSampleSize) {

        mLottieAnimationView.setImageAssetDelegate(new ImageAssetDelegate() {
            @Override
            public Bitmap fetchBitmap(LottieImageAsset asset) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = inSampleSize;
                InputStream inputStream = null;
                try {
                    inputStream = getAssets().open(FOLDER_NAME+File.separator+asset.getFileName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                return bitmap;
            }
        });

    }

    private void playSmallAnim(){
        if(mSmallLottieComposition == null){
            mSmallLottieAnimationView.setImageAssetsFolder(SMALL_FOLDER_NAME);
            mSmallLottieAnimationView.setAnimation(SMALL_FILE_NAME);
            LottieComposition.Factory.fromAssetFileName(getApplicationContext(), SMALL_FILE_NAME, new OnCompositionLoadedListener() {
                @Override
                public void onCompositionLoaded(LottieComposition composition) {
                    mSmallLottieComposition = composition;
                    mSmallLottieAnimationView.setComposition(mSmallLottieComposition);
                    mSmallLottieAnimationView.playAnimation();
                }
            });
        }else {
            mSmallLottieAnimationView.setComposition(mSmallLottieComposition);
            mSmallLottieAnimationView.playAnimation();
        }
    }

    private void playOriginAnim(){
        setDelegate(1);
        if(mBigLottieComposition == null){
            mLottieAnimationView.setImageAssetsFolder(FOLDER_NAME);
            mLottieAnimationView.setAnimation(FILE_NAME);
            mLottieAnimationView.setImageAssetDelegate(new ImageAssetDelegate() {
                @Override
                public Bitmap fetchBitmap(LottieImageAsset asset) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 1;
                    InputStream inputStream = null;
                    try {
                        inputStream = getAssets().open(FOLDER_NAME+File.separator+asset.getFileName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                    return bitmap;
                }
            });
            LottieComposition.Factory.fromAssetFileName(getApplicationContext(), FILE_NAME, new OnCompositionLoadedListener() {
                @Override
                public void onCompositionLoaded(LottieComposition composition) {
                    mBigLottieComposition = composition;
                    mLottieAnimationView.setComposition(mBigLottieComposition);
                    mLottieAnimationView.playAnimation();
                }
            });
        }else {
            mLottieAnimationView.setComposition(mBigLottieComposition);
            mLottieAnimationView.playAnimation();
        }
    }

    private String getFolderName(){
        return Environment.getExternalStorageDirectory().toString()+ File.separator+"lottie";
    }

}
