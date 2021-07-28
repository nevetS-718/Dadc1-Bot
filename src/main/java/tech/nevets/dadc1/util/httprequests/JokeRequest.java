package tech.nevets.dadc1.util.httprequests;

import tech.nevets.dadc1.Config;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JokeRequest {

    public static String joke;

    public static void getHttpConnection() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "text/plain")
                .uri(URI.create(Config.getConfig().getString("command.api.dadjoke")))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        joke = response.body();
    }
}
