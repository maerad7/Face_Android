package com.example.maerad7.face_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Image_Compare_Complete extends AppCompatActivity {
    final int MP_List=20;
    final int MP_register=21;
    final int image_compare=22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image__compare__complete);

    }@Override
    public boolean onCreateOptionsMenu(Menu menu){
        boolean result=super.onCreateOptionsMenu(menu);
        menu.add(0,MP_List,1,"실종자목록");
        menu.add(0,MP_register,2,"실종자등록");
        menu.add(0,image_compare,3,"이미지비교");
        return result;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String menuString=(String)item.getTitle();
        boolean returnResult;
        switch (item.getItemId()){
            case MP_List:
                Intent intent=new Intent(getApplication(),Missing_Person_List.class);
                startActivity(intent);
                returnResult = true ;
                break;
            case MP_register:
                Intent MP_register_intent=new Intent(getApplication(),Missing_Person_register.class);
                startActivity(MP_register_intent);
                returnResult = true;
                break;
            case image_compare:
                Intent Image_compare_intent=new Intent(getApplication(),Image_Compare.class);
                startActivity(Image_compare_intent);

            default:
                returnResult= super.onOptionsItemSelected(item);
        }
        Toast.makeText(getApplicationContext(),menuString,Toast.LENGTH_SHORT).show();
        return returnResult;
        //11111
    }
}
//test merge2