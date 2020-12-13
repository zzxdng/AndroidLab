package com.zinc.example.androidlab.push_demo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zinc.example.androidlab.MainActivity;
import com.zinc.example.androidlab.R;
import com.zinc.example.androidlab.util.SoftReferenceManager;

/**
 * Author: zhao zhongxin
 * Create: 2020/12/13
 * Describe:
 */
public class NotificationActivity extends AppCompatActivity {

    private Button mNotificationBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_demo);
        mNotificationBtn = findViewById(R.id.notifyBtnId);

        mNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                notification(v.getContext(), "测试通知消息");
                sendNotification("测试标题", "测试通知消息");
            }
        });
    }

    private static final int NOTIFY_ID=123;


    private void notification(Context context, String msg){
        NotificationManager mNotificationManager = SoftReferenceManager.getInstance().getSystemService(context, NOTIFICATION_SERVICE);

        mNotificationManager.cancel(NOTIFY_ID);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            //创建一个message通道，名字为消息
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel("message", "消息", NotificationManager.IMPORTANCE_HIGH);
            }

            mBuilder.setContentTitle("推送消息")//设置通知栏标题
                    .setContentText(msg) //<span style="font-family: Arial;">/设置通知栏显示内容</span>
                    .setContentIntent(getDefaultIntent(context,msg)) //设置通知栏点击意图
                    //  .setNumber(number) //设置通知集合的数量
                    .setTicker("测试通知来啦") //通知首次出现在通知栏，带上升动画效果的
                    .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                    .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
                    .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                    .setOngoing(false)//true，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                    .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                    //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                    .setSmallIcon(R.mipmap.ic_launcher);//设置通知小ICON
        }else {
            mBuilder.setContentTitle("推送消息")//设置通知栏标题
                    .setContentText(msg) //<span style="font-family: Arial;">/设置通知栏显示内容</span>
                    .setContentIntent(getDefaultIntent(context,msg)) //设置通知栏点击意图
                    //  .setNumber(number) //设置通知集合的数量
                    .setTicker("测试通知来啦") //通知首次出现在通知栏，带上升动画效果的
                    .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                    .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                    .setOngoing(false)//true，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                    .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                    //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                    .setSmallIcon(R.mipmap.ic_launcher);//设置通知小ICON
        }

        mNotificationManager.notify(NOTIFY_ID, mBuilder.build());
    }

    public PendingIntent getDefaultIntent(Context context, String msg){
        Intent intent=new Intent(context, MainActivity.class);
        intent.putExtra("message",msg);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager  = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    private void sendNotification(String title, String content) {
        //创建一个message通道，名字为消息
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel("message", "消息", NotificationManager.IMPORTANCE_HIGH);
        }
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, R.string.app_name, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "message");
        builder.setContentIntent(pendingIntent).setAutoCancel(true).setSmallIcon(R.mipmap.ic_launcher).setTicker("提示消息").setWhen(System.currentTimeMillis())
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher)).setContentTitle(title).setContentText(content);
        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

}
