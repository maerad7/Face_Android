package com.example.maerad7.face_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button LoginButton=(Button)findViewById(R.id.Login);
        Button Member_Join_Button=(Button)findViewById(R.id.Member_Join);
        LoginButton.setOnClickListener(onClickListener);
        Member_Join_Button.setOnClickListener(onClickListener);
    }Button.OnClickListener onClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch(id){
                case R.id.Login:
                    Intent intent=new Intent(getApplication(),Missing_Person_List.class);
                    startActivity(intent);
                    break;
                case R.id.Member_Join:
                    Intent Member_Join_intent=new Intent(getApplication(),Member_Join.class);
                    startActivity(Member_Join_intent);
                    break;
            }
        }
    };
}
