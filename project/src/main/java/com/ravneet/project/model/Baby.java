package com.ravneet.project.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Baby implements Serializable {

    public String name;
    public String dob;
    public String gender;
    public String docId;
    public ArrayList<Vaccination> vaccinations;


    public Baby() {
    }

    public Baby(String name, String dob, String gender, String docId) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.docId = docId;
    }




    @Override
    public String toString() {
        return "Baby{" +
                "name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", docId='" + docId + '\'' +
                '}';
    }
}
