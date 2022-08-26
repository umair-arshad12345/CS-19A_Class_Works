package com.example.test.MyServices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class BoundService extends Service {
    public BoundService() {
    }
    Integer progress = 0;
    Boolean pause = false;
    public class ProgressBinder extends Binder{

        public BoundService getServiceContext(){
            return BoundService.this;
        }
    }

    Handler h = new Handler();
    ProgressBinder binder = new ProgressBinder();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public void SetPause(Boolean value){
        this.pause = value;
    }
    public Integer getProgress(){
        return progress;
    }
    public void start_progress(ProgressBar progressBar, TextView progress_text){

        Runnable r = new Runnable() {
            @Override
            public void run() {
                if(!pause) {
                    progress += 1;
                    progress_text.setText(progress.toString());
                    progressBar.setProgress(progress);
                }
                if(progress < 100 && h!=null){
                    h.postDelayed(this,1000);
                }else{
                    stopSelf();
                }
            }
        };
        h.postDelayed(r,1000);
    }
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Toast.makeText(this, "Task Removed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        h = null;
        Toast.makeText(this, "Service Destroy", Toast.LENGTH_SHORT).show();

    }
}