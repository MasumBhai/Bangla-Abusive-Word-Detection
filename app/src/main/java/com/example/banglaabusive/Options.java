package com.example.banglaabusive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Options extends AppCompatActivity {


    private Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        aSwitch=(Switch) findViewById(R.id.switch2);
        TextView ID=(TextView) findViewById(R.id.editTextTextPersonName2);


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    if(ID.getText().toString().equals("123"))
                    {
                        Toast.makeText(Options.this,"Connected",Toast.LENGTH_SHORT).show();
                        try {
                            Thread.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        goMenu();
                    }
                    else
                    {
                        aSwitch.setChecked(false);
                        Toast.makeText(Options.this,"Enter a valid ID",Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(Options.this,"Disconnected",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void goMenu()
    {
        Intent intent= new Intent(Options.this,Menu.class);
        startActivity(intent);

    }
}