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

/**
 * Created by maerad7 on 17. 12. 21.
 */

public class Member_Infromation_API {


    final static String sendMember_InfromationURL = "http://192.168.0.191:5000/sendMember_Infromation";

    public Member_Information_Json getMember_Information_Json() {
        Member_Information_Json member_infromation_json = new Member_Information_Json();

        try {
            URL url = new URL(sendMember_InfromationURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));
            member_infromation_json = parseJSON(json);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return member_infromation_json;
    }
    //jSON PARSE
    private Member_Information_Json parseJSON(JSONObject json) throws JSONException {

        Member_Information_Json member_information_json = new Member_Information_Json();
        JSONArray values=json.getJSONArray("Member_Informaiton_Json");

        JSONObject M = values.getJSONObject(0);
        member_information_json.address=M.getString("Address");
        member_information_json.email=M.getString("Email");
        member_information_json.iD=M.getString("ID");
        member_information_json.memberGrade=M.getInt("Member_Grade");
        member_information_json.memberID=M.getString("Member_ID");
        member_information_json.name=M.getString("Name");
        member_information_json.password=M.getString("Password");
        member_information_json.phoneNumber=M.getString("PhoneNumber");
        member_information_json.registrationDate=M.getString("Registration_Date");



        return member_information_json;
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
