package com.example.notificationstyle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.Random;

public class BigNotification extends AppCompatActivity {
    private NotificationManager mNotificationManager;
    private NotificationChannel mNotificationChannel;
    private Context mContext;
    private int mID=1000;
    private Intent mIntent;
    private PendingIntent mPendingIntent;
    private Random mRrandom;
    private Bitmap small;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_notification);

        mContext=getApplicationContext();
        mNotificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mNotificationChannel=new NotificationChannel("channel","testChannel",NotificationManager.IMPORTANCE_HIGH);
        mNotificationManager.createNotificationChannel(mNotificationChannel);
        mIntent=new Intent(this,MainActivity.class);
        mPendingIntent=PendingIntent.getActivity(this,0,mIntent,PendingIntent.FLAG_IMMUTABLE);
        mRrandom=new Random();
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
                Intent intent = new Intent(BigNotification.this, Settings.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public void sendBigTextNotification(View view){

    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public void sendBigPictureNotification(View view){
        small= BitmapFactory.decodeResource(getResources(),R.drawable.a);
        int randomText=mRrandom.nextInt(10000);
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(mContext,"channel")
                .setSmallIcon(R.drawable.image)
                .setContentTitle("basic_"+mID)
                .setContentText("19961226"+randomText)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.image))
                .setStyle(new NotificationCompat
                        .BigPictureStyle()
                        .bigPicture(small)
                        .showBigPictureWhenCollapsed(true)
                        .setSummaryText("19961226"+randomText));

        mNotificationManager.notify(mID,notificationBuilder.build());
        mID+=1;
    }

    public void sendInboxNotification(View view){

    }

    public void sendMessagingNotification(View view){

    }

    public void sendConversationNotification(View view){

    }

    public void sendMediaNotification(View view){

    }

    public void sendDecoratedNotification(View view){

    }

    public void sendCustomNotification(View view){

    }

    public void sendBigCustomNotification(View view){

    }
}