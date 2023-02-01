package com.example.notificationstyle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
//import android.support.design.widget.TextInputLayout;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;


public class SpecialNotification extends AppCompatActivity {

    private NotificationManager mNotificationManager;
    private NotificationChannel mNotificationChannel;
    private Context mContext;
    private int mID=4000;
    private Intent mIntent;
    private PendingIntent mPendingIntent;
    private Random mRrandom;
    private EditText mIDText;
    private EditText mContentTitle;
    private EditText mContentText;
    private EditText mSubText;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_notification);

        mContext=getApplicationContext();
        mNotificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mNotificationChannel=new NotificationChannel("channel2","特殊通知",NotificationManager.IMPORTANCE_HIGH);
        mNotificationManager.createNotificationChannel(mNotificationChannel);
        mIntent=new Intent(this,MainActivity.class);
        mPendingIntent=PendingIntent.getActivity(this,0,mIntent,PendingIntent.FLAG_IMMUTABLE);
        mRrandom=new Random();
        mIDText=findViewById(R.id.sid);
        mContentTitle=findViewById(R.id.stitle);
        mContentText=findViewById(R.id.stext);
        mSubText=findViewById(R.id.sstext);
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
                Intent intent = new Intent(SpecialNotification.this, Settings.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendSpecialBasic(View view){
        String idString=mIDText.getText().toString();
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(mContext,"channel2")
                .setSmallIcon(R.drawable.image)
                .setContentTitle(mContentTitle.getText())
                .setContentIntent(mPendingIntent)
                .setAutoCancel(true)
                .setContentText(mContentText.getText())
                .setSubText(mSubText.getText());
        if (!idString.equals("")) {
            mID = Integer.valueOf(idString);
        }
        mNotificationManager.notify(mID,notificationBuilder.build());
        mID+=1;
    }

    public void sendSpecialExpandable(View view){
        String idString=mIDText.getText().toString();
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(mContext,"channel2")
                .setSmallIcon(R.drawable.image)
                .setContentTitle(mContentTitle.getText())
                .setContentIntent(mPendingIntent)
                .setAutoCancel(true)
                .setSubText(mSubText.getText())
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(mContentText.getText()));
        if (!idString.equals("")) {
            mID = Integer.valueOf(idString);
        }
        mNotificationManager.notify(mID,notificationBuilder.build());
        mID+=1;

    }

    public void sendSpecialBullet(View view){
        String idString=mIDText.getText().toString();
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(mContext,"channel2")
                .setSmallIcon(R.drawable.image)
                .setContentTitle(mContentTitle.getText())
                .setContentIntent(mPendingIntent)
                .setAutoCancel(true)
                .setSubText(mSubText.getText())
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(mContentText.getText()));
        if (!idString.equals("")) {
            mID = Integer.valueOf(idString);
        }
        for(int i=0;i<100;i++){
            mNotificationManager.notify(mID,notificationBuilder.build());
            mID+=1;
        }
    }
}