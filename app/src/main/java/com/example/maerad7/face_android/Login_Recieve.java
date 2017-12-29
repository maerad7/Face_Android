package com.example.maerad7.face_android;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by maerad7 on 17. 12. 22.
 */

public class Login_Recieve {
    final static String sendlogin_InfromationURL = "http://192.168.0.191:5000/postjson";
    public String getLoginreturn() {
        String login_result = null;
        URL url = null;
        try {
            url = new URL(sendlogin_InfromationURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            login_result= getStringFromInputStream(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }return login_result;

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
