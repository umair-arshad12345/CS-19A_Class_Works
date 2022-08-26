package com.example.test.MyServices;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }
    Handler h = new Handler();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        start_progress();
        return super.onStartCommand(intent, flags, startId);
    }

    void start_progress(){
        final Integer[] progress = {0};

        Runnable r = new Runnable() {
            @Override
            public void run() {
                progress[0] += 1;
                if(progress[0] < 100 && h!=null){
//                    Toast.makeText(MyService.this, progress[0].toString(), Toast.LENGTH_SHORT).show();
                    System.out.println(progress[0].toString());
                    Intent i = new Intent();
                    i.putExtra("progress",progress[0]);
                    i.setAction("UpdateProgress");
                    sendBroadcast(i);
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