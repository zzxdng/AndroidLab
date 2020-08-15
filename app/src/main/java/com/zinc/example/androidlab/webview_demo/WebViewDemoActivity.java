package com.zinc.example.androidlab.webview_demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;

import com.zinc.example.androidlab.R;

public class WebViewDemoActivity extends AppCompatActivity {
    private static final String TAG = "WebViewDemoActivity";

    private final String DARK_NIHGT_MODE = "暗黑模式";
    private final String DAYTIME_MODE = "白天模式";

    WebView mWebView;
    private Button mChangeModeBtn;
    private long mStartTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_demo);

        initView();
        initWebViewFragment();

        /**
        <script type="text/javascript">

        <!--这里取到的是 android端传过来的数据-->
                function setDarkMode(data){
            if(data == true){
                <!--设置为暗黑模式-->
            }else {
                <!--其他默认为正常白色背景-->
            }
        }

    </script>

         **/
    }

    private void initView() {
        FrameLayout frameLayout = findViewById(R.id.fl_content_id);
        Fragment webViewFragment = new WebViewTestFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.add(R.id.fl_content_id, webViewFragment);
        trans.show(webViewFragment);
        trans.commitAllowingStateLoss();
    }

    private void initWebViewFragment() {

    }

    private void changeMode() {
        if(mChangeModeBtn.getText().equals(DARK_NIHGT_MODE)){
            mWebView.loadUrl("javascript:setDarkMode(true)");
            mChangeModeBtn.setText(DAYTIME_MODE);
        }else {
            mWebView.loadUrl("javascript:setDarkMode(false)");
            mChangeModeBtn.setText(DARK_NIHGT_MODE);
        }
    }

    private void callJsFunction(String data){
        mWebView.loadUrl("javascript:setDarkMode("+data+")");

    }


    private class JsObj {

        public JsObj() {
        }

        @JavascriptInterface
        public String getData() {
            Log.i("zzx", "getData: ");
            String data = "";
            return data;
        }


    }
}
