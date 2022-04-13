package com.example.servicetest;

import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class MyService extends Service {

    private DownloadBinder mBinder=new DownloadBinder();
    private String downloadUri="http://gdown.baidu.com/data/wisegame/55dc62995fe9ba82/jinritoutiao_448.apk";
    private DownloadManager downloadManager;
    private long signId;//下载任务标志

    class DownloadBinder extends Binder {
        //开始下载
        public void startDownload(){
            DownloadManager.Request request=new DownloadManager.Request(Uri.parse(downloadUri));
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
            request.setTitle("下载");
            request.setDescription("今日头条正在下载");
            request.setAllowedOverRoaming(false);
            request.setDestinationInExternalFilesDir(MyService.this, Environment.DIRECTORY_DOWNLOADS,"mydown");
            downloadManager=(DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            signId=downloadManager.enqueue(request);
            Log.d("MyService","startDownload executed");
        }
        //暂停下载
        public void pauseDownload(){
            downloadManager.remove(signId);
        }
        //
        public int getProgress(){
            Log.d("MyService","getProgress executed");
            return 0;
        }
    }
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate executed");
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("channel_001","name",NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableVibration(false);
            notificationManager.createNotificationChannel(notificationChannel);
            Notification notification = new NotificationCompat.Builder(this, "channel_001")
                    .setContentText("this is content text")
                    .setContentTitle("this is content title")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setPriority(Notification.DEFAULT_SOUND)
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setContentIntent(pi)
                    .setLights(Color.BLUE, 2000, 1000)
                    .build();
//            notificationManager.notify(2,notification);
            startForeground(3, notification);
        }
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.d("MyService","onStartCommand executed");
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("MyService","onDestroy executed");
    }
}
