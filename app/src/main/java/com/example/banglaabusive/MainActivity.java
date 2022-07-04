package com.example.banglaabusive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        TextView user=(TextView) findViewById(R.id.editTextTextPersonName);
        TextView pass=(TextView) findViewById(R.id.editTextTextPassword);
        Button butt=(Button) findViewById(R.id.button);
        Button signbut=(Button)findViewById(R.id.button4);
        mAuth= FirebaseAuth.getInstance();

        signbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inT=new Intent(MainActivity.this,SignIn.class);
                startActivity(inT);
            }
        });



        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getText().toString().equals("bibh") && pass.getText().toString().equals("bibh")){

                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    goOption();
                }
                else{Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();}

            }


        });
    }



    public void goOption(){
        Intent intent= new Intent(MainActivity.this,Options.class);
        startActivity(intent);

    }
}