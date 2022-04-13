package com.example.listviewtest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private  String [] data={"apple","banana","orange","watermelon","pear","grape","pineapple","strawberry","cherry"
    ,"mango","apple","banana","orange","watermelon","pear","grape","pineapple","strawberry","cherry"
            ,"mango","apple","banana","orange","watermelon","pear","grape","pineapple","strawberry","cherry"
            ,"mango"};
    private  List<Fruit> fruitList =new ArrayList<>();
    //private  static List<Activity> activities =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ArrayAdapter<String> adapter =new ArrayAdapter<String>(
//            MainActivity.this,android.R.layout.simple_list_item_1,data);
//        ListView listView = findViewById(R.id.list_view);
//        listView.setAdapter(adapter);
        initFruits();
        FruitAdapter adapter=new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit =fruitList.get(position);
                Toast.makeText(MainActivity.this,fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private  void initFruits(){
        for(int i=0; i<20;i++){
            Fruit fruit= new Fruit("apple",R.drawable.ic_launcher_background);
            fruitList.add(fruit);
        }
    }
}
