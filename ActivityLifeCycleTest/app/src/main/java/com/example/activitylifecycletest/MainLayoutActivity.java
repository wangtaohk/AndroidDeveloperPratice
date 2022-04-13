package com.example.activitylifecycletest;

import android.content.Intent;
import android.nfc.Tag;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainLayoutActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        if(savedInstanceState !=null){
            String stringData=savedInstanceState.getString("tempStringData");
            int intData=savedInstanceState.getInt("tempIntData");
            Log.d(TAG,"临时字符串数据为："+stringData);
            Log.d(TAG,"临时整数数据为："+intData);
        }
        Button butNormalActivity =findViewById(R.id.butNormalActivity);
        butNormalActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLayoutActivity.this, NormalActivity.class);
                startActivity(intent);
            }
        });
        Button butDialogActivity =findViewById(R.id.butDialogActivity);
        butDialogActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainLayoutActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(MainLayoutActivity.this,"onStart",Toast.LENGTH_SHORT);
        Log.d(TAG,this.getLocalClassName()+"执行onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,this.getLocalClassName()+"执行onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,this.getLocalClassName()+"执行onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,this.getLocalClassName()+"执行onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,this.getLocalClassName()+"执行onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,this.getLocalClassName()+"执行onRestart");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        String dataString="临时数据";
        int dataInt=10;
        outState.putString("tempStringData",dataString);
        outState.putInt("tempIntData",dataInt);
    }
}
