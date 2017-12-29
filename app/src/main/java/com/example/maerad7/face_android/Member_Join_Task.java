package com.example.maerad7.face_android;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by maerad7 on 17. 12. 23.
 */

public class Member_Join_Task extends AsyncTask<String,String,String> {
    String result;

    @Override
    protected String doInBackground(String... pParams) {

        JSONObject jsonObject = new JSONObject();
        String username = pParams[0];
        String ID = pParams[1];
        String Name = pParams[2];
        String Password = pParams[3];
        String Registration_Date = pParams[4];
        String PhoneNumber = pParams[5];
        String Member_Grade = pParams[6];
        String Address = pParams[7];
        String Email = pParams[8];
        String URL = pParams[9];
        try {
            jsonObject.accumulate("Member_ID", username);
            jsonObject.accumulate("ID", ID);
            jsonObject.accumulate("Name", Name);
            jsonObject.accumulate("Password", Password);
            jsonObject.accumulate("Registration_Date", Registration_Date);
            jsonObject.accumulate("PhoneNumber", PhoneNumber);
            jsonObject.accumulate("Member_Grade", Member_Grade);
            jsonObject.accumulate("Address", Address);
            jsonObject.accumulate("Email", Email);
            HttpURLConnection con = null;
            BufferedReader reader = null;
            try {
                java.net.URL url = new URL(URL);
                //연결을 함
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");//POST방식으로 보냄
                con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
                con.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
                con.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
                con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
                con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
                con.connect();
                OutputStream outStream = con.getOutputStream();
                //버퍼를 생성하고 넣음
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
                writer.write(jsonObject.toString());
                writer.flush();
                writer.close();//버퍼를 받아줌
                //서버로 부터 데이터를 받음
                InputStream stream = con.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);


                }

                return buffer.toString();//서버로 부터 받은 값을 리턴해줌 아마 OK!!가 들어올것임
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();//버퍼를 닫아줌
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}