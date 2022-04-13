package com.example.servicetest;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DownloadCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        if(intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)){
            long id=intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1);
            Toast.makeText(context,"编号："+id+"的下载任务已经完成！",Toast.LENGTH_SHORT).show();
        }else if(intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)){
            Toast.makeText(context, "别瞎点！！！", Toast.LENGTH_SHORT).show();
        }
    }
}
