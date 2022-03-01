package blackjack.models;

import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Connects to open source data server, OpenWeather. Using API
 * don't forget org.json.jar
 * @author Tomas Alander
 */
public class OpenWeatherApi {
    private HttpURLConnection con;
    private String inputline;
    private String openWeatherApiKey = "084cbb00a74eb1329949d90952c5c519";
    private double lasVegasLat = 36.114647;
    private double lasVegasLong = -115.172813;

    public OpenWeatherApi() {
    }

    /**
     * Establish connection to OpenWeather open data server using API.
     * @param city
     * @return the param city Geocode in JSON-format as a String.
     */
    public String getGeocoding(String city) {
        String ret = "";
        try {
            URL url = new URL(" http://api.openweathermap.org/geo/" +
                    "1.0/direct?q=" + city + "&limit=5&appid=084cbb00a74eb1329949d90952c5c519");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setConnectTimeout(10000);
            con.getReadTimeout();
            int connectionInfo = con.getResponseCode();
            System.out.println(connectionInfo);
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((inputline = reader.readLine()) != null) {
                System.out.println(inputline);
                ret += inputline;
            }
            con.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Establish connection to OpenWeather open data server using API.
     * @param lat
     * @param lon
     * @return return a string in JSON-format with the current weather data from the given location.
     */
    public String getLocalWeather(double lat, double lon) {
        String ret = "";
        try {
              URL url = new URL(" http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + openWeatherApiKey);
              con = (HttpURLConnection) url.openConnection();
              con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
              con.setConnectTimeout(10000);
              con.getReadTimeout();
              int connectionInfo = con.getResponseCode();
              System.out.println(connectionInfo);
              BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
              while ((inputline = reader.readLine()) != null) {
                  System.out.println(inputline);
                  ret += inputline;
            }
            con.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
    //for test purpose
    /*
    public static void main(String[] arg) throws JSONException {
        OpenWeatherApi APIquery = new OpenWeatherApi();
        String json = APIquery.getLocalWeather(APIquery.lasVegasLat,APIquery.lasVegasLong);
        JSONObject jsonObj = new JSONObject(json);
        System.out.println("Pretty Print of JSON:");
        System.out.println(jsonObj.toString(4));
    }
     */
}
// private String LasVegas = "Las Vegas	US	5506956	-115.137222	36.174969";
// private String LasVegasLat = "36.114647";
// private String LasVegasLong = "-115.172813";
//String json = APIquery.getGeocoding("哥德堡"); // Fungerar ej med JSONObject
