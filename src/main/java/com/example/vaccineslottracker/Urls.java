package com.example.vaccineslottracker;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Urls {
    ArrayList<String> urls;

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }

    public Urls(@JsonProperty(value = "urls") ArrayList<String> urls) {
        this.urls = urls;
    }
}
