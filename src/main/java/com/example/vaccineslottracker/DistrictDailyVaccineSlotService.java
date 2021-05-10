package com.example.vaccineslottracker;

import DistrictPojo.DistrictData;
import DistrictPojo.DistrictSessions;
import UserPojo.Client;
import UserPojo.ClientData;
import UserPojo.District;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@RestController
@Service
public class DistrictDailyVaccineSlotService {
    private final RestTemplate restTemplate;
    public DistrictDailyVaccineSlotService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    Map<String,Boolean> slotsPrintedDaily = new HashMap<>();
    @GetMapping("/dailyhello")
    private void sendAlertsDaily() throws Exception{
        try {
            String filePath = "src/main/resources/customDailyUsersInput.json";
            File file = new File(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            ClientData clientData = objectMapper.readValue(file,ClientData.class);
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ArrayList<District> districts = clientData.getDistricts();
            for(District district : districts){
                String url = district.getUrl();
                ArrayList<String> districtEmails = new ArrayList<>();
                ArrayList<Client> clients = district.getClients();
                for(Client client : clients) {
                    String email = client.getEmail();
                    districtEmails.add(email);
                }
                checkUrlsForDistrictDaily(districtEmails,url);            }
        } catch (Exception ex){
            System.out.println("Na ho paa raha " + ex.getLocalizedMessage());
        }
    }
    public String checkUrlsForDistrictDaily(ArrayList<String>districtEmails,String url) throws Exception {
        try{
            System.out.println("Hitting " + url);
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
            headers.add("Content-Type", "application/json");
            HttpEntity entity = new HttpEntity("parameters", headers);
            ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            String json = result.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            DistrictData districtData = objectMapper.readValue(json, DistrictData.class);
            checkAvailabilityForDistrictDaily(districtData,districtEmails);
            return json;
        } catch (Exception ex){
            System.out.println("Cant hit url, check scheduling frequency");
            return ex.getLocalizedMessage();
        }
    }
    private void checkAvailabilityForDistrictDaily(DistrictData districtData,ArrayList<String>districtEmailsDaily){
        ArrayList<DistrictSessions> districtSessions= districtData.getDistrictSessions();
        ArrayList<String> messagesDaily = new ArrayList<>();

            for(DistrictSessions districtSession : districtSessions){
                int available_capacity = districtSession.getAvailable_capacity();
                int min_age_limit = districtSession.getMin_age_limit();
                if(available_capacity > 1 && min_age_limit == 18){
                    String message = printSession(districtSession);
                    if(message.equals("")){
                        continue;
                    }
                    messagesDaily.add(message);
                }
            }

        // No session available
        if(messagesDaily.size() == 0) {
            return;
        }
        String text = "Vaccine slot available for your district, login to cowin to book your slot\n";
        text += "Link : https://selfregistration.cowin.gov.in/\n";
        for(String message : messagesDaily){
            text += message + '\n';
        }
        String[] emails = new String[districtEmailsDaily.size()];
        emails = districtEmailsDaily.toArray(emails);
        EmailUtil.sendSimpleMessageMultiple(text,emails);
    }
    private String printSession(DistrictSessions districtSessions){

        int centre_id = districtSessions.getCenter_id();
        String date = districtSessions.getSession_id();
        String key = Integer.toString(centre_id) + date;
        if (slotsPrintedDaily.containsKey(key) == true) {
            return "";
        }

        slotsPrintedDaily.put(key, true);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String message = "SLOT AVAILABLE !!!\n";
        message += "Date : " + districtSessions.getDate();
        message += "State Name : " + districtSessions.getState_name()+ "\n";
        message += "District Name : " + districtSessions.getDistrict_name()+ "\n";
        message += "Address: " + districtSessions.getName() + ", " + districtSessions.getAddress() + "\n";
        message += "Pin Code " + districtSessions.getPincode() + "\n";
        message += "Available Capacity " + districtSessions.getAvailable_capacity() + "\n";
        message += "Min Age Limit " + districtSessions.getMin_age_limit() + "\n";
        System.out.println(message);
        return message;
    }
}
