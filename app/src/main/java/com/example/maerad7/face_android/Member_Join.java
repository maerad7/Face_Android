package com.example.maerad7.face_android;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class Member_Join extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member__join);
        Button Member_Join=(Button)findViewById(R.id.Member_Join);
        Member_Join.setOnClickListener(onClickListener);
    }Button.OnClickListener onClickListener= new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            Member_Join_Task member_join_task= new Member_Join_Task();
            EditText EDIT_ID =(EditText)findViewById(R.id.ID);
            EditText EDIT_Name = (EditText)findViewById(R.id.NAME);
            EditText EDIT_Password=(EditText)findViewById(R.id.PASSWORD);
            EditText EDIT_Address=(EditText)findViewById(R.id.ADDRESS);
            EditText EDIT_Email=(EditText)findViewById(R.id.EMAIL);
            EditText EDIT_PhoneNumber=(EditText)findViewById(R.id.PHONENUMBER);
            String id = (UUID.randomUUID().toString());
            String Member_ID=EDIT_ID.getText().toString();
            String Password = EDIT_Password.getText().toString();
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd.hh:mm:ss");
            String Registration_Date= String.valueOf(sdf.format(date).toString());
            String PhoneNumber = EDIT_PhoneNumber.getText().toString();
            String Name = EDIT_Name.getText().toString();
            String Member_Grade= "1";
            String Address=EDIT_Address.getText().toString();
            String Email=EDIT_Email.getText().toString();
            String URL = "http://192.168.0.191:5000/member_join";
            member_join_task.execute(id,Member_ID,Name,Password,Registration_Date,PhoneNumber,Member_Grade,Address,Email,URL);
            Toast.makeText(getApplicationContext(),"회원가입완료",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getApplication(),MainActivity.class);
            startActivity(intent);
        }
    };
}
