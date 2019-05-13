package com.ravneet.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends ArrayAdapter<Book> {

        Context context;
        int resource;
        List<Book> objects;

    public BooksAdapter( Context context, int resource,  ArrayList<Book> objects) {
            super(context, resource, objects);

            this.context=context;
            this.resource=resource;
            this.objects=objects;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = LayoutInflater.from(context).inflate(resource,parent,false);

            TextView txtBookName = view.findViewById(R.id.textViewName);
            TextView txtAuthor = view.findViewById(R.id.textViewAuthor);
            TextView txtPrice = view.findViewById(R.id.textViewPrice);

            Book book = objects.get(position);
            txtBookName.setText(book.name);
            txtAuthor.setText(book.author);
            txtPrice.setText(book.price);

            return view;

        }
    }
