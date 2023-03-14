package com.example.notificationstyle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class NormalNotification extends AppCompatActivity {
    private NotificationManager mNotificationManager;
    private NotificationChannel mNotificationChannel;
    private Context mContext;
    private int mID=1000;
    private Intent mIntent;
    private PendingIntent mPendingIntent;
    private Random mRrandom;
    private NotificationCompat.Builder notificationBuilder;
    private int len;


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    notificationBuilder.setProgress(100,len,false);
                    mNotificationManager.notify(0, notificationBuilder.build());

                    break;
                case 1:
                    break;
                default:
                    break;
            }
        }

    };


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_notification);

        mContext=getApplicationContext();
        mNotificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mNotificationChannel=new NotificationChannel("channel","普通通知",NotificationManager.IMPORTANCE_HIGH);
        mNotificationManager.createNotificationChannel(mNotificationChannel);
        mIntent=new Intent(this,MainActivity.class);
        mPendingIntent=PendingIntent.getActivity(this,0,mIntent,PendingIntent.FLAG_IMMUTABLE);
        mRrandom=new Random();
    }

    public void sendBasicNotification(View view) throws InterruptedException {
        int randomText=mRrandom.nextInt(10000);
        notificationBuilder=new NotificationCompat.Builder(mContext,"channel")
                .setSmallIcon(R.drawable.image)
                .setContentTitle("basic_"+mID)
                .setContentIntent(mPendingIntent)
                .setAutoCancel(true)
                .setContentText("19961226 "+randomText);

        mNotificationManager.notify(mID,notificationBuilder.build());
        mID+=1;
    }

    public void sendFullScreenNotification(View view) throws InterruptedException {
        int randomText=mRrandom.nextInt(10000);
        notificationBuilder=new NotificationCompat.Builder(mContext,"channel")
                .setSmallIcon(R.drawable.image)
                .setContentTitle("fullscreen_"+mID)
                .setContentText("19961226 "+randomText)
                .setFullScreenIntent(mPendingIntent,true);
        Toast.makeText(mContext,"请退回到桌面", Toast.LENGTH_SHORT).show();
        Thread.sleep(2000);
        mNotificationManager.notify(mID,notificationBuilder.build());
        mID+=1;
    }

    public void sendOngoingNotification(View view){
        int randomText=mRrandom.nextInt(10000);
        notificationBuilder=new NotificationCompat.Builder(mContext,"channel")
                .setSmallIcon(R.drawable.image)
                .setContentTitle("ongoing_"+mID)
                .setContentText("19961226 "+randomText)
                .setOngoing(true);
        mNotificationManager.notify(mID,notificationBuilder.build());
        mID+=1;
    }

    public void sendMinibarNotification(View view) throws InterruptedException {
        int randomText=mRrandom.nextInt(10000);
        notificationBuilder=new NotificationCompat.Builder(mContext,"channel")
                .setSmallIcon(R.drawable.image)
                .setContentTitle("minibar_"+mID)
                .setContentText("19961226 "+randomText)
                .setContentIntent(mPendingIntent);

        Toast.makeText(mContext,"请退回到桌面", Toast.LENGTH_SHORT).show();
        Thread.sleep(2000);
        mNotificationManager.notify(mID,notificationBuilder.build());
        mID+=1;
    }




    public void sendProgreassNotification(View view){
        int randomText=mRrandom.nextInt(10000);
        notificationBuilder=new NotificationCompat.Builder(mContext,"channel")
                .setSmallIcon(R.drawable.image)
                .setContentTitle("basic_"+mID)
                .setContentIntent(mPendingIntent)
                .setAutoCancel(true)
                .setProgress(100,0,false)
                .setContentText("19961226 "+randomText);
        mNotificationManager.notify(0,notificationBuilder.build());
        new DownLoadThread().start();
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_set://监听菜单按钮
                Intent intent = new Intent(NormalNotification.this, Settings.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }



    private class DownLoadThread extends Thread{
        private Timer timer = new Timer();
        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub

                    Message msg = new Message();
                    msg.what = 0;
                    msg.obj = len;
                    handler.sendMessage(msg);

                    if(len == 100){
                        timer.cancel();
                        handler.sendEmptyMessage(1);
                    }

                }
            }, 0, 1000);
            len = 0;
            try {
                while(len < 100){
                    len++;
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


}