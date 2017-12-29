package com.example.maerad7.face_android;

import android.view.View;

import org.json.JSONArray;
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
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by maerad7 on 17. 12. 22.
 */

public class Missing_Person_Information_detail_API {
    final static String Missing_Person_Information_detailURL = "http://192.168.0.191:5000/sendMIssingInformation";



    //id 파라미터 로 JSON 파싱하
    public Missing_Person_Information_detail_Json getMissing_Person_Information_detail_Json(int memberID) {
        Missing_Person_Information_detail_Json missing_person_information_detail_json = new Missing_Person_Information_detail_Json();
        String urlString = Missing_Person_Information_detailURL +'/'+ memberID;

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));

            missing_person_information_detail_json = parseJSON(json);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return missing_person_information_detail_json;


    }

    //jSON PARSE



    public void getMissing_Person_Information_Json(View view) {


    }

    private static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    ;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
    private Missing_Person_Information_detail_Json parseJSON(JSONObject json) throws JSONException {

        Missing_Person_Information_detail_Json missing_person_information_detail_json = new Missing_Person_Information_detail_Json();
        JSONArray values = json.getJSONArray("sendMissingInformation");

        JSONObject M = values.getJSONObject(0);
        missing_person_information_detail_json.disappearanceAddress = M.getString("Disappearance_Address");
        missing_person_information_detail_json.mPName=M.getString("MP_Name");
        missing_person_information_detail_json.missingAge=M.getString("Missing_age");
        missing_person_information_detail_json.disappearanceDate=M.getString("Disappearance_Date");
        missing_person_information_detail_json.missingHeight=M.getString("Missing_height");
        missing_person_information_detail_json.missingWeight=M.getString("Missing_Weight");


        return missing_person_information_detail_json;
    }
/*
    //id 파라미터 로 JSON 파싱하
    public Missing_Person_Information_Json getMissing_Person_Information_Json(String id) {
        Missing_Person_Information_Json missing_Person_Information_Json = new Missing_Person_Information_Json();

        try {
            URL url = new URL(sendMissing_Person_InformationURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));

            missing_Person_Information_Json = parseJSON1(json,id);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return missing_Person_Information_Json;


    }*/
/*
    private Missing_Person_Information_Json parseJSON1(JSONObject json,String id) throws JSONException {

        Missing_Person_Information_Json missing_Person_Information_Json = new Missing_Person_Information_Json();
        JSONArray values = json.getJSONArray("Missing_Person_Information_Json");

        JSONObject M = values.getJSONObject(0);
        missing_Person_Information_Json.disappearanceAddress = M.getString("Disappearance_Address");
        missing_Person_Information_Json.disappearanceDate = M.getString("Disappearance_Date");
        missing_Person_Information_Json.mPName = M.getString("MP_Name");
        missing_Person_Information_Json.missingPersonID = M.getInt("MissingPerson_ID");
        missing_Person_Information_Json.missingWeight = M.getString("Missing_Weight");
        missing_Person_Information_Json.missingAge = M.getString("Missing_age");
        missing_Person_Information_Json.missingHeight = M.getString("Missing_height");
        missing_Person_Information_Json.memberID = M.getString("Member_ID");

        return missing_Person_Information_Json;
    }*/

}
