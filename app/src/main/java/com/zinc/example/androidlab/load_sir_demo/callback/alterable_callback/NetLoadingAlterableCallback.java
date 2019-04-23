package com.zinc.example.androidlab.load_sir_demo.callback.alterable_callback;

import com.kingja.loadsir.callback.Callback;

/**
 * author： zhao zhongxin
 * date： 2018/11/6
 * description：
 */
public class NetLoadingAlterableCallback extends Callback {
    private int mStatus = 0;

    public NetLoadingAlterableCallback(int status) {
        mStatus = status;
    }

    @Override
    protected int onCreateView() {
        return 0;
    }
}
