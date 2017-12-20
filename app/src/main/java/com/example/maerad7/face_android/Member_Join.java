package com.example.maerad7.face_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
            Toast.makeText(getApplicationContext(),"회원가입완료",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getApplication(),MainActivity.class);
            startActivity(intent);
        }
    };
}
