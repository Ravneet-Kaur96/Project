package com.ravneet.project.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.ravneet.project.R;
import com.ravneet.project.model.Baby;
import com.ravneet.project.model.Vaccination;

import java.util.ArrayList;
import java.util.List;

public class VaccineAdapter extends ArrayAdapter<Vaccination> {

    Context context;
    int resource;
    ArrayList<Vaccination> objects;
    Baby baby=new Baby();


    public VaccineAdapter( Context context, int resource, ArrayList<Vaccination>objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // view is ref variable which is pointing to list_item
        View view= LayoutInflater.from(context).inflate(resource,parent,false);


        TextView txtVaccineName=view.findViewById(R.id.textViewVaccineName);
//        TextView txtDueDate=view.findViewById(R.id.textViewDueDate);

        Vaccination vaccination=objects.get(position);

        // Extracting Data from News Object and Setting the data on list_item


        txtVaccineName.setText(vaccination.name);
//        txtDueDate.setText(vaccination.dueDate);





        return view;
    }


}
