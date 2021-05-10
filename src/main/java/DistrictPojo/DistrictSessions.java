package DistrictPojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class DistrictSessions {
    int center_id;
    String name;
    String address;
    String state_name;
    String district_name;
    String block_name;
    int pincode;
    String fee_type;
    String session_id;
    int available_capacity;
    String date;
    String vaccine;
    int min_age_limit;
    ArrayList<String> slots;

    public DistrictSessions(@JsonProperty(value = "center_id") int center_id,
                            @JsonProperty(value = "name") String name,
                            @JsonProperty(value = "address") String address,
                            @JsonProperty(value = "state_name") String state_name,
                            @JsonProperty(value = "district_name") String district_name,
                            @JsonProperty(value = "block_name") String block_name,
                            @JsonProperty(value = "pincode") int pincode,
                            @JsonProperty(value = "fee_type") String fee_type,
                            @JsonProperty(value = "session_id") String session_id,
                            @JsonProperty(value = "available_capacity") int available_capacity,
                            @JsonProperty(value = "date") String date,
                            @JsonProperty(value = "vaccine") String vaccine,
                            @JsonProperty(value = "min_age_limit") int min_age_limit,
                            @JsonProperty(value = "slots") ArrayList<String> slots) {
        this.center_id = center_id;
        this.name = name;
        this.address = address;
        this.state_name = state_name;
        this.district_name = district_name;
        this.block_name = block_name;
        this.pincode = pincode;
        this.fee_type = fee_type;
        this.session_id = session_id;
        this.available_capacity = available_capacity;
        this.date = date;
        this.vaccine = vaccine;
        this.min_age_limit = min_age_limit;
        this.slots = slots;
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
