package com.example.notificationstyle;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;
import androidx.core.app.Person;
import androidx.core.graphics.drawable.IconCompat;

public class CallService extends Service {

    private Intent mIntent;
    private PendingIntent mPendingIntent;
    private Bitmap small;

    public CallService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @RequiresApi(api = Build.VERSION_CODES.S)
    public void createNotification(int id){
        small= BitmapFactory.decodeResource(getResources(),R.drawable.image);
        mIntent=new Intent(this, MainActivity.class);
        mPendingIntent=PendingIntent.getActivity(this,0,mIntent,PendingIntent.FLAG_IMMUTABLE);
        Person person=new Person.Builder()
                .setName("小明")
                .setIcon(IconCompat.createWithBitmap(small))
                .build();
        Notification.Builder builder= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this,"channel1")
                    .setContentTitle("title")
                    .setContentText("text")
                    .setSmallIcon(R.drawable.image);
        }
        @SuppressLint("RestrictedApi") Notification notification=builder.setVisibility(Notification.VISIBILITY_PUBLIC)
                .setStyle(Notification.CallStyle.forScreeningCall(person.toAndroidPerson(), mPendingIntent,mPendingIntent))
                .setSmallIcon(R.drawable.image)
                .build();
        startForeground(id,notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotification(intent.getExtras().getInt("id"));
        return super.onStartCommand(intent, flags, startId);
    }
}