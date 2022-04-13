package com.example.activitytest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class secondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Button butPutData=findViewById(R.id.butPutData);
        butPutData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =getIntent();
                String data=intent.getStringExtra("secondActivity");
                Toast.makeText(secondActivity.this, "传递数据为"+data, Toast.LENGTH_SHORT).show();
            }
        });
        Button butRequestData=findViewById(R.id.butRequestData);
        butRequestData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.putExtra("requestData","HELLO EARTH PEOPLE");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        Button butOpenThirdActivity =findViewById(R.id.butOpenThirdActivity);
        butOpenThirdActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(secondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
    //每个活动中都加上类似启动活动代码应是极好的
    public static void  actionStart(Context context,String data1,String data2){
        Intent intent =new Intent(context,secondActivity.class);
        intent.putExtra("param1",data1);
        intent.putExtra("param2",data2);
        context.startActivity(intent);
    }
}
