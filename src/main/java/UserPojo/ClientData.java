package UserPojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ClientData {
    ArrayList<District> districts;

    public ClientData(@JsonProperty(value = "districts") ArrayList<District> districts) {
        this.districts = districts;
    }

    public ArrayList<District> getDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<District> districts) {
        this.districts = districts;
    }
}
