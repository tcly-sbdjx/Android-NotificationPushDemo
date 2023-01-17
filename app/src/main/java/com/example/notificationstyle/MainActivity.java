package com.example.notificationstyle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.graphics.BitmapKt;
import androidx.core.graphics.drawable.IconCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Contacts;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private Intent mIntent;


    @SuppressLint("WrongThread")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void goToNormalNotification(View view){
        mIntent=new Intent(MainActivity.this,NormalNotification.class);
        startActivity(mIntent);
    }

    public void goToBigNotification(View view){
        mIntent=new Intent(MainActivity.this,BigNotification.class);
        startActivity(mIntent);
    }

    public void goToGroupNotification(View view){
        mIntent=new Intent(MainActivity.this,GroupNotificatin.class);
        startActivity(mIntent);
    }

    public void goToSpecalNotification(View view){
        mIntent=new Intent(MainActivity.this,SpecialNotification.class);
        startActivity(mIntent);
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
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}