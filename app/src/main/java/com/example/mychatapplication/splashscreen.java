package com.example.mychatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splashscreen extends AppCompatActivity {

    private static int SPLASH_TIMER=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);


        Handler h=new Handler();
        Runnable r=new Runnable() {
            @Override
            public void run() {
                runn();
            }
        };
        h.postDelayed(r,SPLASH_TIMER);
    }

    private void runn() {
        Intent intent=new Intent(splashscreen.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}