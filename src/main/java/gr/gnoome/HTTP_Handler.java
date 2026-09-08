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

    public static void SendServerRequest(String method, String url, String json) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest.Builder request = HttpRequest.newBuilder();
        request.uri(URI.create(url));
        request.header("Content-Type", "application/json");

        switch (method) {
            case "POST":
                request.POST(HttpRequest.BodyPublishers.ofString(json));
                break;
            case "PUT":
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

        System.out.println(response.statusCode());
        System.out.println(response.body());

    }

    public static void ViewAllCivilians() throws IOException, InterruptedException {
        SendServerRequest("GET", URL, null);
    }

    public static void SearchCivilian(Person person) throws IOException, InterruptedException {
       
        SendServerRequest("GET", URL + "/search" 
        + "?id=" + URLEncoder.encode(person.id, StandardCharsets.UTF_8)
         + "&name="+ person.name
         + "&surname=" + person.surname
          + "&gender=" + person.gender
           + "&birthdate="+ person.birthdate 
         + "&address=" + URLEncoder.encode(person.address, StandardCharsets.UTF_8)
          + "&tax=" + URLEncoder.encode(person.tax, StandardCharsets.UTF_8), null);
    }

    public static void SendCivilian(Person person) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(person);
        SendServerRequest("POST", URL, json);
    }

    public static void UpdateCivilian(Person person) throws IOException, InterruptedException {
        SendServerRequest("PUT", URL+"/"+person.id+"?address="+URLEncoder.encode(person.address, StandardCharsets.UTF_8)+"&tax="+URLEncoder.encode(person.tax, StandardCharsets.UTF_8), null);
    }

    public static void DeleteCivilian(String id) throws IOException, InterruptedException {
        SendServerRequest("DELETE", URL+"/"+id, null);
    }


}
