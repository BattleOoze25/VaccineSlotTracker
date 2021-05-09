package UserPojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Client {
    String name;
    String email;
    String phone;

    public Client(@JsonProperty(value = "name") String name,
                  @JsonProperty(value = "email") String email,
                  @JsonProperty(value = "phone") String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
