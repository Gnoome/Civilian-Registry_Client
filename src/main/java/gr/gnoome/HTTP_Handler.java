package gr.gnoome;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HTTP_Handler {

    private static void SendServerRequest(String method, String url, String json){

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest.Builder request = HttpRequest.newBuilder();
        request.uri(URI.create(url));
        
        switch(method){
            case "POST":
                request.POST(HttpRequest.BodyPublisher.of);
            

        }
    }


    static ObjectMapper mapper = new ObjectMapper();
    
    public static void SendCivilian(Person person) throws Exception{
        
        String json = mapper.writeValueAsString(person);

    }
}
