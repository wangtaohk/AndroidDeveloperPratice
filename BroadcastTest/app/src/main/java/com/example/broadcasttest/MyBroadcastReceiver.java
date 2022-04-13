package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        String data=intent.getStringExtra("key");
        //Toast.makeText(context,"接受到了广播,action:"+ action +",data:"+data,Toast.LENGTH_SHORT).show();
        Log.d("MyBroadcastReceiver","接受到了广播,action:"+ action +",data:"+data);
    }
}
