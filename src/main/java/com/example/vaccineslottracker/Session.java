package com.example.vaccineslottracker;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
@JsonIgnoreProperties
public class Session {
    String session_id;
    int available_capacity;
    String date;
    String vaccine;
    int min_age_limit;
    ArrayList<String> slots;

    public Session(@JsonProperty(value = "session_id") String session_id,
                   @JsonProperty(value = "available_capacity") int available_capacity,
                   @JsonProperty(value = "date") String date,
                   @JsonProperty(value = "vaccine") String vaccine,
                   @JsonProperty(value = "min_age_limit") int min_age_limit,
                   @JsonProperty(value = "slots")ArrayList<String> slots) {
        this.session_id = session_id;
        this.available_capacity = available_capacity;
        this.date = date;
        this.vaccine = vaccine;
        this.min_age_limit = min_age_limit;
        this.slots = slots;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public int getAvailable_capacity() {
        return available_capacity;
    }

    public void setAvailable_capacity(int available_capacity) {
        this.available_capacity = available_capacity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public int getMin_age_limit() {
        return min_age_limit;
    }

    public void setMin_age_limit(int min_age_limit) {
        this.min_age_limit = min_age_limit;
    }

    public ArrayList<String> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<String> slots) {
        this.slots = slots;
    }


}
