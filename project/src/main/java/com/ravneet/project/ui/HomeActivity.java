package com.ravneet.project.ui;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ravneet.project.R;

public class HomeActivity extends AppCompatActivity {

    TextView txtResult;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtResult= findViewById(R.id.textViewResult);
        sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        txtResult.setText("Hello" +sharedPreferences.getString("keyname",null));
    }
}
