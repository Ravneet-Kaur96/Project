package com.ravneet.project.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ravneet.project.R;
import com.ravneet.project.model.User;
import com.ravneet.project.model.Util;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    TextView txtRegister, txtLogin;
    EditText eTxtName, etxtEmail, eTxtPhone, etxtPassword;
    Button btnRegister;

    User user;

    FirebaseAuth auth;
    FirebaseFirestore db;

    ProgressDialog progressDialog;
    FirebaseUser firebaseUser;

//    String data;
//    String name, phone, password;
//    SharedPreferences sharedPreferences;
//    SharedPreferences.Editor editor;

    void initViews() {
        txtRegister=findViewById(R.id.textViewRegister);
        txtLogin=findViewById(R.id.textViewLogin);
        eTxtName = findViewById(R.id.editTextName);
        etxtEmail=findViewById(R.id.editTextEmail);
        eTxtPhone = findViewById(R.id.editTextPhone);
        etxtPassword = findViewById(R.id.editTextPassword);
        btnRegister = findViewById(R.id.buttonRegister);

        btnRegister.setOnClickListener(this);
        txtLogin.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);

        user=new User();

        auth = FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

    }

//        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
//        editor = sharedPreferences.edit();


//        @Override
//        public void onClick (View v){
//
//            String Name= eTxtName.getText().toString();
//            String Phone= eTxtPhone.getText().toString();
//            String Password= etxtPassword.getText().toString();

//            editor.putString("keyname", Name);
//            editor.putString("keyphone", Phone);
//            editor.putString("keypassword", Password);
//            sharedPreferences.edit().apply();
//            editor.commit();
//            Toast.makeText(RegisterActivity.this, "Data Saved in SharedPrefs", Toast.LENGTH_LONG).show();
//
//            Intent intent= new Intent(RegisterActivity.this, HomeActivity.class);
//            startActivity(intent);
//
//            eTxtName.setText("");
//            eTxtPhone.setText("");
//            etxtPassword.setText("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
//        Intent intent= new Intent(RegisterActivity.this,HomeActivity.class);
//        if (sharedPreferences.contains("keyname")){
//            startActivity(intent);
        }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.buttonRegister) {
            user.name = eTxtName.getText().toString();
            user.email=etxtEmail.getText().toString();
            user.phone = eTxtPhone.getText().toString();
            user.password = etxtPassword.getText().toString();

            registerUser();
            if (Util.isInternetConnected(this)) {
                registerUser();
            } else {
                Toast.makeText(this, "Please Connect to Internet and Try Again", Toast.LENGTH_LONG).show();
            }
        } else {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    void registerUser() {

        progressDialog.show();


        auth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()) {
                            Toast.makeText(RegisterActivity.this, user.name + "Registered Successsfully", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();

                            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                            saveUserInCloudDB();
                        }
                    }

                });
    }

    void saveUserInCloudDB() {
//        db.collection("users").add(user)
//                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentReference> task) {
//                        if (task.isComplete()) {
//                            Toast.makeText(RegisterActivity.this, user.name + "Registered Sucessfully", Toast.LENGTH_LONG).show();
//                            progressDialog.dismiss();
//
//                            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
//                            startActivity(intent);
//                            finish();
//
//                        }
//                    }
//                });


        firebaseUser=auth.getCurrentUser();
        db.collection("users").document(firebaseUser.getUid()).set(user)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(RegisterActivity.this, user.name + "Registered Sucessfully", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });

    }

}






