/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;
    import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 *
 * @author Lenovo
 */
public class openTripMapApi {

    private static final String API_KEY = "5ae2e3f221c38a28845f05b68c7e76071dd35dc99e898129768f3801";

    public static String getPlaceInfo(String placeName) throws IOException {
        String url = String.format("https://api.opentripmap.com/0.1/en/places/geoname?name=%s&apikey=%s", placeName, API_KEY);
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();
            return response.toString();
        } else {
            throw new IOException("Error getting place info from OpenTripMap API. Response code: " + responseCode);
        }
    }
}
    

