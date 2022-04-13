package com.example.sharedpreferencestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button savaData=findViewById(R.id.save_data);
        savaData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name","Tom");
                editor.putInt("age",28);
                editor.putBoolean("married",false);
                editor.apply();
            }
        });
        Button restoreData=findViewById(R.id.restore_data);
        restoreData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPreferences pref =getSharedPreferences("data",MODE_PRIVATE);
                String name=pref.getString("name","");
                int age=pref.getInt("age",0);
                Boolean married =pref.getBoolean("married",false);
                Toast.makeText(MainActivity.this,"name:"+name+"age:"+age+"married:"+married,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
