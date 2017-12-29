package com.example.maerad7.face_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Missing_Person_List extends AppCompatActivity {
    final int MP_List=20;
    final int MP_register=21;
    final int image_compare=22;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing__person__list);
        ArrayList<HashMap<String,Object>> arrayList=new ArrayList<HashMap<String,Object>>();
        HashMap<String,Object> hashMap=null;
        hashMap = new HashMap<String,Object>();
        /*hashMap.put("MP_Name","yerin");
        hashMap.put("Disappearance_Address","Seoul");
        hashMap.put("Image",R.drawable.yerin);
        arrayList.add(hashMap);
        HashMap<String,Object> hashMap2=null;
        hashMap2 = new HashMap<String,Object>();
        hashMap2.put("MP_Name","yerin");
        hashMap2.put("Disappearance_Address","Seoul");
        hashMap2.put("Image",R.drawable.yerin);
        arrayList.add(hashMap2);*/
        Missing_Person_Information_APITask t = new Missing_Person_Information_APITask();
        try{
            arrayList= (ArrayList) t.execute().get();
            recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            adapter=new RecyclerAdapter(arrayList);
            recyclerView.setAdapter(adapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


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

//test6
