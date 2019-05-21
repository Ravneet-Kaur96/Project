package com.ravneet.project.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.ravneet.project.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imageBaby, imageFeed, imageVaccine, imageAppinfo;

    TextView btnAddBaby, btnFeed, btnVaccine, btnAppInfo;

    FirebaseAuth auth;

    void initViews() {
        btnAddBaby = findViewById(R.id.buttonAdd);
        btnFeed=findViewById(R.id.buttonFeed);
        btnVaccine = findViewById(R.id.buttonVaccine);
        btnAppInfo=findViewById(R.id.buttonApp);

        imageBaby=findViewById(R.id.imagebaby);
        imageFeed=findViewById(R.id.imagefeed);
        imageVaccine=findViewById(R.id.imagevaccine);
        imageAppinfo=findViewById(R.id.imageappinfo);


        btnAddBaby.setOnClickListener(this);
        btnFeed.setOnClickListener(this);
        btnVaccine.setOnClickListener(this);
        btnAppInfo.setOnClickListener(this);

        imageBaby.setOnClickListener(this);
        imageFeed.setOnClickListener(this);
        imageVaccine.setOnClickListener(this);
        imageAppinfo.setOnClickListener(this);
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

            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
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
                Intent intent2= new Intent(MainActivity.this, VaccinationKnowledgeActivity.class);
                startActivity(intent2);
                break;

            case R.id.buttonApp:
                Intent intent3= new Intent(MainActivity.this,AppInfoActivity.class);
                startActivity(intent3);
                break;
            case R.id.imagebaby:
                Intent intent4 = new Intent(MainActivity.this, AddBabyActivity.class);
                startActivity(intent4);
                break;

            case R.id.imagefeed:
                Intent intent5 = new Intent(MainActivity.this, FeedActivity.class);
                startActivity(intent5);
                break;

            case R.id.imagevaccine:
                Intent intent6 = new Intent(MainActivity.this, VaccinationKnowledgeActivity.class);
                startActivity(intent6);
                break;

            case R.id.imageappinfo:
                Intent intent7 = new Intent(MainActivity.this, AppInfoActivity.class);
                startActivity(intent7);
                break;
        }

    }
}

