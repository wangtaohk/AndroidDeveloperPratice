package com.example.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FirstActivity","启动"+this.toString());
        setContentView(R.layout.first_layout);
        Button butToast = findViewById(R.id.butToast);
        butToast.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(FirstActivity.this,
                        "Click me!",Toast.LENGTH_SHORT).show();
            }
        });
        Button butBack = findViewById(R.id.butBack);
        butBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button butNewActivity = findViewById(R.id.butNewActivity);
        butNewActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                显示Intent
//                Intent intent = new Intent(FirstActivity.this,secondActivity.class);
//                startActivity(intent);
//                隐式Intent
//                Intent intent = new Intent("com.example.ActivityTest.ACTION_START");
//                intent.addCategory("com.example.ActivityTest.MY_CATEGORY");
//                startActivity(intent);
//                Intent intent =new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("http://www.baidu.com"));
//                startActivity(intent);
//                Intent intent=new  Intent(Intent.ACTION_VIEW);
//                intent.addCategory("android.intent.category.BROWSABLE");
//                intent.setData(Uri.parse("http://www.sougou.com"));
//                startActivity(intent);
//                Intent intent=new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:10086"));
//                startActivity(intent);
                String data="HELLO WAI XING REN！";
                Intent intent =new Intent("com.example.ActivityTest.ACTION_START");
                intent.putExtra("secondActivity",data);
                startActivity(intent);

            }
        });
        Button butReturnData=findViewById(R.id.butReturnData);
        butReturnData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.ActivityTest.ACTION_START");
                startActivityForResult(intent,1);
            }
        });
        Button butStandard =findViewById(R.id.butStandard);
        butStandard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, R.string.toast_add, Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, R.string.toast_remove, Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnData = data.getStringExtra("requestData");
                    Toast.makeText(FirstActivity.this, "返回数据为：" + returnData, Toast.LENGTH_LONG).show();
                }
                break;
            default:
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
