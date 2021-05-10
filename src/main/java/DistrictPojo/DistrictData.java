package DistrictPojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class DistrictData {
    ArrayList<DistrictSessions> districtSessions;

    public DistrictData(@JsonProperty(value = "sessions") ArrayList<DistrictSessions> districtSessions) {
        this.districtSessions = districtSessions;
    }

    public ArrayList<DistrictSessions> getDistrictSessions() {
        return districtSessions;
    }

    public void setDistrictSessions(ArrayList<DistrictSessions> districtSessions) {
        this.districtSessions = districtSessions;
    }


}
