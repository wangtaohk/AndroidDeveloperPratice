package com.example.playaudiotest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private MediaPlayer mediaPlayer=new MediaPlayer();
    private VideoView videoView;
    private int flag=0;
    private static File file_music=null;
    private static File file_video=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button play=findViewById(R.id.play);
        Button pause=findViewById(R.id.pause);
        Button stop =findViewById(R.id.stop);
        RadioGroup musicAndVedio=findViewById(R.id.music_and_vedio);
        RadioButton radioMusic=findViewById(R.id.radio_music);
        RadioButton radioVedio=findViewById(R.id.radio_vedio);
        videoView=findViewById(R.id.video_view);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String [] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else{
            initMediaPlayer();
        }
        musicAndVedio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(group.getCheckedRadioButtonId()){
                    case R.id.radio_music:
                        if(videoView!=null){
                            videoView.stopPlayback();
                            videoView.suspend();
                        }
                        flag=0;
                        initMediaPlayer();
                        break;
                    case R.id.radio_vedio:
                        if(mediaPlayer!=null) {
                            mediaPlayer.reset();
                        }
                        flag=1;
                        initVideoPath();
                        break;
                    default:
                }
            }
        });
    }
    private void initMediaPlayer(){
        Log.d("MainActivity.this","tiaoshi   log");
        try{
            if(file_music==null) {
                file_music = new File(Environment.getExternalStorageDirectory(), "music.mp3");
            }
            mediaPlayer.setDataSource(file_music.getPath());
            mediaPlayer.prepare();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void initVideoPath(){
        try {
            if(file_video==null) {
                file_video = new File(Environment.getExternalStorageDirectory(), "movie.mp4");
            }
            videoView.setVideoPath(file_video.getPath());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    initMediaPlayer();
                }
                else{
                    Toast.makeText(this,"拒绝权限将无法使用程序",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.play:
                if(flag==0) {
                   // Log.d("MainActivity.this", mediaPlayer.isPlaying());
                    if (!mediaPlayer.isPlaying()) {
                        mediaPlayer.start();
                    }
                }else if(flag==1){
                    if(!videoView.isPlaying()){

                        videoView.start();
                    }
                }
                break;
            case R.id.pause:
                if(flag==0) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                    }
                }else if(flag==1){
                    if(videoView.isPlaying()){
                        videoView.pause();
                    }
                }
                break;
            case R.id.stop:
                if(flag==0) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.reset();
                        initMediaPlayer();
                    }
                }else if(flag==1){
                    if(videoView.isPlaying()){
                        videoView.resume();
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        if(videoView!=null){
            videoView.suspend();
        }
    }
}
