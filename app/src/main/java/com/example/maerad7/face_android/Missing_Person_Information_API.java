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
 * Created by maerad7 on 17. 12. 19.
 */

public class Missing_Person_Information_API {


    final static String sendMissing_Person_InformationURL = "http://192.168.0.191:5000/sendMissing_Person_Information";

    // json list
    public ArrayList getArrayListMissing_Person_Information_Json() {
        Missing_Person_Information_Json missing_Person_Information_Json = new Missing_Person_Information_Json();

        try {
            URL url = new URL(sendMissing_Person_InformationURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));

            arrayList = parseJSON(json);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();

    //jSON PARSE
    private ArrayList parseJSON(JSONObject json) throws JSONException {

        JSONArray values = json.getJSONArray("Missing_Person_Information_Json");
        for (int i = 0; i < values.length(); i++) {
            Missing_Person_Information_Json missing_Person_Information_Json = new Missing_Person_Information_Json();
            HashMap<String, Object> hashMap = null;
            hashMap = new HashMap<String, Object>();
            JSONObject M = values.getJSONObject(i);
            missing_Person_Information_Json.disappearanceAddress = M.getString("Disappearance_Address");
            hashMap.put("Disappearance_Address", missing_Person_Information_Json.getDisappearanceAddress());
            missing_Person_Information_Json.disappearanceDate = M.getString("Disappearance_Date");
            hashMap.put("Disappearance_Date", missing_Person_Information_Json.getDisappearanceDate());

            missing_Person_Information_Json.mPName = M.getString("MP_Name");
            hashMap.put("MP_Name", missing_Person_Information_Json.getMPName());

            missing_Person_Information_Json.missingPersonID = ( M.getInt("MissingPerson_ID"));

            hashMap.put("MissingPerson_ID", String.valueOf(missing_Person_Information_Json.getMissingPersonID()));

            missing_Person_Information_Json.missingWeight = M.getString("Missing_Weight");
            hashMap.put("Missing_Weight", missing_Person_Information_Json.getMissingWeight());

            missing_Person_Information_Json.missingAge = M.getString("Missing_age");
            hashMap.put("Missing_age", missing_Person_Information_Json.getMissingAge());

            missing_Person_Information_Json.missingHeight = M.getString("Missing_height");
            hashMap.put("Missing_height", missing_Person_Information_Json.getMissingHeight());

            missing_Person_Information_Json.memberID=M.getString("Member_ID");
            hashMap.put("Member_ID",missing_Person_Information_Json.getMemberID());
            arrayList.add(hashMap);
        }
        return arrayList;
    }


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

