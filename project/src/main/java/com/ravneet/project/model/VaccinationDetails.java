package com.ravneet.project.model;

import java.io.Serializable;
import java.util.Date;

    public class VaccinationDetails implements Serializable {

        public String name;
        public String dueDate;
        Baby baby=new Baby();

        public VaccinationDetails() {
        }

        public VaccinationDetails(String name, String dueDate) {
            this.name = name;
            this.dueDate= dueDate;
        }
    }

