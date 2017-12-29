package com.example.maerad7.face_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class Missing_Person_register extends AppCompatActivity {
    final int MP_List=20;
    final int MP_register=21;
    final int image_compare=22;
    String MissingPerson_ID;
    String Disappearance_Address;
    String Disappearance_Date;
    String MP_Name;
    String Missing_age;
    String Missing_height;
    String Missing_Weight;
    String Image_Information_ID;
    String Image_Name;
    String Image_Physical_Address;
    String URL;
    String ID;
    InputStream inputStream = null;
    Uri selectedImage=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing__person_register);
        ImageButton imageButtonmageButton = (ImageButton)findViewById(R.id.imageButton);
        TextView Name_textview = (TextView)findViewById(R.id.Name);
        TextView Disappearance_Address_textview=(TextView)findViewById(R.id.Disappearance_Address);
        TextView Missing_age_textview=(TextView)findViewById(R.id.Missing_age);
        TextView Disappearance_Date_textview=(TextView)findViewById(R.id.Disappearance_Date);
        TextView Missing_height_textview=(TextView)findViewById(R.id.Missing_height);
        TextView Missing_Weight_textview=(TextView)findViewById(R.id.Missing_Weight);
        Button MP_button = (Button)findViewById(R.id.MP_Button);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd.hh:mm:ss");
        //MemberID = server
        MissingPerson_ID=(UUID.randomUUID().toString());
        Disappearance_Address = Disappearance_Address_textview.getText().toString();
        MP_Name= Name_textview.getText().toString();
        Disappearance_Date= Disappearance_Date_textview.getText().toString();
        Missing_age=Missing_age_textview.getText().toString();
        Missing_height=Missing_height_textview.getText().toString();
        Missing_Weight=Missing_Weight_textview.getText().toString();
        Image_Information_ID = (UUID.randomUUID().toString());
        Image_Name=Name_textview.getText().toString();
        Image_Physical_Address=Name_textview.getText().toString()+String.valueOf(sdf.format(date).toString());
        URL = "http://192.168.0.191:5000/Missng_register";
        SharedPreferences sharedPreferences = getSharedPreferences("Login_Information", Context.MODE_PRIVATE);
        ID = sharedPreferences.getString("ID",null);

        MP_button.setOnClickListener(monclickListner);
        imageButtonmageButton.setOnClickListener(onClickListener);

    }ImageButton.OnClickListener onClickListener= new ImageButton.OnClickListener() {
        @Override
        public void onClick(View v) {
            int LOAD_IMAGE=102;
            Intent intent = new Intent();
            intent.setType("image/*");   //가져오려하는 종류
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, LOAD_IMAGE);
        }


    };Button.OnClickListener monclickListner = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch(id){
                case R.id.MP_Button:
                    String URL1="http://192.168.0.191:5000/img_Upload";
                    String image= null;
                    String path = null;
                    path = getRealPathFromURI_BelowAPI11(v.getContext(),selectedImage);
                    try {
                        image = readFileAsString(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    FileUplaodTask fileUplaodTask = new FileUplaodTask();
                    fileUplaodTask.execute(image,URL1);

                    break;
            }


            /*
            Missing_Person_register_Task missing_person_register_task = new Missing_Person_register_Task();
            missing_person_register_task.execute(MissingPerson_ID,Disappearance_Address,MP_Name,Disappearance_Date,Missing_age,
                    Missing_height,Missing_Weight,Image_Information_ID,Image_Name, Image_Physical_Address,URL,ID);*/
        }
    };
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        ImageButton imageView = (ImageButton) findViewById(R.id.imageButton);
        if(data != null){
            selectedImage = data.getData();
            try {
                inputStream = this.getContentResolver().openInputStream(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            imageView.setImageBitmap(bitmap);
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
    private static String getStringFromInputStream(InputStream is){
        BufferedReader br = null;
        StringBuilder sb =new StringBuilder();

        String line;
        try{
            br= new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(br != null){
                try{
                    br.close();;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public String getStringFromFile (String filePath) throws Exception {
        File fl = new File(filePath);
        FileInputStream fin = new FileInputStream(fl);
        String ret = getStringFromInputStream(fin);
        fin.close();
        return ret;
    }

    public static String getRealPathFromURI_BelowAPI11(Context context, Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        String result = null;

        try {
            Cursor cursor = context.getContentResolver().query(contentUri, null, null, null, null);
            if (cursor == null) { // Source is Dropbox or other similar local file path
                result = contentUri.getPath();
            } else {
                cursor.moveToFirst();
//                int column_index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);

                result = cursor.getString(6);
            }

        } catch (Exception e) {

            result = null;
        }
        return result;
    }//파일경로로받아 서 파 일 문자열 로바꾸 기
     public static String readFileAsString(String filePath) {
        String result = "";
        File file = new File(filePath);
        if ( file.exists() ) {
            //byte[] buffer = new byte[(int) new File(filePath).length()];
            FileInputStream fis = null;
            try {
                //f = new BufferedInputStream(new FileInputStream(filePath));
                //f.read(buffer);

                fis = new FileInputStream(file);
                char current;
                while (fis.available() > 0) {
                    current = (char) fis.read();
                    result = result + String.valueOf(current);
                }
            } catch (Exception e) {
                Log.d("TourGuide", e.toString());
            } finally {
                if (fis != null)
                    try {
                        fis.close();
                    } catch (IOException ignored) {
                    }
            }
            //result = new String(buffer);
        }
        return result;
    }

}