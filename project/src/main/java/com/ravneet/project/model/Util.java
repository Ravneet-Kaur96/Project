package com.ravneet.project.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

public class Util {

    public static final int DB_VERSION=1;
    public static final String DB_NAME= "Babies.db";

    public static final String TAB_NAME="Baby";

    public static final String COL_ID ="_ID";
    public static final String COL_NAME= "name";
    public static final String COL_PHONE="phone";
    public static final String COL_EMAIL="email";

    public static final String CREATE_TAB_QUERY ="create table Customer("+
            "_ID Integer primary key autoincrement,"+
            "name varchar(256),"+
            "phone varchar(20),"+
            "email varchar(50)"+
            ")";

    public static final Uri CUSTOMER_URI= Uri.parse("content://com.ravneet.myapplication.cp/"+TAB_NAME);

    public static boolean isInternetConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }

}
