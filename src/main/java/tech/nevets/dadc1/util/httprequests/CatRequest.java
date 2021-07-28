package tech.nevets.dadc1.util.httprequests;

import org.json.JSONArray;
import org.json.JSONObject;
import tech.nevets.dadc1.Config;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CatRequest {
    public static String url;

    public static void getHttpConnection() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .header("api_key", Config.getConfig().getString("command.api.cat.key"))
                .uri(URI.create(Config.getConfig().getString("command.api.cat.url")))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(CatRequest::parse)
                .join();
    }

    public static String parse(String response) {
        JSONArray ja = new JSONArray(response);

        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            url = jo.getString("url");
        }

        return null;
    }
}
