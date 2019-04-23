package com.zinc.example.androidlab.load_sir_demo;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadLayout;
import com.kingja.loadsir.core.LoadService;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class LoadLayoutHelper {

    public Callback getRealCurrentCallback(LoadService loadService){
        Callback curCallback = null;
        Map<Class<? extends Callback>, Callback> realCallbacks = new HashMap<>();
        try {
            Field loadLayout = loadService.getClass().getDeclaredField("loadLayout");
            loadLayout.setAccessible(true);
            LoadLayout realLoadLayout = (LoadLayout)loadLayout.get(loadService);
            Field callbacks = realLoadLayout.getClass().getDeclaredField("callbacks");
            callbacks.setAccessible(true);
            realCallbacks = (Map<Class<? extends Callback>, Callback>) callbacks.get(realLoadLayout);
            curCallback = realCallbacks.get(loadService.getCurrentCallback());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return curCallback;
    }
}
