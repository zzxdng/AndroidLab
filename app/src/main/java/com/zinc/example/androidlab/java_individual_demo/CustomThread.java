package com.zinc.example.androidlab.java_individual_demo;


public class CustomThread extends Thread {

    private static final String TAG = "CustomThread";

    private final Object mLock = new Object();

    @Override
    public void run() {
        System.out.println("tag:"+TAG + " helios "+ "run: ");
        long currentTime = System.currentTimeMillis();
        long duration = 3500;
        long gap = 200L;
        while (true){
            synchronized (mLock){
                try {
                    mLock.wait(gap);
                    if(System.currentTimeMillis() > (currentTime + duration)){
                        System.out.println("tag:"+TAG + " helios "+ " timeout!");
                        return;
                    }
                    System.out.println("tag:"+TAG + " helios "+ "run: gap");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("tag:"+TAG + " helios "+ "InterruptedException e："+e.toString());
                    return;
                }
            }

        }
    }
}
