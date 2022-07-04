package com.example.banglaabusive;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Start extends AppCompatActivity {

    private ProgressBar progressBar;
    private  int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        progressBar = findViewById(R.id.progressBar5);
        Thread thread=new Thread(() -> {
            for(progress=10;progress<=100;progress=progress+10){
                try {
                    Thread.sleep(300);
                    progressBar.setProgress(progress);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            startActivity(new Intent(Start.this,SignIn.class));
            finish();
        });

        thread.start();
    }
}