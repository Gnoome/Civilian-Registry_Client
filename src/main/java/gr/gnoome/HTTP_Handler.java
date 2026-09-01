package gr.gnoome;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HTTP_Handler {

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
                request.PUT(HttpRequest.BodyPublishers.ofString(json));
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

   

    public static void SendCivilian(Person person) throws Exception {

        

        SendServerRequest(Post, json, json);


    }
}
