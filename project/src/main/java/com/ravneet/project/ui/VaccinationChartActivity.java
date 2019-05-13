package com.ravneet.project.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ravneet.project.R;
import com.ravneet.project.adapter.VaccineAdapter;
import com.ravneet.project.listener.MyListener;
import com.ravneet.project.model.Baby;
import com.ravneet.project.model.Vaccination;

import java.util.ArrayList;

public class VaccinationChartActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

//    TextView txtDueDate;
    ArrayList<Vaccination> vaccineList;
    ListView listView;
    VaccineAdapter vaccineAdapter;
    MyListener myListener;
    public String date;
    Baby baby;


    void initViews(){

        baby=new Baby();
//        txtDueDate=findViewById(R.id.textViewDueDate);
        listView=findViewById(R.id.listView);
        vaccineList=new ArrayList<>();

        Vaccination vacc1=new Vaccination("BCG","");
        Vaccination vacc2=new Vaccination("DTP","");
        Vaccination vacc3=new Vaccination("HepA","");
        Vaccination vacc4=new Vaccination("HepB","");
        Vaccination vacc5=new Vaccination("Hib","");
        Vaccination vacc6=new Vaccination("IPV","");
        Vaccination vacc7=new Vaccination("Influenza","");
        Vaccination vacc8=new Vaccination("MMR","");
        Vaccination vacc9=new Vaccination("Measles","");
        Vaccination vacc10=new Vaccination("OPV","");
        Vaccination vacc11=new Vaccination("PCV","");
        Vaccination vacc12=new Vaccination("Rota","");
        Vaccination vacc13=new Vaccination("Typhoid","");
        Vaccination vacc14=new Vaccination("Varicella","");
        Vaccination vacc15=new Vaccination("TDAP","");

        vaccineList.add(vacc1);
        vaccineList.add(vacc2);
        vaccineList.add(vacc3);
        vaccineList.add(vacc4);
        vaccineList.add(vacc5);
        vaccineList.add(vacc6);
        vaccineList.add(vacc7);
        vaccineList.add(vacc8);
        vaccineList.add(vacc9);
        vaccineList.add(vacc10);
        vaccineList.add(vacc11);
        vaccineList.add(vacc12);
        vaccineList.add(vacc13);
        vaccineList.add(vacc14);
        vaccineList.add(vacc15);



        vaccineAdapter=new VaccineAdapter(this,R.layout.list_item,vaccineList);
        listView.setAdapter(vaccineAdapter);
       listView.setOnItemClickListener(this);

        Intent rcv=getIntent();

//         date.setTime(rcv.getLongExtra("keyDueDate",-1));
//        baby=(Baby)rcv.getSerializableExtra("keyDueDate");
//        date=rcv.getExtras().getString("keyDueDate");



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_chart);
        initViews();
    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(VaccinationChartActivity.this,VaccineDetailsActivity.class);
        intent.putExtra("keyIndex",position);
        startActivity(intent);
    }
}
