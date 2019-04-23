package com.zinc.example.androidlab.viewpagerdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zinc.example.androidlab.R;

import java.util.List;

public class GalleryViewPagerAdapter extends PagerAdapter{

    private Context mContext;
    private List<String> mStringList;

    public GalleryViewPagerAdapter(Context context, List<String> data) {
        mContext = context;
        mStringList = data;

    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.item_viewpager_demo, null);
        TextView textView = view.findViewById(R.id.tv_viewpager_demo_id);
        textView.setText(mStringList.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return mStringList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }
}
