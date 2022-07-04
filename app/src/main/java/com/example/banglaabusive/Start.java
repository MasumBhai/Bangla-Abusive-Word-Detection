package com.example.banglaabusive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.window.SplashScreen;

public class Start extends AppCompatActivity {

    private ProgressBar progressBar;
    private  int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        progressBar = findViewById(R.id.progressBar5);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                goMain();
            }
        });

        thread.start();



    }
    public void doWork()  {

        for(progress=10;progress<=100;progress=progress+10){
            try {
                Thread.sleep(300);
                progressBar.setProgress(progress);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }


    }
    public void goMain(){
        Intent intent= new Intent(Start.this,MainActivity.class);
        startActivity(intent);


    }
}