package com.ravneet.project.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ravneet.project.R;
import com.ravneet.project.model.User;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    TextView txtReg;

    EditText eTxtPhone, eTxtOTP;
    Button btnLogin;

    PhoneAuthProvider authProvider;
    FirebaseAuth auth;

//    ProgressDialog progressDialog;

//    FirebaseFirestore db;
//    FirebaseUser firebaseUser;


    void initViews(){
        txtReg=findViewById(R.id.textViewReg);
        eTxtPhone=findViewById(R.id.editTextPhone);
        eTxtOTP=findViewById(R.id.editTextOTP);
        btnLogin=findViewById(R.id.buttonLogin);

        btnLogin.setOnClickListener(clickListener);
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Please Wait...");
//        progressDialog.setCancelable(false);

        authProvider=PhoneAuthProvider.getInstance();
        auth=FirebaseAuth.getInstance();
//        if (auth.getCurrentUser() != null) {
//
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    View.OnClickListener clickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String phone = eTxtPhone.getText().toString().trim();

//                Toast.makeText(LoginActivity.this, "Authentication failed, check your phone number or sign up",
//                        Toast.LENGTH_LONG).show();
//                progressDialog.dismiss();

            authProvider.verifyPhoneNumber(
                    phone,
                    60,
                    TimeUnit.SECONDS,
                    LoginActivity.this, callbacks);

        }
    };

    PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            signInUser(phoneAuthCredential);

        }


        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(LoginActivity.this,"Verification Failed"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    };

    void signInUser(PhoneAuthCredential phoneAuthCredential){
        auth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(!task.isSuccessful()){
                    if(task.isComplete()){
                    FirebaseUser user=task.getResult().getUser();
                    String userId=user.getUid();
                    Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
//        else {
//                    Toast.makeText(LoginActivity.this, "Authentication failed, check your phone number or sign up",
//                            Toast.LENGTH_LONG).show();
//                    progressDialog.dismiss();
//                }
            }
        });

    }
}