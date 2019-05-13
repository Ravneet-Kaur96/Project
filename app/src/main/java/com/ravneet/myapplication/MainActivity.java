package com.ravneet.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");}

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    public void clickHandler(View view){

            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*menu.add(1, 101, 0, "CNN");
        menu.add(1, 201, 0, "AJJ TAK");
        menu.add(2, 301, 0, "ZEE NEWS");
        menu.add(2, 401, 0, "PTC PUNJABI");
*/
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

      /*  if(itemId==101){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        else if(itemId==201){

        }
        else if(itemId==301){

        }
        else{

        }*/

      if (itemId==R.id.cnn){

      }
      else if(itemId==R.id.aaj){
Intent intent = new Intent(MainActivity.this, HomeActivity.class);
startActivity(intent);
      }
      else if(itemId==R.id.zee){

      }
      else{

      }
        return super.onOptionsItemSelected(item);
    }
}
