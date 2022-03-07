package blackjack.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Connects to open source data server, OpenWeather. Using API
 * don't forget org.json.jar
 * @author Tomas Alander
 * @version 2022-03-07
 */
public class OpenWeatherApi {
    private HttpURLConnection con;
    private String inPutLine;
    private final String openWeatherApiKey = "084cbb00a74eb1329949d90952c5c519";
    private final double lasVegasLat = 36.114647;
    private final double lasVegasLong = -115.172813;

    /**
     *  A constructor for class OpenWeatherApi
     */
    public OpenWeatherApi() {
    }

    /**
     * Establish connection to OpenWeather open data server using API.
     * @pre  -90<=lat<=90, -180<=lon<=180
     * @param lat
     * @param lon
     * @return return a string in JSON-format with the current weather data from the given location.
     */
    public String getLocalWeather(double lat, double lon) {
        String ret = "";
        String urlAdress = " http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + openWeatherApiKey;
        if(lat<-90 || lat>90 || lon>180 ||lon<-180){
            return  ret = "Coordinates dont exist";
        }
        try{
            con = getConnected(urlAdress);
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((inPutLine = reader.readLine()) != null) {
                //System.out.println(inPutLine);
                ret += inPutLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            con.disconnect();
        }
        return ret;
    }

    public double getLasVegasLongitude(){
        return this.lasVegasLong;
    }

    public double getLasVegasLatitude(){
        return this.lasVegasLat;
    }

    private HttpURLConnection getConnected(String urlAddress) throws IOException {
            URL url = new URL(urlAddress);
            con=(HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setConnectTimeout(10000);
            con.getReadTimeout();
            int connectionInfo = con.getResponseCode();
            System.out.println("LocalWeather response code " + connectionInfo);
            return con;
    }
}
