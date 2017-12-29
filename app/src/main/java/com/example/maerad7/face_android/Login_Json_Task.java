package com.example.maerad7.face_android;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

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
 * Created by maerad7 on 17. 12. 22.
 */

public class Login_Json_Task extends AsyncTask<String,String,String> {
    String result;
    Context mcontext;

    public Login_Json_Task(Context context){
        this.mcontext=context;
    }
    @Override
    protected String doInBackground(String... pParams) {

        JSONObject jsonObject = new JSONObject();
        String username = pParams[0];
        String password = pParams[1];
        String URL = pParams[2];
        try {
            jsonObject.accumulate("username", username);
            jsonObject.accumulate("password", password);
            HttpURLConnection con = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(URL);
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
        Login_Recieve login_recieve = new Login_Recieve();
        String flag=login_recieve.getLoginreturn();
        return flag;

    }
    @Override
    protected void onPostExecute(String flag){
      /*  String OK = "OK";
        if (flag.equals(OK)) {
            Intent intent = new Intent(mcontext, Missing_Person_List.class);
            mcontext.startActivity(intent);

        } else {
            Toast.makeText(mcontext, "Not Login", Toast.LENGTH_LONG);
            Intent intent = new Intent(mcontext,MainActivity.class);
            mcontext.startActivity(intent);

        }        super.onPostExecute(flag);



*/
    }




}

