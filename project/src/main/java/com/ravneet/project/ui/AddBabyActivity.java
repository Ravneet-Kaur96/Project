package com.ravneet.project.ui;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ravneet.project.R;
import com.ravneet.project.adapter.BabyAdapter;
import com.ravneet.project.adapter.VaccineAdapter;
import com.ravneet.project.model.Baby;
import com.ravneet.project.model.Util;
import com.ravneet.project.model.Vaccination;

import java.util.ArrayList;
import java.util.Calendar;

public class AddBabyActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    TextView txtTitle, txtGender;
    EditText eTxtName, eTxtDOB;
    Button btnAdd;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;

    DatePickerDialog datePickerDialog;

    Baby baby;

    FirebaseAuth auth;
    ProgressDialog progressDialog;

    FirebaseFirestore db;
    FirebaseUser firebaseUser;

    BabyAdapter babyAdapter;
    Vaccination vaccination;

    VaccineAdapter vaccineAdapter;
    ArrayList<Vaccination> vaccineList;

    ArrayList<Vaccination> vaccinationArrayList;

    Boolean updateMode;


    void initViews(){
        txtTitle=findViewById(R.id.textViewTitle);
        eTxtName=findViewById(R.id.editTextName);
        eTxtDOB=findViewById(R.id.editTextDateOfBirth);

        txtGender=findViewById(R.id.textViewGender);
        rgGender=new RadioGroup(this);
        rbMale=findViewById(R.id.radioButtonMale);
        rbFemale=findViewById(R.id.radioButtonFemale);

        btnAdd=findViewById(R.id.buttonAddBaby);

        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        baby=new Baby();
        vaccination=new Vaccination();
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();

        rbMale.setOnCheckedChangeListener(this);
        rbFemale.setOnCheckedChangeListener(this);

        btnAdd.setOnClickListener(clickListener);
//        showDatePickerDialog();
        eTxtDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        Intent rcv=getIntent();
        updateMode=rcv.hasExtra("keyBaby");

        if(updateMode){


            baby=(Baby)rcv.getSerializableExtra("keyBaby");

            txtTitle.setText("Update Baby");
            eTxtName.setText(baby.name);
            eTxtDOB.setText(baby.dob);

            btnAdd.setText("Update Baby");

        }

    }

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            String date = year + "/" + (month + 1) + "/" + dayOfMonth;
            eTxtDOB.setText(date);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_baby);
        initViews();

    }

    void showDatePickerDialog() {


        Calendar calendar = Calendar.getInstance();
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        int mm = calendar.get(Calendar.MONTH);
        int yy = calendar.get(Calendar.YEAR);


        datePickerDialog = new DatePickerDialog(this, onDateSetListener, yy, mm, dd);
        datePickerDialog.show();
    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            baby.name = eTxtName.getText().toString();
            baby.dob = eTxtDOB.getText().toString();



            if (Util.isInternetConnected(AddBabyActivity.this)) {
                saveUserInCloud();
            } else {
                Toast.makeText(AddBabyActivity.this, "Please connect to Internet and Try Again", Toast.LENGTH_LONG).show();
            }


        }
    };


    void saveUserInCloud() {

        if (updateMode) {
            db.collection("users")
                    .document(firebaseUser.getUid())
                    .collection("babies")
                    .document(baby.docId).set(baby).
                    addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isComplete()) {
                                babyAdapter.notifyDataSetChanged();
                                Toast.makeText(AddBabyActivity.this, "Updation Finished", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(AddBabyActivity.this, "Updation Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        } else {

            db.collection("users").
                    document(firebaseUser.getUid())
                    .collection("babies").add(baby).
                    addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {

                            if (task.isComplete()) {

                                String babyId = task.getResult().getId();
                                babyDetails();

                                db.collection("users").
                                        document(firebaseUser.getUid()).collection("babies").
                                        document(babyId).set(baby).addOnCompleteListener(AddBabyActivity.this, new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });


                            }

                        }


                    });

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


        int id=buttonView.getId();
        switch (id){

            case R.id.radioButtonMale:
                if(isChecked){
                    Toast.makeText(AddBabyActivity.this, "You Selected Male", Toast.LENGTH_LONG).show();
                    baby.gender="Male";
                }
                break;
            case R.id.radioButtonFemale:
                if(isChecked){
                    Toast.makeText(AddBabyActivity.this, "You Selected Female", Toast.LENGTH_LONG).show();
                    baby.gender="Female";
                }
                break;
        }

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,101,1,"All Babies");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==101){
            Intent intent=new Intent(AddBabyActivity.this,AllBabiesActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    void clearFields(){
        eTxtName.setText("");
        eTxtDOB.setText("");
//        rbMale.setText("");
//        rbFemale.setText("");
    }


    void babyDetails(){

        baby.vaccinations= new ArrayList<>();

        Vaccination vacc1 = new Vaccination("BCG","");
        baby.vaccinations.add(vacc1);

//        baby.vaccinations=vaccinationArrayList;
//        vaccinationArrayList=baby.vaccinations;

        String dueDate=baby.dob;

//        for (int i =0; i<vaccinationArrayList.size();i++){
//            Calendar cal=Calendar.getInstance();
//            cal.set(Calendar.DAY_OF_MONTH);
//            cal.add(Calendar.MONTH, 1);
//            vaccinationArrayList.set(0,"BCJ",(dueDate+cal));
//
//        }
        Intent intent= new Intent(AddBabyActivity.this,AllBabiesActivity.class);
        startActivity(intent);


        clearFields();
    }
}


