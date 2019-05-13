package com.ravneet.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.ravneet.myapplication.R;
import com.ravneet.myapplication.listener.OnRecyclerItemClickListener;
import com.ravneet.myapplication.model.Customer;

import java.util.ArrayList;

public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.ViewHolder>{

    Context context;
    int resource;
    ArrayList<Customer> objects;
    OnRecyclerItemClickListener recyclerItemClickListener;

   public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener recyclerItemClickListener){
        this.recyclerItemClickListener=recyclerItemClickListener;

    }

    public CustomersAdapter(Context context, int resource, ArrayList<Customer> objects) {
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public CustomersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(resource,parent,false);
        final CustomersAdapter.ViewHolder holder = new CustomersAdapter.ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });

        return holder;
    }


    // onBindViewHolder will be execute n number of time from 0 to n-1 when n is the count which we are returning
    @Override
    public void onBindViewHolder(@NonNull CustomersAdapter.ViewHolder holder, int position) {

        Customer customer  = objects.get(position);

        // Extracting Data from Customers Object and Setting the data on customers_list
        holder.txtName.setText(customer.name);
        holder.txtPhone.setText(customer.phone);
        holder.txtEmail.setText(customer.email);
    }

    @Override
    public int getItemCount() {
        return objects.size(); // how many list items we wish to have in our recycler view
    }


    // Nested Class : ViewHolder to hold Views of list_item
    class ViewHolder extends RecyclerView.ViewHolder{

        // Attributes of ViewHolder
        TextView txtName;
        TextView txtPhone;
        TextView txtEmail;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.textViewName);
            txtPhone = itemView.findViewById(R.id.textViewPhone);
            txtEmail = itemView.findViewById(R.id.textViewEmail);
        }
    }

}