package com.example.notificationstyle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;
import androidx.core.graphics.drawable.IconCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
    private Person person;
    private Person person1;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_notification);
        small= BitmapFactory.decodeResource(getResources(),R.drawable.a);

        mContext=getApplicationContext();
        mNotificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mNotificationChannel=new NotificationChannel("channel","testChannel",NotificationManager.IMPORTANCE_HIGH);
        mNotificationManager.createNotificationChannel(mNotificationChannel);
        mIntent=new Intent(this,MainActivity.class);
        mPendingIntent=PendingIntent.getActivity(this,0,mIntent,PendingIntent.FLAG_IMMUTABLE);
        mRrandom=new Random();
        person=new Person.Builder()
                .setName("小明")
                .setIcon(IconCompat.createWithBitmap(small))
                .build();
        person1=new Person.Builder()
                .setName("小张")
                .setIcon(IconCompat.createWithBitmap(small))
                .build();
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
        int randomText=mRrandom.nextInt(10000);
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(mContext,"channel")
                .setSmallIcon(R.drawable.image)
                .setContentTitle("bigtext_"+mID)
                .setContentText("19961226"+randomText)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.image))
                .setStyle(new NotificationCompat
                        .BigTextStyle()
                        .bigText("bigTextbigTextbigTextbigTextbigTextbigTextbigTextbigTextbigTextbigTextbigTextbigText" +
                                "bigTextbigTextbigTextbigTextbigTextbigTextbigTextbigTextbigTextbigTextbigTextbigText" +
                                "bigTextbigTextbigTextbigTextbigTextbigTextbigTextbigTextbigTextbigTextbigTextbigText" +
                                "bigTextbigTextbigTextbigTextbigTextbigTextbigTextbigTextbigTextbigTextbigTextbigText"));

        mNotificationManager.notify(mID,notificationBuilder.build());
        mID+=1;
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public void sendBigPictureNotification(View view){
        int randomText=mRrandom.nextInt(10000);
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(mContext,"channel")
                .setSmallIcon(R.drawable.image)
                .setContentTitle("bigpicture_"+mID)
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
        int randomText=mRrandom.nextInt(10000);
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(mContext,"channel")
                .setSmallIcon(R.drawable.image)
                .setContentTitle("inbox_"+mID)
                .setContentText("19961226"+randomText)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.image))
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("123")
                        .addLine("456")
                        .addLine("789"));
        mNotificationManager.notify(mID,notificationBuilder.build());
        mID+=1;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void sendMessagingNotification(View view){
        int randomText=mRrandom.nextInt(10000);
        @SuppressLint({"NewApi", "LocalSuppress"})
        ShortcutInfo shortcutInfo=new ShortcutInfo.Builder(mContext,"id")
                .setLongLived(true)
                .setIntent(new Intent(mContext,MainActivity.class).setAction(Intent.ACTION_VIEW))
                .setShortLabel("会话")
                .setIcon(Icon.createWithBitmap(small))
                .build();

        ShortcutManager shortcutManager= null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            shortcutManager = (ShortcutManager) mContext.getSystemService(Context.SHORTCUT_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            shortcutManager.pushDynamicShortcut(shortcutInfo);
        }
        NotificationCompat.Builder mseeageNotification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N_MR1) {
            mseeageNotification = new NotificationCompat.Builder(mContext,"channel")
                    .setStyle(new NotificationCompat.MessagingStyle("会话通知")
                            .setGroupConversation(true)
                            .addMessage("message1",System.currentTimeMillis(),person1)
                            .addMessage("message2",System.currentTimeMillis(), person))

                    .setSmallIcon(R.drawable.image)
                    .setContentTitle("message_"+mID)
                    .setContentText("19961226"+randomText)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.image));
        }
        Toast.makeText(mContext,"仅在原生样式生效", Toast.LENGTH_SHORT).show();
        mNotificationManager.notify(mID,mseeageNotification.build());
        mID+=1;


    }

    public void sendConversationNotification(View view){
        int randomText=mRrandom.nextInt(10000);
        @SuppressLint({"NewApi", "LocalSuppress"})
        ShortcutInfo shortcutInfo=new ShortcutInfo.Builder(mContext,"id")
                .setLongLived(true)
                .setIntent(new Intent(mContext,MainActivity.class).setAction(Intent.ACTION_VIEW))
                .setShortLabel("会话")
                .setIcon(Icon.createWithBitmap(small))
                .build();

        ShortcutManager shortcutManager= null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            shortcutManager = (ShortcutManager) mContext.getSystemService(Context.SHORTCUT_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            shortcutManager.pushDynamicShortcut(shortcutInfo);
        }
        NotificationCompat.Builder messageNotification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N_MR1) {
            messageNotification = new NotificationCompat.Builder(mContext,"channel")
                    .setStyle(new NotificationCompat.MessagingStyle("会话通知")
                            .setGroupConversation(true)
                            .addMessage("message1",System.currentTimeMillis(),person1)
                            .addMessage("message2",System.currentTimeMillis(), person))

                    .setSmallIcon(R.drawable.image)
                    .setContentTitle("message_"+mID)
                    .setContentText("19961226"+randomText)
                    .setShortcutId(shortcutInfo.getId())
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.image));
        }
        mNotificationManager.notify(mID,messageNotification.build());
        mID+=1;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void sendMediaNotification(View view){
//        Notification.Builder builder= null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            builder = new Notification.Builder(mContext,"channel")
//                    .setContentTitle("title")
//                    .setContentText("text")
//                    .setSmallIcon(Icon.createWithBitmap(small));
//        }
//        Notification notification=builder.setVisibility(Notification.VISIBILITY_PUBLIC)
//               .setSmallIcon(Icon.createWithBitmap(small))
//               .addAction(android.R.drawable.ic_media_previous, "Previous", mPendingIntent) // #0
//               .addAction(android.R.drawable.ic_media_play, "Pause", mPendingIntent)  // #1
//               .addAction(android.R.drawable.ic_media_next, "Next", mPendingIntent)
//               .setStyle(new Notification.MediaStyle()
//                       .setShowActionsInCompactView(0,1,2)
//                       .setMediaSession(new MediaSession(mContext,"MediaSession").getSessionToken()))
//               .setLargeIcon(small)
//               .setOngoing(true)
//               .build();
//        mNotificationManager.notify(mID,notification);
        Intent intent=new Intent(this, MediaService.class);
        intent.putExtra("id",mID);
        startService(intent);
        mID+=1;
    }

    public void sendDecoratedNotification(View view){

    }

    public void sendCustomNotification(View view){

    }

    public void sendBigCustomNotification(View view){

    }
}