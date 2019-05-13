package com.ravneet.myapplication.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.ravneet.myapplication.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class PhoneRegisterActivity extends AppCompatActivity {

//    @BindView(R.id.editTextPhone)
//    EditText etxtPhone;
//
//    @BindView(R.id.editTextOTP)
//    EditText etxtOTP;
//
//    @BindView(R.id.buttonValidate)
//    Button btnValidate;

    EditText etxtPhone, etxtOTP;
    Button btnValidate;

    PhoneAuthProvider authProvider;
    FirebaseAuth auth;



    void intViews(){
        etxtPhone=findViewById(R.id.editTextPhone);
        etxtOTP=findViewById(R.id.editTextOTP);
        btnValidate=findViewById(R.id.buttonValidate);
        btnValidate.setOnClickListener(clickListener);

        authProvider=PhoneAuthProvider.getInstance();
        auth=FirebaseAuth.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_register);
        intViews();
    }

    View.OnClickListener clickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String phone = etxtPhone.getText().toString().trim();

            authProvider.verifyPhoneNumber(
                    phone,
                    60,
                    TimeUnit.SECONDS,
                    PhoneRegisterActivity.this,callbacks);
        }
    };

    PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            signInUser(phoneAuthCredential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(PhoneRegisterActivity.this,"Verification Failed"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    };

    void signInUser(PhoneAuthCredential phoneAuthCredential){
        auth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isComplete()){
                    FirebaseUser user=task.getResult().getUser();
                    String userId=user.getUid();
                }
            }
        });
    }
}
