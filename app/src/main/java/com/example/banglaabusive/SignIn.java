package com.example.banglaabusive;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SignIn extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private boolean mVerificationInProgress = false;
    private Button verifyOTPBtn, generateOTPBtn;
    private EditText editablePhone, editableOTP;
    private String verificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Restoring the instance state
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }

//        FirebaseApp.initializeApp(getApplicationContext());
//        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
//        firebaseAppCheck.installAppCheckProviderFactory(
//                SafetyNetAppCheckProviderFactory.getInstance());

        mAuth = FirebaseAuth.getInstance();

        editablePhone = findViewById(R.id.editTextTextPass);
        generateOTPBtn = findViewById(R.id.buttock);

        generateOTPBtn.setOnClickListener(view -> {
            if (editablePhone.getText().toString().trim().isEmpty()) {
                Toast.makeText(SignIn.this, "Enter your mobile number", Toast.LENGTH_LONG).show();
            } else if (editablePhone.getText().toString().length() == 11) {
                String phone = "+88" + editablePhone.getText().toString();
                sendVerificationCode(phone);
            } else {
                Toast.makeText(SignIn.this, "Enter a valid mobile number", Toast.LENGTH_LONG).show();
            }
        });


    }

    //Implementing SaveInstanceState to save the flag.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_VERIFY_IN_PROGRESS, mVerificationInProgress);
    }
    //Implementing RestoreInstanceState to restore the flag.
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mVerificationInProgress = savedInstanceState.getBoolean(KEY_VERIFY_IN_PROGRESS);
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            Log.d(TAG, "Currently Signed in: " + currentUser.getPhoneNumber());
            Toast.makeText(getApplicationContext(), "Currently Logged in: " + currentUser.getPhoneNumber(), Toast.LENGTH_LONG).show();
            try {
                Thread.sleep(3000);
                startActivity(new Intent(getApplicationContext(), Options.class));
                finish(); // stopping the current intent, thus previous layout can't be seen
            } catch (InterruptedException e) {
                Toast.makeText(getApplicationContext(), "Thread Error" + e, Toast.LENGTH_LONG).show();
                throw new RuntimeException(e);
            }
        }
    }

    private void sendVerificationCode(String phone) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phone)            // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

        mVerificationInProgress = true;
    }

    // callback method is called on Phone auth provider.
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String verifyId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//            super.onCodeSent(verifyId, forceResendingToken);
            verificationId = verifyId;
            Log.d(TAG, "onCodeSent:" + verificationId);
            mResendToken = forceResendingToken;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            Log.d(TAG, "onVerificationCompleted:" + phoneAuthCredential);
            mVerificationInProgress = false;
            signInWithCredential(phoneAuthCredential);

            // below line is used for getting OTP code
//            final String code = phoneAuthCredential.getSmsCode();
//            if (code != null) {
//                Intent intent = new Intent(getApplicationContext(), VerifyOTP.class);
//                intent.putExtra("mobile", editablePhone.getText().toString());
//                intent.putExtra("verificationID", verificationId);
//                intent.putExtra("resendOTP", mResendToken);
//                intent.putExtra("otpCode", code);
//                startActivity(intent);
//            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            // displaying error message with firebase exception.
            mVerificationInProgress = false;
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("auth err: ", e.getMessage());
        }
    };

    private void signInWithCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = task.getResult().getUser();
                        Log.d(TAG, "firebaseUser:" + user);
                        startActivity(new Intent(getApplicationContext(), Options.class));
                        finish();
                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

}