package com.example.banglaabusive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText Name,Password;
    private Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth=FirebaseAuth.getInstance();



        Name=(EditText) findViewById(R.id.editTextTextPerson);
        Password=(EditText) findViewById(R.id.editTextTextPass);
        go=(Button)findViewById(R.id.buttock);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
//                Intent uo=new Intent(SignIn.this,MainActivity.class);
//                startActivity(uo);
            }

        });




    }
    private void register(){
        String name=Name.getText().toString().trim();
        String pass=Password.getText().toString().trim();

        if(name.isEmpty())
        {
            Name.setError("Name Can't be empty");

        }
        mAuth.createUserWithEmailAndPassword(name,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Register reg=new Register(name,pass);

                            FirebaseDatabase.getInstance().getReference("Register")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(reg).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(SignIn.this,"Successful",Toast.LENGTH_SHORT).show();
                                            }
                                            else{
                                                Toast.makeText(SignIn.this,"Unsuccessful",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        }
                    }
                });


    }


}