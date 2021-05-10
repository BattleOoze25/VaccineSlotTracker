package com.example.vaccineslottracker;

import UserPojo.Client;
import UserPojo.ClientData;
import UserPojo.District;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@Service
public class VaccineSlotService {
    private final RestTemplate restTemplate;
    public VaccineSlotService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    Map<String,Boolean> slotsPrinted = new HashMap<>();
    @Scheduled(fixedRate = 20000)
    @GetMapping("/hello")
    private void sendAlerts() throws Exception{
        try {
            String filePath = "src/main/resources/realusersInput.json";
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
                checkUrlsForDistrict(districtEmails,url);            }
        } catch (Exception ex){
            System.out.println("Na ho paa raha " + ex.getLocalizedMessage());
        }
    }

    public String checkUrlsForDistrict(ArrayList<String>districtEmails,String url) throws Exception {
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
            ApiData apiData = objectMapper.readValue(json, ApiData.class);
            checkAvailabilityforDistrict(apiData,districtEmails);
            return json;
        } catch (Exception ex){
            System.out.println("Cant hit url, check scheduling frequency");
            return ex.getLocalizedMessage();
        }
    }
    private void checkAvailabilityforDistrict(ApiData apiData,ArrayList<String>districtEmails){
        ArrayList<Centres> centers= apiData.getCenters();
        ArrayList<String> messages = new ArrayList<>();
        for (Centres centre : centers) {
            ArrayList<Session> sessions = centre.getSessions();
            for(Session session : sessions){
                int available_capacity = session.getAvailable_capacity();
                int min_age_limit = session.getMin_age_limit();
                if(available_capacity > 1 && min_age_limit == 18){
                    String message = printSession(session,centre);
                    if(message.equals("")){
                        continue;
                    }
                    messages.add(message);
                }
            }
        }
        // No session available
        if(messages.size() == 0) {
            return;
        }
        String text = "Vaccine slot available for your district, login to cowin to book your slot\n";
        text += "Link : https://selfregistration.cowin.gov.in/ \n";
        for(String message : messages){
            text += message + '\n';
        }
        String[] emails = new String[districtEmails.size()];
        emails = districtEmails.toArray(emails);
        EmailUtil.sendSimpleMessageMultiple(text,emails);
    }

    private String printSession(Session session, Centres centre) {

        int centre_id = centre.getCenter_id();
        String date = session.getSession_id();
        String key = Integer.toString(centre_id) + date;
        if (slotsPrinted.containsKey(key) == true) {
            return "";
        }

        slotsPrinted.put(key, true);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String message = "SLOT AVAILABLE !!!\n";
        message += "Date : " + session.getDate();
        message += "State Name : " + centre.getState_name()+ "\n";
        message += "District Name : " + centre.getDistrict_name()+ "\n";
        message += "Address: " + centre.getName() + ", " + centre.getAddress() + "\n";
        message += "Pin Code " + centre.getPincode() + "\n";
        message += "Available Capacity " + session.getAvailable_capacity() + "\n";
        message += "Min Age Limit " + session.getMin_age_limit() + "\n";
        System.out.println(message);
        return message;
    }
}
