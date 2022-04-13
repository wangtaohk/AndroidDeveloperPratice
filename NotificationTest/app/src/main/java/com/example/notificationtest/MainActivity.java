package com.example.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

//第一行代码中代码适用于android O及以下，此demo有所修改适配于android P
public class MainActivity extends AppCompatActivity {

    private Notification notification = null;
    private NotificationChannel mChanne=null;
    private NotificationManager notificationManager=null;
    private NotificationCompat.Builder builder=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice=findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.send_notice:
                        createNotification();
                            break;
                        default:
                            break;
                }
            }
        });
    }
    public void createNotification(){
        String id = "channel_001";
        String name = "name";
        Bitmap btm=BitmapFactory.decodeResource(getResources(),R.drawable.tutorials_select);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent=new Intent(MainActivity.this,NotificationActivity.class);
        PendingIntent pi=PendingIntent.getActivity(MainActivity.this,0,intent,0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//判断API
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
            setNotificationStyle(id,pi);
        }else{
            setNotificationStyle(id,pi);
        }
    }
    public void setNotificationStyle(String id,PendingIntent pi){
        Bitmap btm=BitmapFactory.decodeResource(getResources(),R.drawable.tutorials_select);
        builder= new NotificationCompat.Builder(MainActivity.this,"default")
                .setContentTitle("活动")
                .setContentText("您有一项新活动")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setOngoing(true)
                .setChannelId(id)//无效
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setTicker("new message")
                .setLargeIcon(btm)
                .setSmallIcon(R.drawable.brainstorming_sun_icon)
                .setNumber(30);
//                .setStyle(new NotificationCompat.InboxStyle()
//                        .addLine("M.Twain (Google+) Haiku is more than a cert...")
//                        .addLine("M.Twain Reminder")
//                        .addLine("M.Twain Lunch?")
//                        .addLine("M.Twain Revised Specs")
//                        .addLine("M.Twain ")
//                        .addLine("Google Play Celebrate 25 billion apps with Goo..")
//                        .addLine("Stack Exchange StackOverflow weekly Newsl...")
//                        .setBigContentTitle("6 new message")
//                        .setSummaryText("mtwain@android.com")
//                );
//                .setStyle(new NotificationCompat.BigTextStyle()
//                        .bigText("Stack Exchange StackOverflow weekly Newsl...M.Twain (Google+) Haiku is more than a cert...Google Play Celebrate 25 billion apps with Goo..")
//                        .setBigContentTitle("7 new message")
//                        .setSummaryText("wangtao@android.com")
//                        )
//                .setStyle(new NotificationCompat.BigPictureStyle()
//                        .bigLargeIcon(btm)
//                        .bigPicture(btm)
//                        .setBigContentTitle("8 new message")
//                        .setSummaryText("wangtao")
//                )
        //notification=builder.build();
        //notificationManager.notify(1,notification);
        //inn();
        //inn2();
        inn3();
    }
    public void inn(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int incr=0;incr<=100;incr+=5){
                    builder.setProgress(100,incr,false);
                    notificationManager.notify(2,builder.build());
                    try{
                        Thread.sleep(300);
                    }catch(InterruptedException e ){
                        Log.i("MainActivity.this","sleep failure");
                    }
                }
                builder.setContentText("download complete")
                        .setProgress(0,0,false);
                notificationManager.notify(2,builder.build());
            }
        }).start();
    }
    public void inn2(){
        builder.setProgress(0,0,true);
        notificationManager.notify(2,builder.build());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                builder.setProgress(100,100,false);

                notificationManager.notify(2,builder.build());
                builder.setContentText("Download complete")
                        .setProgress(0, 0, false);
                notificationManager.notify(2, builder.build());
            }
        }).start();
    }
    public void inn3(){
        RemoteViews contentViews=new RemoteViews(getPackageName(),R.layout.remoteview_layout);
        contentViews.setImageViewResource(R.id.imageNo,R.drawable.tutorials_select);
        contentViews.setTextViewText(R.id.titleNo,"自定义通知标题");
        contentViews.setTextViewText(R.id.textNo,"自定义通知内容");
        builder.setContent(contentViews);
        notificationManager.notify(3,builder.build());
    }
}
