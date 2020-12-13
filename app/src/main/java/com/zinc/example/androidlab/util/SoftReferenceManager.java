package com.zinc.example.androidlab.util;

import android.content.Context;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 减少对SystemService重复创建而导致性能和binder泄漏问题
 */
public class SoftReferenceManager {
    private Map<String, SoftReference> mSystemServiceMap;
    private Map<String, SoftReference> mCommonMap;

    private static class InstanceHolder {
        private static final SoftReferenceManager INSTANCE = new SoftReferenceManager();
    }

    public static SoftReferenceManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private SoftReferenceManager(){
        mSystemServiceMap = new HashMap<>();
        mCommonMap = new HashMap<>();
    }

    public <T> T getSystemService(Context context, String serviceName){
        if(mSystemServiceMap.containsKey(serviceName)){
            SoftReference<T> softReference = mSystemServiceMap.get(serviceName);
            T systemService = softReference.get();
            if(systemService != null){
                return systemService;
            }
        }
        T systemService = (T) context.getSystemService(serviceName);
        mSystemServiceMap.put(serviceName, new SoftReference<>(systemService));
        return systemService;
    }

    public boolean contains(String key){
        return mCommonMap.containsKey(key);
    }

    public <T> void set(String key, T t){
        mCommonMap.put(key, new SoftReference<>(t));
    }

    public <T> T get(String key){
        if(contains(key)){
            SoftReference<T> softReference = mCommonMap.get(key);
            if(softReference != null){
                return softReference.get();
            }
        }
        return null;
    }

    public void remove(String key){
        if(contains(key)){
            mCommonMap.remove(key);
        }
    }

    public void clearSystemService(){
        if(!mSystemServiceMap.isEmpty()){
            mSystemServiceMap.clear();
        }
        if(!mCommonMap.isEmpty()){
            mCommonMap.clear();
        }
    }
}
