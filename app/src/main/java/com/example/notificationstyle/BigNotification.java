package com.example.notificationstyle;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class BigNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_notification);
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

    public void sendBigTextNotification(View view){

    }

    public void sendBigPictureNotification(View view){

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