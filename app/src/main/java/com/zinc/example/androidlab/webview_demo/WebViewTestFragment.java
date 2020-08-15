package com.zinc.example.androidlab.webview_demo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.zinc.example.androidlab.R;


/**
 * Author: zhao zhongxin
 * Create: 2020/3/9
 * Describe:
 */
public class WebViewTestFragment extends Fragment {
    private static final String TAG = WebViewTestFragment.class.getSimpleName();

    private WebView mWebView;
    private WebView mWebView2;
    private View mView;

    private long mStartTime;
    private long mStartTime2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = View.inflate(getContext(), R.layout.fragment_web_view_demo, null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWebView = new WebView(getContext().getApplicationContext());
        mWebView2 = new WebView(getContext().getApplicationContext());

        initWebView(mWebView);
        initWebView(mWebView2);

        mWebView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.i("zzx", "onPageStarted 1: ");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i(TAG, "onPageFinished 1: "+(System.currentTimeMillis() - mStartTime));
            }
        });

        mWebView2.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.i("zzx", "onPageStarted 2: ");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i(TAG, "onPageFinished 2: "+(System.currentTimeMillis() - mStartTime2));
            }
        });

        mStartTime = System.currentTimeMillis();
        mStartTime2 = System.currentTimeMillis();


        setJs(mWebView2);


    }

    private void initWebView(WebView webView) {
        RelativeLayout relativeLayout = mView.findViewById(R.id.wv_container_id);
        relativeLayout.addView(webView);

//        setJs(webView);
    }

    private void setJs(WebView webView){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JsObj(), "Search");
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
