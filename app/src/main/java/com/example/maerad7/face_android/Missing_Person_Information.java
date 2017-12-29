package com.example.maerad7.face_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class Missing_Person_Information extends AppCompatActivity {
    final int MP_List=20;
    final int MP_register=21;
    final int image_compare=22;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing__person__information);
        TextView Disappearance_AddressView=(TextView) findViewById(R.id.Disappearance_Address);
        TextView Name_View=(TextView)findViewById(R.id.Name);
        TextView Missing_age_View=(TextView)findViewById(R.id.Missing_age);
        TextView Missing_height_View=(TextView)findViewById(R.id.Missing_height);
        TextView Missing_wigeht_View=(TextView)findViewById(R.id.Missing_Weight);
        TextView Disappearance_dateView=(TextView)findViewById(R.id.Disappearance_Date);
        Bundle bundle=getIntent().getExtras();
        String id = bundle.getString("ID");
        int member_id = Integer.parseInt(id);
        Missing_Person_Information_detail_APItask t = new Missing_Person_Information_detail_APItask();
        try{
            Missing_Person_Information_detail_Json missing_person_information_detail_json= (Missing_Person_Information_detail_Json) t.execute(member_id).get();

            Disappearance_AddressView.setText(missing_person_information_detail_json.getDisappearanceAddress());
            Name_View.setText(missing_person_information_detail_json.getMPName());
            Missing_age_View.setText(missing_person_information_detail_json.getMissingAge());
            Missing_height_View.setText(missing_person_information_detail_json.getMissingHeight());
            Missing_wigeht_View.setText(missing_person_information_detail_json.getMissingWeight());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void getMissing_Person_Information_Json(View view){
        //@SuppressLint("WrongViewCast") EditText editText2=(EditText)findViewById(R.id.Disappearance_Address1);
        //String Disappearance_Address=editText2.setText();


    }









    @Override
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
    }

}

