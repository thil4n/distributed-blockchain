package net.hacksland;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkClient {
    public static void postJSON(String targetUrl, String jsonData) {
        try {
            URL url = new URL(targetUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonData.getBytes());
                os.flush();
            }

            conn.getResponseCode(); // trigger the request
            conn.disconnect();
        } catch (Exception e) {
            System.err.println("Error sending request to " + targetUrl + ": " + e.getMessage());
        }
    }
}
