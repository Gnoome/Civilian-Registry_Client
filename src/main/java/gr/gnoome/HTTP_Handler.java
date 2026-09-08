package gr.gnoome;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HTTP_Handler {

    private static String URL =  "http://localhost:8080/Civilian_REST/api/Civilians";

    public static HttpResponse<String> sendServerRequest(String method, String url, String json) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest.Builder request = HttpRequest.newBuilder();
        request.uri(URI.create(url));
        request.header("Content-Type", "application/json");

        switch (method) {
            case "POST":
                request.POST(HttpRequest.BodyPublishers.ofString(json));
                break;
            case "PATCH":
                request.method("PATCH",HttpRequest.BodyPublishers.noBody());
                break;
            case "GET":
                request.GET();
                break;
            case "DELETE":
                request.DELETE();
                break;

        }

        HttpResponse<String> response = client.send(request.build(),HttpResponse.BodyHandlers.ofString());

        return response;
        

    }

    public static void viewAllCivilians() throws IOException, InterruptedException {
        HttpResponse<String> response = sendServerRequest("GET", URL, null);

        ObjectMapper objectMapper = new ObjectMapper();
        Person[] persons = objectMapper.readValue(response.body(), Person[].class);
        for (Person person : persons) {
            System.out.println(person);
        }
        
    }

    public static void searchCivilian(Person person) throws IOException, InterruptedException {
       
        StringBuilder url = new StringBuilder(URL + "/search");
        boolean firstParam = true;

        if (person.id != null){
            url.append(firstParam ? "?" : "&");
            url.append("id=").append(URLEncoder.encode(person.id, StandardCharsets.UTF_8));
            firstParam = false;
        }
        if (person.name != null){
            url.append(firstParam ? "?" : "&");
            url.append("name=").append(URLEncoder.encode(person.name, StandardCharsets.UTF_8));
            firstParam = false;
        }
        if (person.surname != null){
            url.append(firstParam ? "?" : "&");
            url.append("surname=").append(URLEncoder.encode(person.surname, StandardCharsets.UTF_8));
            firstParam = false;
        }
        if (person.gender != null){
            url.append(firstParam ? "?" : "&");
            url.append("gender=").append(URLEncoder.encode(person.gender, StandardCharsets.UTF_8));
            firstParam = false;
        }
        if (person.birthdate != null){
            url.append(firstParam ? "?" : "&");
            url.append("birthdate=").append(URLEncoder.encode(person.birthdate, StandardCharsets.UTF_8));
            firstParam = false;
        }
        if (person.address != null){
            url.append(firstParam ? "?" : "&");
            url.append("address=").append(URLEncoder.encode(person.address, StandardCharsets.UTF_8));
            firstParam = false;
        }
        if (person.tax != null){
            url.append(firstParam ? "?" : "&");
            url.append("tax=").append(URLEncoder.encode(person.tax, StandardCharsets.UTF_8));
        }

        HttpResponse<String> response = sendServerRequest("GET", url.toString(), null);
        ObjectMapper objectMapper = new ObjectMapper();
        Person[] persons = objectMapper.readValue(response.body(), Person[].class);
        for (Person p : persons) {
            System.out.println(p);
        }
    }

    public static void sendCivilian(Person person) throws IOException, InterruptedException {

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(person);
        HttpResponse<String> response = sendServerRequest("POST", URL, json);
        System.out.println(response.body());
    }

    public static void updateCivilian(Person person) throws IOException, InterruptedException {
         
        StringBuilder url = new StringBuilder(URL + "/"+person.id);


         if (person.address != null){
            url.append("?address=").append(URLEncoder.encode(person.address, StandardCharsets.UTF_8));
            
        }
        if (person.tax != null){
            url.append(person.address==null? "?" : "&");
            url.append("tax=").append(URLEncoder.encode(person.tax, StandardCharsets.UTF_8));
        }
        
       HttpResponse<String> response = sendServerRequest("PATCH", url.toString(), null);
       System.out.println(response.body());
    }

    public static void deleteCivilian(String id) throws IOException, InterruptedException {

       HttpResponse<String> response = sendServerRequest("DELETE", URL+"/"+id, null);
       System.out.println(response.body());
    }


}
