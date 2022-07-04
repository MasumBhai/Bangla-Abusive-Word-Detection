package com.example.banglaabusive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class Menu extends AppCompatActivity {
     private Button button;
     private Button pb;

    Map<String, Object> data = new HashMap<String, Object>();
//    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


//        DocumentReference dbref = FirebaseFirestore.getInstance().collection("Get_Location").document("Realtime");
//        dbref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                if (documentSnapshot.exists()){
//                    String lat = documentSnapshot.getString("Latitude");
//                    String lon = documentSnapshot.getString("Longitude");
//                    Log.d("GOT IT lat ", lat);
//                    Log.d("GOT IT lon ", lon);
//
//                }
//            }
//        });

        button= (Button) findViewById(R.id.button3);
        pb=(Button)findViewById(R.id.button6);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    String address = "Dhaka,Bangladesh";
                    String url = "http://maps.google.com/maps?daddr="+address;
                    String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 23.764008548943707, 90.40681327212089);
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr=23.764008548943707,90.40681327212089"));
//                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(uri));
                    startActivity(intent);
                }


        });
        pb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfetch();
            }
        });



    }
    public void   openfetch()
    {

//        data.put("Value", "1");
//        db.collection("Track_Location")
//                .add(data)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("ID:", documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("Error adding document", e);
//                    }
//                });




        Intent i2=new Intent(Menu.this,userlist.class);
        startActivity(i2);
        finish();
    }

}