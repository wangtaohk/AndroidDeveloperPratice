package com.example.fragmenttest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById( R.id.button);
        replaceFragment(new RightFragment());
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button:
                        replaceFragment(new AnotherRightFragment());
                        break;
                    default:
                        break;
                }
            }
        });
    }
    private void replaceFragment(Fragment fragment){
//        FragmentManager fragmentManager=getSupportFragmentManager();
//        FragmentTransaction transaction =((FragmentManager) fragmentManager).beginTransaction();
//        transaction.replace(R.id.right_layout,fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
    }
}
