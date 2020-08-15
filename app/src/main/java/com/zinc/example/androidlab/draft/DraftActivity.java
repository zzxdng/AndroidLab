package com.zinc.example.androidlab.draft;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.zinc.example.androidlab.R;
import com.zinc.example.androidlab.custom_view.canvas_draw_view.CanvasDrawViewDemoActivity;
import com.zinc.example.androidlab.service_demo.ServiceDemoActivity;
import com.zinc.example.androidlab.util.FileUtils;
import com.zinc.example.androidlab.util.ShellUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.functions.Action1;


public class DraftActivity extends AppCompatActivity {

    public static final String SD_PATH =  Environment.getExternalStorageDirectory()+"";
    private static HashMap<String, OuterClass.InnerClass> map = new HashMap<>();

    private ImageView mImageView;
    private View mView;

    static {
        map.put("1", new OuterClass.InnerClass(){

            @Override
            void printName() {
                System.out.println("zzx "+"printName: "+"1");
            }
        });

        map.put("2", new OuterClass.InnerClass(){

            @Override
            void printName() {
                System.out.println("zzx "+"printName: "+"2");
            }
        });

        map.put("3", new OuterClass.InnerClass(){

            @Override
            void printName() {
                System.out.println("zzx "+"printName: "+"3");
            }
        });

        map.put("4", new OuterClass.InnerClass(){

            @Override
            void printName() {
                System.out.println("zzx "+"printName: "+"4");
            }
        });

        map.put("5", new OuterClass.InnerClass(){

            @Override
            void printName() {
                System.out.println("zzx "+"printName: "+"5");
            }
        });

        map.put("6", new OuterClass.InnerClass(){

            @Override
            void printName() {
                System.out.println("zzx "+"printName: "+"6");
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        long timeStamp = getIntent().getLongExtra("timestamp", -1);

        Log.i("zzx", "onCreate: 跳转耗时:"+(System.currentTimeMillis() - timeStamp));

        mImageView = findViewById(R.id.blur_img_id);
        mView = findViewById(R.id.overlay_view_id);
//        applyBlur();

        RxView.clicks(findViewById(R.id.btn_start_for_result_id)).subscribe(new Action1<Void>() {

            @Override
            public void call(Void aVoid) {
//                startZuoYeBang("");
//                startActivityForResult(new Intent(DraftActivity.this, CanvasDrawViewDemoActivity.class), 0);
//                showToast(getApplicationContext(), "test");
//                showDialog();
//                testRegex();
//                startCCExample();
//                startServiceExample();
//                transformString();
//                testDex();
                getPackageDataInfo();
            }
        });
    }

    private void getPackageDataInfo(){

        if(getStatePermission(this)){
            FileUtils.getPkgSizeInfo(getApplicationContext(), "com.eebbk.askhomework");
        }


    }

    private boolean getStatePermission(Activity activity) {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.PACKAGE_USAGE_STATS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.PACKAGE_USAGE_STATS},
                    123);
        } else {
            // Permission has already been granted
            return true;
        }
        return false;
    }

    public static boolean checkAppUsagePermission(Context context) {
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        if(usageStatsManager == null) {
            return false;
        }
        long currentTime = System.currentTimeMillis();
        // try to get app usage state in last 1 min
        List<UsageStats> stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, currentTime - 60 * 1000, currentTime);
        if (stats.size() == 0) {
            return false;
        }

        return true;
    }

    public static void requestAppUsagePermission(Context context) {
        Intent intent = new Intent(android.provider.Settings.ACTION_USAGE_ACCESS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void testDex() {
    }

    private void startServiceExample(){
        startActivity(new Intent(this, ServiceDemoActivity.class));
    }

    private void startCCExample(){
        //或 异步调用，不需要回调结果
//        String callId = CC.obtainBuilder("test_module")
//                .setActionName("showActivity")
//                .build()
//                .callAsync();
    }


    private void startZuoYeBang(String fname) {
        fname = Environment.getExternalStorageDirectory()+"/"+"test.jpg";
        File file = new File(fname);
        if (file == null || !file.exists() || file.length() == 0 || !file.getName().endsWith(".jpg")) {
//            this.mainControl.showToast("要输入的图片必须是一个可以读写的JPG文件.");
            Log.e("TAG", "要输入的图片必须是一个可以读写的JPG文件.");
            return;
        }
        file.setReadable(true, false);
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setComponent(new ComponentName("com.baidu.homework", "com.baidu.homework.activity.homework.AutoAnswerActivity"));
            intent.putExtra("INPUT_IMG_FILE", file.getPath());
            intent.putExtra("INPUT_USE_OCR", true);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            this.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void transformString(){

        String string = FileUtils.readTxt(SD_PATH + File.separator + "temp.txt");
        Log.i("zzx", "transformString: string="+string);
        String regex = "/n";
        String result = string.replaceAll(regex, "\n");
        FileUtils.addStrToTxt(SD_PATH + File.separator + "result.txt", result);
        Log.i("zzx", "transformString: string="+result);
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
