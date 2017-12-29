package com.example.maerad7.face_android;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Image_Compare extends AppCompatActivity {
    final int MP_List=20;
    final int MP_register=21;
    final int image_compare=22;
    int IMAGE_CAPTURE=1;
    int LOAD_IMAGE = 104;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image__compare);
        Button Image_Upload_Button =(Button)findViewById(R.id.Image_Upload_Button);

        Button Image_Compare_Button=(Button)findViewById(R.id.Image_Compare_Button);

        Image_Compare_Button.setOnClickListener(onClickListener);
        Image_Upload_Button.setOnClickListener(onClickListener);

    }Button.OnClickListener onClickListener=new Button.OnClickListener(){

        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch(id) {
                case R.id.Image_Upload_Button:
                    // Create the Snackbar
                    LinearLayout.LayoutParams objLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);
                    // Get the Snackbar's layout view
                    Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
                    layout.setPadding(0, 0, 0, 0);
                    // Hide the text
                    TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setVisibility(View.INVISIBLE);

                    LayoutInflater mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    // Inflate our custom view
                    View snackView = getLayoutInflater().inflate(R.layout.my_snackbar, null);
                    // Configure the view
                    TextView textViewOne = (TextView) snackView.findViewById(R.id.txtOne);

                    textViewOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("TwoButtonSnack", "First one is clicked");

                            if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, IMAGE_CAPTURE);

                            }
                        }
                    });

                    TextView textViewTwo = (TextView) snackView.findViewById(R.id.txtTwo);
                    textViewTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("TwoButtonSnack", "Second one is clicked");

                            Intent intent = new Intent();
                            intent.setType("image/*");   //가져오려하는 종류
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(intent, LOAD_IMAGE);

                        }
                    });

                    // Add the view to the Snackbar's layout
                    layout.addView(snackView, objLayoutParams);
                    // Show the Snackbar
                    snackbar.show();
                    break;
/*
                    Snackbar.make(view,"Image_Upload",Snackbar.LENGTH_LONG).setAction("Camera", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
                                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent,IMAGE_CAPTURE);

                            }
                        }
                    }).show();
                      Snackbar.make(view,"Image_Upload2",Snackbar.LENGTH_LONG).setAction("Gallery", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int LOAD_IMAGE=101;
                            Intent intent = new Intent();
                            intent.setType("image/*");   //가져오려하는 종류
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(intent, LOAD_IMAGE);
                        }
                    }).show();*/


                case R.id.Image_Compare_Button:
                    Intent Compare_intent = new Intent(getApplication(), Progress_Image_Compare.class);
                    startActivity(Compare_intent);
                    break;

            }
        }
    };

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
    } protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==104) {
            ImageView imageView = (ImageView) findViewById(R.id.Registration_Image);
            if (data != null) {
                Uri selectedImage = data.getData();
                InputStream inputStream = null;
                try {
                    inputStream = this.getContentResolver().openInputStream(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            }

        }else if(requestCode==1){
            ImageView imageView=(ImageView)findViewById(R.id.Registration_Image);
            if(requestCode== IMAGE_CAPTURE){
                if(resultCode==RESULT_OK){
                    Bundle extras = data.getExtras();
                    Bitmap bitmap=(Bitmap)extras.get("data");
                    imageView.setImageBitmap(bitmap);
                }else{
                    Toast.makeText(getApplicationContext(),"bitmap",Toast.LENGTH_LONG).show();;
                }
            }

        }
    }

}

