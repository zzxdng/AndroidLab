package com.zinc.example.androidlab.load_sir_demo.callback;

import com.kingja.loadsir.callback.Callback;
import com.zinc.example.androidlab.R;

/**
 * author： zhao zhongxin
 * date： 2018/10/19
 * description：
 */
public class LoadingCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_loading_callback;
    }
}
