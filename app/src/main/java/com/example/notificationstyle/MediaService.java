package com.example.notificationstyle;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.media.session.MediaSession;
import android.os.IBinder;

import com.example.notificationstyle.MainActivity;
import com.example.notificationstyle.R;

public class MediaService extends Service {
    private Intent mIntent;
    private PendingIntent mPendingIntent;
    private Bitmap small;

    public MediaService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    public void createNotification(int id){
        small= BitmapFactory.decodeResource(getResources(),R.drawable.a);
        mIntent=new Intent(this, MainActivity.class);
        mPendingIntent=PendingIntent.getActivity(this,0,mIntent,PendingIntent.FLAG_IMMUTABLE);
        Notification.Builder builder= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this,"channel")
                    .setContentTitle("title")
                    .setContentText("text")
                    .setSmallIcon(R.drawable.image);
        }
        Notification notification=builder.setVisibility(Notification.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.image)
                .addAction(android.R.drawable.ic_media_previous, "Previous", mPendingIntent) // #0
                .addAction(android.R.drawable.ic_media_play, "Pause", mPendingIntent)  // #1
                .addAction(android.R.drawable.ic_media_next, "Next", mPendingIntent)
                .setStyle(new Notification.MediaStyle()
                        .setShowActionsInCompactView(0,1,2)
                        .setMediaSession(new MediaSession(this,"MediaSession").getSessionToken()))
                .setLargeIcon(small)
                .setOngoing(true)
                .build();
        startForeground(id,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotification(intent.getExtras().getInt("id"));
        return super.onStartCommand(intent, flags, startId);
    }
}