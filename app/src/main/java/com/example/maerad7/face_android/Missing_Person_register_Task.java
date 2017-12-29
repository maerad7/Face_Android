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
 * Created by maerad7 on 17. 12. 26.
 */

public class Missing_Person_register_Task extends AsyncTask<String,String,String> {
    @Override
    protected String doInBackground(String... pParams) {
        JSONObject jsonObject = new JSONObject();
        String MissingPerson_ID = pParams[0];
        String Disappearance_Address = pParams[1];
        String MP_Name = pParams[2];
        String Disappearance_Date = pParams[3];
        String Missing_age = pParams[4];
        String Missing_height =pParams[5];
        String Missing_Weight = pParams[6];
        //String Member_ID = pParams[7];
        String Image_Information_ID= pParams[7];
        //String Image_Path=pParams[8];
       // String Image_Size=pParams[9];
        String Image_Name=pParams[8];
        String Image_Physical_Address=pParams[9];
        //String Mini_Image_Name=pParams[12];
        //String Mini_Image_Physical_Path=pParams[13];
        //String Image_SIze_Limit=pParams[14];
        //String Mini_Image_Path=pParams[15];
        String URL = pParams[10];
        String ID = pParams[11];


        try {
            jsonObject.accumulate("MissingPerson_ID", MissingPerson_ID);
            jsonObject.accumulate("Disappearance_Address", Disappearance_Address);
            jsonObject.accumulate("MP_Name", MP_Name);
            jsonObject.accumulate("Disappearance_Date", Disappearance_Date);
            jsonObject.accumulate("Missing_age", Missing_age);
            jsonObject.accumulate("Missing_height", Missing_height);
            jsonObject.accumulate("Missing_Weight", Missing_Weight);
            //jsonObject.accumulate("Member_ID", Member_ID);
            jsonObject.accumulate("Image_Information_ID", Image_Information_ID);
            //jsonObject.accumulate("Image_Path", Image_Path);
            //jsonObject.accumulate("Image_Size", Image_Size);
            //jsonObject.accumulate("Image_Name", Image_Name);
            jsonObject.accumulate("Image_Physical_Address", Image_Physical_Address);
            //jsonObject.accumulate("Mini_Image_Name", Mini_Image_Name);
           // jsonObject.accumulate("Mini_Image_Physical_Path", Mini_Image_Physical_Path);
           // jsonObject.accumulate("Image_SIze_Limit", Image_SIze_Limit);
           // jsonObject.accumulate("Mini_Image_Path",Mini_Image_Path);
            jsonObject.accumulate("URL",URL);
            jsonObject.accumulate("ID",ID);
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
