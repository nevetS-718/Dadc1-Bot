package tech.nevets.dadc1.util.httprequests;

import org.json.JSONArray;
import org.json.JSONObject;
import tech.nevets.dadc1.Config;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FoxRequest {
    public static String url;

    public static void getHttpConnection() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(Config.getConfig().getString("command.api.fox.url")))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(FoxRequest::parse)
                .join();
    }

    public static String parse(String response) {
        String mod = "[ " + response + " ]";
        JSONArray ja = new JSONArray(mod);

        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            url = jo.getString("image");
        }

        return null;
    }
}