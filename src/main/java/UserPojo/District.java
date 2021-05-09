package UserPojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class District {
    String name;
    String code;
    String url;
    ArrayList<Client> clients;

    public District(@JsonProperty(value = "name")    String name,
                    @JsonProperty(value = "code")    String code,
                    @JsonProperty(value = "url")     String url,
                    @JsonProperty(value = "clients") ArrayList<Client> clients) {
        this.name = name;
        this.code = code;
        this.url = url;
        this.clients = clients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }
}
