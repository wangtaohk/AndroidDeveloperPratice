package com.example.networktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView resopnseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendRequest=findViewById(R.id.send_request);
        resopnseText=findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()  == R.id.send_request){
//            sendRequestWithHttpsURLConnection();
            sendRequestWithOkHttp();
        }
    }
//    使用HttpsURLConnection实现请求
    private void sendRequestWithHttpsURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpsURLConnection connection=null;
                BufferedReader reader=null;
                try{
                    URL url=new URL("https://www.qq.com");
                    connection=(HttpsURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
//                    connection.setRequestMethod("POST");
//                    DataOutputStream out=new DataOutputStream(connection.getOutputStream());
//                    out.writeBytes("wd=http");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in =connection.getInputStream();
                    reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder response=new StringBuilder();
                    String line;
                    int i=0;
                    while ((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    Log.d("MainActivity.this","tiaoshi"+connection.getURL().toString());
                    showResponse(response.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(reader!=null){
                        try{
                            reader.close();
                        }catch (IOException e ){
                            e.printStackTrace();
                        }
                    }
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
//使用OkHttp实现请求
    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
//                    RequestBody requestBody=new FormBody.Builder()
//                            .add("username","admin")
//                            .add("password","123456")
//                            .build();
                    Request request=new Request.Builder()
//                            .url("https://www.qq.com")
//                            .url("http://www.w3school.com.cn/example/xmle/simple.xml")
                            .url("https://www.runoob.com/try/ajax/json_demo.txt")
//                            .post(requestBody)
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    showResponse(responseData);
//                    parseXMLWithPull(responseData);
//                    parseXMLWithSAX(responseData);
//                    parseJSONWithJSONObject(responseData);
                    parseJSONWithGSON(responseData);
                }catch(Exception e ){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resopnseText.setText(response);
            }
        });
    }
    //PULL方式解析XML
    private void parseXMLWithPull(String xmlData){
        try{
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser=factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType =xmlPullParser.getEventType();
            String name="";
            String price="";
            String description="";
            String calories="";
            while(eventType!=XmlPullParser.END_DOCUMENT){
                String nodeName=xmlPullParser.getName();
                switch(eventType){
                    case XmlPullParser.START_TAG:{
                        if("name".equals(nodeName)){
                            name=xmlPullParser.nextText();
                        }else if("price".equals(nodeName)){
                            price=xmlPullParser.nextText();
                        }else if("description".equals(nodeName)){
                            description=xmlPullParser.nextText();
                        }else if("calories".equals(nodeName)){
                            calories=xmlPullParser.nextText();
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG:{
                        if("food".equals(nodeName)){
                            Log.d("MainActivity","tiaoshi id is: "+name);
                            Log.d("MainActivity","tiaoshi name is: "+price);
                            Log.d("MainActivity","tiaoshi version is: "+description);
                            Log.d("MainActivity","tiaoshi version is: "+calories);
                        }
                        break;
                    }
                    default:
                        break;
                }
                eventType=xmlPullParser.next();
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
    //SAX方式解析XML
    private void parseXMLWithSAX(String xmlData){
        try{
            SAXParserFactory factory=SAXParserFactory.newInstance();
            XMLReader xmlReader=factory.newSAXParser().getXMLReader();
            ContentHandler handler=new ContentHandler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //使用JSONObject解析JSON
    private void parseJSONWithJSONObject(String jsonData){
        try{
            JSONArray jsonArray=new JSONArray(jsonData);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String name=jsonObject.getString("name");
                String num=jsonObject.getString("num");
                String sites=jsonObject.getString("sites");
                Log.d("MainActivity","tiaoshi name is ："+name);
                Log.d("MainActivity","tiaoshi name is ："+num);
                Log.d("MainActivity","tiaoshi name is ："+sites);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void parseJSONWithGSON(String jsonData){

        Gson gson=new Gson();
        List<app> appList =gson.fromJson(jsonData,new TypeToken<List<app>>(){}.getType());
        for(app App :appList){
            Log.d("MainActivity","name is "+App.getName());
        }
    }
}
//使用GSON解析JSON


