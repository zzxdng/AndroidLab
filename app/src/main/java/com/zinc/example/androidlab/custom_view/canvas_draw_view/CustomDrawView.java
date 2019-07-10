package com.zinc.example.androidlab.custom_view.canvas_draw_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Author: zhao zhongxin
 * Create: 2019/6/19
 * Describe:自定义绘图View
 */
public class CustomDrawView extends View{


    public CustomDrawView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //创建画笔
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        canvas.drawText("画矩形：", 100, 800, paint);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(600, 600, 800, 800, paint);

    }
}
