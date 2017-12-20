package com.example.maerad7.face_android;

import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by maerad7 on 17. 12. 19.
 */

public class Missing_Person_Information_API {


    final static String openWeatherURL = "http://192.168.0.191:5000/sendMissing_Person_Information/";

    public Missing_Person_Information_Json getMissing_Person_Information_Json() {
        Missing_Person_Information_Json missing_Person_Information_Json = new Missing_Person_Information_Json();

        try {
            URL url = new URL(openWeatherURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));
            missing_Person_Information_Json = parseJSON(json);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return missing_Person_Information_Json;
    }

        private Missing_Person_Information_Json parseJSON(JSONObject json) throws JSONException {

            Missing_Person_Information_Json missing_Person_Information_Json = new Missing_Person_Information_Json();
            missing_Person_Information_Json.setDisappearanceAddress(json.getJSONObject("Missing_Person_Information_Json").getString("Disappearance_Address"));


            //w.setCloudy();



            return missing_Person_Information_Json;

        }








    public void getMissing_Person_Information_Json(View view){


    }
    private static String getStringFromInputStream(InputStream is){
        BufferedReader br = null;
        StringBuilder sb =new StringBuilder();

        String line;
        try{
            br= new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(br != null){
                try{
                    br.close();;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }return sb.toString();
    }
}


