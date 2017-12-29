package com.example.maerad7.face_android;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private static final int INTERNET_REQUEST_CODE = 101;
    private static final int NETWORK_REQUEST_CODE=102;
    private static String TAG = "PermissionDemo";
    String flag=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button LoginButton=(Button)findViewById(R.id.Login);
        Button Member_Join_Button=(Button)findViewById(R.id.Member_Join);
       int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
       if ( permission != PackageManager.PERMISSION_GRANTED)
       {
           Log.i(TAG,"Permission to record denied");
        makeRequest();
       }

        LoginButton.setOnClickListener(onClickListener);
        Member_Join_Button.setOnClickListener(onClickListener);


    }Button.OnClickListener onClickListener;

    {
        onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                switch (id) {
                    case R.id.Login:
                        Login_Json_Task login_json_task = new Login_Json_Task(view.getContext());

                        EditText editText_ID = (EditText) findViewById(R.id.ID);
                        EditText editText_PW = (EditText) findViewById(R.id.Password);
                        String ID = editText_ID.getText().toString();
                        String PassWord = editText_PW.getText().toString();
                        String URL = "http://192.168.0.191:5000/postjson";
                        try {
                            flag = login_json_task.execute(ID, PassWord, URL).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        String OK = "OK";
                        if (flag.equals(OK)) {
                            Intent intent = new Intent(getApplication(), Missing_Person_List.class);
                            SharedPreferences.Editor editor = getSharedPreferences("Login_Information", Context.MODE_PRIVATE).edit();
                            editor.putString("ID",ID);
                            editor.commit();
                            Login_Send_Task login_send_task = new Login_Send_Task();
                            login_send_task.execute(ID,"http://192.168.0.191:5000/Missing_register");
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(), "Not Login", Toast.LENGTH_LONG);
                            Intent intent = new Intent(getApplication(),MainActivity.class);
                            startActivity(intent);

                        }
                        break;

                    case R.id.Member_Join:
                        Intent Member_Join_intent = new Intent(getApplication(), Member_Join.class);
                        startActivity(Member_Join_intent);
                        break;
                }
            }
        };
    }

    protected void makeRequest(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},INTERNET_REQUEST_CODE);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_NETWORK_STATE},NETWORK_REQUEST_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permission[],int[] grantResults){
        switch (requestCode){
            case INTERNET_REQUEST_CODE:
            {
                if (grantResults.length==0||grantResults[0]!=PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "Permission denied");
                }
                else{
                    Log.i(TAG,"Permission grated");
                    }
                return;

            }
        }
    }
}
