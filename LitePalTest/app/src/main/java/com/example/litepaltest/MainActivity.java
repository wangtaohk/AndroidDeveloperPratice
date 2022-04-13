package com.example.litepaltest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        book=new Book();
        Button createDatabase=findViewById(R.id.create_database);
        //创建数据库
        Button addData=findViewById(R.id.add_data);
        createDatabase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });
        //添加数据
        addData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();
            }
        });
        //更新数据
        Button updateData=findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //第一种更新方式

                book.setName("The Lost Symbol");
                book.setAuthor("Dan Brown");
                book.setPages(510);
                book.setPrice(19.95);
                book.setPress("Unknow");
                book.save();
                book.setPrice(10.99);
                book.save();

                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name=? and author=?","The Lost Symbol","Dan Brown");
//                book.setToDefault("pages");
//                book.updateAll();
            }
        });
        //删除数据
        Button deleteData=findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class,"price < ?","15");

            }
        });
        //查询数据
        Button queryData=findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                List<Book> books=DataSupport.findAll(Book.class);
                //List<Book> bookd=DataSupport.select("").where("").order("").find(Book.class);
                for (Book book:books){
                    Log.d("MainActivity","tiaoshi"+"name:"+book.getName());
                    Log.d("MainActivity","tiaoshi"+"author:"+book.getAuthor());
                    Log.d("MainActivity","tiaoshi"+"pages:"+book.getPages());
                    Log.d("MainActivity","tiaoshi"+"price:"+book.getPrice());
                    Log.d("MainActivity","tiaoshi"+"press:"+book.getPress());
                }
            }
    });
    }
}
