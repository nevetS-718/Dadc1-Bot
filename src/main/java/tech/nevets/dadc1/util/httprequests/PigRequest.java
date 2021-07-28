package tech.nevets.dadc1.util.httprequests;

import org.json.JSONArray;
import org.json.JSONObject;
import tech.nevets.dadc1.Config;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PigRequest {
    public static String url;

    public static void getHttpConnection() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .header("x-rapidapi-host", Config.getConfig().getString("command.api.pig.host"))
                .header("x-rapidapi-key", Config.getConfig().getString("command.api.pig.key"))
                .uri(URI.create(Config.getConfig().getString("command.api.pig.url")))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(PigRequest::parse)
                .join();
    }

    public static String parse(String response) {

        System.out.print("Response: " + response + "\n");

        String mod = "[ " + response + " ]";

        System.out.print(mod + "\n");

        JSONArray ja = new JSONArray(mod);

        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            url = jo.getString("source");
        }

        return null;
    }
}
