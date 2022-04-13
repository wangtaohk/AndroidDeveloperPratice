package com.example.myapplication;

import android.os.AsyncTask;
import android.widget.ProgressBar;

public class DownloadTask extends AsyncTask<Void,Integer,Boolean> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try{
            while (true){
                int downloadPercent=1;
                publishProgress(downloadPercent);
                if(downloadPercent>=100){
                    break;
                }
            }
        }catch ( Exception e){
            return false;
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
