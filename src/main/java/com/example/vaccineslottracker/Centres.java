package com.example.vaccineslottracker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
@JsonIgnoreProperties
public class Centres {
    int center_id;
    String name;
    String address;
    String state_name;
    String district_name;
    String block_name;
    int pincode;
    String fee_type;
    ArrayList<Session> sessions;

    public Centres(@JsonProperty(value = "center_id")int center_id,
                   @JsonProperty(value = "name") String name,
                   @JsonProperty(value = "address") String address,
                   @JsonProperty(value = "state_name") String state_name,
                   @JsonProperty(value = "district_name") String district_name,
                   @JsonProperty(value = "block_name") String block_name,
                   @JsonProperty(value = "pincode") int pincode,
                   @JsonProperty(value = "fee_type") String fee_type,
                   @JsonProperty(value = "sessions") ArrayList<Session> sessions) {
        this.center_id = center_id;
        this.name = name;
        this.address = address;
        this.state_name = state_name;
        this.district_name = district_name;
        this.block_name = block_name;
        this.pincode = pincode;
        this.fee_type = fee_type;
        this.sessions = sessions;
    }

    public int getCenter_id() {
        return center_id;
    }

    public void setCenter_id(int center_id) {
        this.center_id = center_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getBlock_name() {
        return block_name;
    }

    public void setBlock_name(String block_name) {
        this.block_name = block_name;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }
}
