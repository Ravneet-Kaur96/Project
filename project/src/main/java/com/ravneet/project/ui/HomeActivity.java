package com.ravneet.project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.ravneet.project.R;



public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

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


//        btnAddBaby.setOnClickListener(this);
//        btnFeed.setOnClickListener(this);
//        btnVaccine.setOnClickListener(this);
//        btnAppInfo.setOnClickListener(this);
//
//        imageBaby.setOnClickListener(this);
//        imageFeed.setOnClickListener(this);
//        imageVaccine.setOnClickListener(this);
//        imageAppinfo.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    @Override
//        public void onClick(View v) {
//
//            switch (v.getId()){
//                case R.id.buttonAdd:
//                    Intent intent = new Intent(HomeActivity.this, AddBabyActivity.class);
//                    startActivity(intent);
//                    break;
//
//                case R.id.buttonFeed:
//                    Intent intent1= new Intent(HomeActivity.this,FeedActivity.class);
//                    startActivity(intent1);
//                    break;
//
//                case R.id.buttonVaccine:
//                    Intent intent2= new Intent(HomeActivity.this, VaccinationKnowledgeActivity.class);
//                    startActivity(intent2);
//                    break;
//
//                case R.id.buttonApp:
//                    Intent intent3= new Intent(HomeActivity.this,AppInfoActivity.class);
//                    startActivity(intent3);
//                    break;
//                case R.id.imagebaby:
//                    Intent intent4 = new Intent(HomeActivity.this, AddBabyActivity.class);
//                    startActivity(intent4);
//                    break;
//
//                case R.id.imagefeed:
//                    Intent intent5 = new Intent(HomeActivity.this, FeedActivity.class);
//                    startActivity(intent5);
//                    break;
//
//                case R.id.imagevaccine:
//                    Intent intent6 = new Intent(HomeActivity.this, VaccinationKnowledgeActivity.class);
//                    startActivity(intent6);
//                    break;
//
//                case R.id.imageappinfo:
//                    Intent intent7 = new Intent(HomeActivity.this, AppInfoActivity.class);
//                    startActivity(intent7);
//                    break;
//            }
//
//        }
}
