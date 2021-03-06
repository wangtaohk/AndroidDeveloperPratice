package com.example.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
//        瀑布流布局管理器
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
//        线性布局管理器
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter((fruitList));
        recyclerView.setAdapter(adapter);

    }
    private  void initFruits(){
        for(int i=0;i<20;i++){
            Fruit apple=new Fruit(getRandomLengthName("apple"),R.drawable.ic_launcher_background);
            fruitList.add(apple);
        }
    }
    private String getRandomLengthName(String name){
        Random random = new Random();
        int length=random.nextInt(20) + 1;
        StringBuilder builder =new StringBuilder();
        for (int i=0; i<length;i++){
            builder.append(name);
        }
        return builder.toString();
    }
}
