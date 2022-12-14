package com.example.mychatapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class OtpAuthentication extends AppCompatActivity {

    TextView mchangenumber;
    EditText mgetotp;
    android.widget.Button mverifyotp;
    String enteredotp;

    FirebaseAuth firebaseAuth;
    ProgressBar mprogressbarofotpauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_authentication);

        mchangenumber=findViewById(R.id.changeNumber);
        mverifyotp=findViewById(R.id.verifyOtp);
        mgetotp=findViewById(R.id.getOtp);
        mprogressbarofotpauth=findViewById(R.id.progressBarOfOtpAuth);

        mchangenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OtpAuthentication.this,MainActivity.class);
                startActivity(intent);
            }
        });

        mverifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enteredotp=mgetotp.getText().toString();
                if(enteredotp.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter your OTP first",Toast.LENGTH_SHORT).show();
                }
                else{
                    mprogressbarofotpauth.setVisibility(View.VISIBLE);
                    String codereciever=getIntent().getStringExtra("opt");
                    PhoneAuthCredential credential=PhoneAuthProvider.getCredential(codereciever,enteredotp);
                    signInWithPhoneCredential(credential);




                }
            }
        });



    }

    private void signInWithPhoneCredential(PhoneAuthCredential credential){

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( Task<AuthResult> task) {
               if(task.isSuccessful()){
                   mprogressbarofotpauth.setVisibility(View.INVISIBLE);
                   Toast.makeText(getApplicationContext(),"Login success",Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent(OtpAuthentication.this,set_Profile.class);
                   startActivity(intent);
                   finish();
               }
               else
               {
                   if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                       Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();

                   }
               }
            }
        });




    }

}