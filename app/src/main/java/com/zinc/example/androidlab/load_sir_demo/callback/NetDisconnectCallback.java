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
public class NetDisconnectCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_net_disconnect_hint;
    }

    //当前Callback的点击事件，如果返回true则覆盖注册时的onReloa()，如果返回false则两者都执行，先执行onReloadEvent()。
    @Override
    protected boolean onReloadEvent(final Context context, View view) {
        view.findViewById(R.id.iv_net_disconnect_hint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了去设置按钮！", Toast.LENGTH_SHORT).show();
            }
        });
        return false;
    }

    //是否在显示Callback视图的时候显示原始图(SuccessView)，返回true显示，false隐藏
    @Override
    public boolean getSuccessVisible() {
        return super.getSuccessVisible();
    }

    //将Callback添加到当前视图时的回调，View为当前Callback的布局View
    @Override
    public void onAttach(Context context, View view) {
        super.onAttach(context, view);
    }

    //将Callback从当前视图删除时的回调，View为当前Callback的布局View
    @Override
    public void onDetach() {
        super.onDetach();
    }
}
