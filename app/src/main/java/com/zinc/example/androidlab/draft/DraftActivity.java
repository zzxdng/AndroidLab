package com.zinc.example.androidlab.draft;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.zinc.example.androidlab.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.functions.Action1;

public class DraftActivity extends AppCompatActivity {

    private ImageView mImageView;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mImageView = findViewById(R.id.blur_img_id);
        mView = findViewById(R.id.overlay_view_id);
//        applyBlur();

        RxView.clicks(findViewById(R.id.btn_start_for_result_id)).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
//                startActivityForResult(new Intent(DraftActivity.this, ExampleAActivity.class), 0);
//                showToast(getApplicationContext(), "test");
//                showDialog();
                testRegex();
            }
        });
    }

    private void testRegex() {
        String REGEX = "\\b\\d\\.\\b";
        String INPUT =
                "1.个；2.各个；3.各个特。";
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT); // 获取 matcher 对象
//        int count = 0;
//
//        List<String> list = new ArrayList<>();
//        int numbers = 0;
//        List<Integer> integerList = new ArrayList<>();
//        while(m.find()) {
//            count++;
//            integerList.add(m.start());
//        }

        String[] strings = INPUT.split(REGEX);
        Log.i("zzx", "testRegex: "+ Arrays.asList(strings).toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showDialog(){
        Dialog dialog = new Dialog(mView.getContext());
        dialog.setContentView(R.layout.dialog_test);
        dialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showToast(Context context, CharSequence sequence){
        Toast toast = new Toast(context);
        WindowManager.LayoutParams params;
        Field mTN = null;
        try {
            mTN = toast.getClass().getDeclaredField("mTN");
            mTN.setAccessible(true);
            Class tnClassName = Class.forName("android.widget.Toast$TN");
            Field mParams = tnClassName.cast(mTN).getClass().getDeclaredField("mParams");
            mParams.setAccessible(true);
            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams)mParams.get(mTN);
            layoutParams.type = WindowManager.LayoutParams.TYPE_SEARCH_BAR;
            toast.setText(sequence);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private void applyBlur(final View view, Activity activity){
        mImageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mImageView.getViewTreeObserver().removeOnPreDrawListener(this);
                mImageView.buildDrawingCache();
                return true;
            }
        });
    }

    private void applyBlur() {
        mImageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mImageView.getViewTreeObserver().removeOnPreDrawListener(this);
                mImageView.buildDrawingCache();

                Bitmap bmp = mImageView.getDrawingCache();
                blur(bmp, mView);
                return true;
            }
        });
    }

    private void blur(Bitmap bkg, View view) {
        long startMs = System.currentTimeMillis();

        float radius = 20;

        Bitmap overlay = Bitmap.createBitmap((int) (view.getMeasuredWidth()),
                (int) (view.getMeasuredHeight()), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(overlay);

        canvas.translate(-view.getLeft(), -view.getTop());
        canvas.drawBitmap(bkg, 0, 0, null);

        RenderScript rs = RenderScript.create(getApplication().getApplicationContext());

        Allocation overlayAlloc = Allocation.createFromBitmap(
                rs, overlay);

        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(
                rs, overlayAlloc.getElement());

        blur.setInput(overlayAlloc);

        blur.setRadius(radius);

        blur.forEach(overlayAlloc);

        overlayAlloc.copyTo(overlay);

        view.setBackground(new BitmapDrawable(
                getResources(), overlay));

        rs.destroy();
        Log.i("zzx", "blur time: "+(System.currentTimeMillis() - startMs) + "ms");
    }
}
