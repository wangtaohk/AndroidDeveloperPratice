package com.example.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,4);
        db=dbHelper.getWritableDatabase();
        Button createDatabase=findViewById(R.id.create_database);
        Button addData=findViewById(R.id.add_data);
        createDatabase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });
        addData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ContentValues values=new ContentValues();
                values.put("name","the da vinci code");
                values.put("author","dan brown");
                values.put("pages",454);
                values.put("price",16.96);
                db.insert("Book",null,values);
                values.clear();
                values.put("name","the lost symbol");
                values.put("author","dan brown");
                values.put("pages",510);
                values.put("price",19.95);
                db.insert("Book",null,values);
            }
        });
        Button updateData=findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ContentValues values=new ContentValues();
                values.put("price",10.90);
                db.update("Book",values,"name=?",new String [] {"the da vinci code"});
            }
        });
        Button deleteData=findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                db=dbHelper.getWritableDatabase();
                db.delete("Book","pages>?",new String [] {"500"});
            }
        });
        Button queryData=findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Cursor cursor=db.query("Book",null,null,null,null,null,null);
                //Cursor cursor=db.rawQuery("select * from Book where name=? ",new String [] {"the da vinci code"});
                if(cursor.moveToFirst()){
                    do{
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String author=cursor.getString(cursor.getColumnIndex("author"));
                        int pages=cursor.getInt(cursor.getColumnIndex("pages"));
                        Double price=cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity",name+author+pages+price+"tiaoshi");
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
    });
    }
}
