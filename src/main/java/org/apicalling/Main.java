package org.apicalling;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        URL url = null;
        HttpURLConnection connection = null;
        int responseCode = 0;
        String ulrstring = "https://api.zippopotam.us/us/33162";

        try{
            url = new URL(ulrstring);
        }catch (MalformedURLException e){
            System.out.println("problem in url");
        }
        try {
            connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();
        }catch (Exception e){
            System.out.println("connection problem");
        }
        if(responseCode==200){
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder apiData = new StringBuilder();
            String readLine = null;

            while((readLine = in.readLine())!= null)
            {
                apiData.append(readLine);
            }
            //
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            JSONObject jsonAPI = new JSONObject(apiData.toString());
            System.out.println(jsonAPI.get("post code"));
            System.out.println(jsonAPI.get("country"));
            System.out.println(jsonAPI.get("country abbreviation"));
            System.out.println(jsonAPI.get("places"));

        }else {
            System.out.println("API call could not be made!!!");
        }
    }
}