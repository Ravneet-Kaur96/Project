package com.ravneet.project.ui;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ravneet.project.R;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    EditText eTxtPhone, eTxtOTP;
    Button btnLogin;

    PhoneAuthProvider authProvider;
    FirebaseAuth auth;

    FirebaseFirestore db;
    FirebaseUser firebaseUser;


    void initViews(){
        eTxtPhone=findViewById(R.id.editTextPhone);
        eTxtOTP=findViewById(R.id.editTextOTP);
        btnLogin=findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(clickListener);

        authProvider=PhoneAuthProvider.getInstance();
        auth=FirebaseAuth.getInstance();
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

//            db.collection("users").whereEqualTo("phone","").get();
//            FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
//            CollectionReference yourCollRef = db.collection("users");
//            Query query = yourCollRef.whereEqualTo("phone", "yourValue");
//            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                    if (task.isSuccessful()) {
//                        for (QueryDocumentSnapshot document : task.getResult()) {
////                           Toast.makeText(LoginActivity.this,document.getId() + " => " + document.getData(),Toast.LENGTH_LONG).show();
//                            Intent intent= new Intent(LoginActivity.this, MainActivity.class);
//                            startActivity(intent);
//                        }
//                    } else {
//                        Toast.makeText(LoginActivity.this,"Please Register With Us First!!",Toast.LENGTH_LONG ).show();
//                    }
//                }
//            });
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
                if(task.isComplete()){
                    FirebaseUser user=task.getResult().getUser();
                    String userId=user.getUid();
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
