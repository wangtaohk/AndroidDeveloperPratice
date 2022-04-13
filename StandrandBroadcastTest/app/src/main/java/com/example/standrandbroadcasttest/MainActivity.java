package com.example.standrandbroadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager=localBroadcastManager.getInstance(this);
        Button butBroadcast=findViewById(R.id.butBroadcast);
        butBroadcast.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.standrandbroadcasttest.LOCAL_BROADCAST");
                intent.setPackage("com.example.standrandbroadcasttest");
               //intent.setPackage("com.example.broadcasttest2");
                //sendBroadcast(intent);
                //sendOrderedBroadcast(intent,null);
                localBroadcastManager.sendBroadcast(intent);
            }
        });
        intentFilter =new IntentFilter();
        intentFilter.addAction("com.example.standrandbroadcasttest.LOCAL_BROADCAST");
        localReceiver=new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    class LocalReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"received local broadcast",Toast.LENGTH_SHORT).show();
        }
    }
}
