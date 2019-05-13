package com.ravneet.project.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.ravneet.project.R;
import com.ravneet.project.SplashActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAddBaby, btnFeed, btnVaccine, btnAppInfo;

    FirebaseAuth auth;

    void initViews() {
        btnAddBaby = findViewById(R.id.buttonAdd);
        btnFeed=findViewById(R.id.buttonFeed);
        btnVaccine = findViewById(R.id.buttonVaccine);
        btnAppInfo=findViewById(R.id.buttonApp);

        btnAddBaby.setOnClickListener(this);
        btnFeed.setOnClickListener(this);
        btnVaccine.setOnClickListener(this);
        btnAppInfo.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 101, 0, "LogOut");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 101) {
            auth.signOut();

            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAdd:
                Intent intent = new Intent(MainActivity.this, AddBabyActivity.class);
                startActivity(intent);
                break;

            case R.id.buttonFeed:
                Intent intent1= new Intent(MainActivity.this,FeedActivity.class);
                startActivity(intent1);
                break;

            case R.id.buttonVaccine:
                Intent intent2= new Intent(MainActivity.this,VaccinationChartActivity.class);
                startActivity(intent2);
                break;

            case R.id.buttonApp:
                Intent intent3= new Intent(MainActivity.this,AppInfoActivity.class);
                startActivity(intent3);
                break;
        }

    }
}

