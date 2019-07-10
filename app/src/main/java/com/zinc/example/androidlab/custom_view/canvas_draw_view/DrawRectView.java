package com.zinc.example.androidlab.custom_view.canvas_draw_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: zhao zhongxin
 * Create: 2019/6/19
 * Describe:
 */
public class DrawRectView extends View {
    private List<Rect> mRectList = new ArrayList<>();
    public DrawRectView(Context context, List<Rect> rectList) {
        super(context);
        mRectList.clear();
        mRectList.addAll(rectList);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        for(Rect rect : mRectList){
            canvas.drawRect(rect, paint);
        }


    }
}
