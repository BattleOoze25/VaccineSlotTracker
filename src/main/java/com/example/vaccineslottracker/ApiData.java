package com.example.vaccineslottracker;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ApiData {
    ArrayList<Centres> centers;

    public ApiData(@JsonProperty(value = "centers") ArrayList<Centres> centers) {
        this.centers = centers;
    }

    public ArrayList<Centres> getCenters() {
        return centers;
    }

    public void setCenters(ArrayList<Centres> centers) {
        this.centers = centers;
    }
}
