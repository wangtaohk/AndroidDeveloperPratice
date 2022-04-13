package com.example.layoutexcrise;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TitleLayout extends LinearLayout {
    public TitleLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
        Button butBack=findViewById(R.id.title_back);
        Button butEdit=findViewById(R.id.title_edit);
        butBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
        butEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"you clicked edit button",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
