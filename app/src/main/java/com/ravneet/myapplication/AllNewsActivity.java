package com.ravneet.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class AllNewsActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener{
    //ListView listView;
    //GridView listView;

    RecyclerView recyclerView;

    ArrayList<News> newsList;
    NewsAdapter newsAdapter;
    NewsRecyclerAdapter recyclerAdapter;

    void initViews(){
        //listView= findViewById(R.id.listView);
        recyclerView= findViewById(R.id.recyclerView);
        newsList= new ArrayList<>();

       // News news1= new News(R.drawable.newspaper, "NDTV", "//http://ndtv.india.com");
       // newsList.add(news1);
        //can use above method or below method for adding objects

        newsList.add(new News(R.drawable.newspaper,"NDTV","https://www.ndtv.com/"));
        newsList.add(new News(R.drawable.newspaper,"CNN","https://edition.cnn.com/"));
        newsList.add(new News(R.drawable.newspaper,"AJJ TAK","https://aajtak.intoday.in/"));
        newsList.add(new News(R.drawable.ic_save_energy,"ZEE NEWS","https://zee.india.com/"));
        newsList.add(new News(R.drawable.newspaper,"PTC NEWS","https://www.ptcnews.tv/"));
        newsList.add(new News(R.drawable.newspaper,"INDIA TODAY","https://www.indiatoday.in/"));
        newsList.add(new News(R.drawable.newspaper,"NEWS 9","http://www.news9.com/"));

        newsAdapter= new NewsAdapter(this, R.layout.list_item, newsList);
//        listView.setAdapter(newsAdapter);
//        listView.setOnItemClickListener(this);

        recyclerAdapter= new NewsRecyclerAdapter(this, R.layout.list_item, newsList);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

//        GridLayoutManager gridLayoutManager= new GridLayoutManager(this);
//        recyclerView.setLayoutManager(gridLayoutManager);

        //StaggeredGridLayoutManager->Explore

        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_news);
        initViews();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news= newsList.get(position);
        Intent intent= new Intent(AllNewsActivity.this, WebViewActivity.class);
        intent.putExtra("keyNews",news);
        startActivity(intent);
    }
}
