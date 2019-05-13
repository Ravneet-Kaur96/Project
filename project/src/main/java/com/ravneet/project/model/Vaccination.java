package com.ravneet.project.model;

import java.io.Serializable;
import java.util.Date;

public class Vaccination implements Serializable {

    public String name;
    public String dueDate;
    Baby baby=new Baby();

    public Vaccination() {
    }

    public Vaccination(String name, String dueDate) {
        this.name = name;
        this.dueDate= dueDate;
    }
}
