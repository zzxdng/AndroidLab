package com.zinc.example.androidlab.load_sir_demo.callback;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.kingja.loadsir.callback.Callback;
import com.zinc.example.androidlab.R;

/**
 * author： zhao zhongxin
 * date： 2018/10/19
 * description：
 */
public class ErrorCallback extends Callback {

    private boolean isClickPic = false;

    @Override
    protected int onCreateView() {
        return R.layout.layout_error_callback;
    }

    @Override
    protected boolean onReloadEvent(final Context context, View view) {
        view.findViewById(R.id.iv_net_error_hint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了网络错误图片！", Toast.LENGTH_SHORT).show();
            }
        });
        return false;
    }



    @Override
    public void onAttach(final Context context, final View view) {
        super.onAttach(context, view);
    }
}
