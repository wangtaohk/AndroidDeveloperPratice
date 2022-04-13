package com.example.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);
        Button butExit =findViewById(R.id.butExit);
        butExit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
                //kill process
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        Button butBrowser =findViewById(R.id.butBrowser);
        butBrowser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });
    }
}
