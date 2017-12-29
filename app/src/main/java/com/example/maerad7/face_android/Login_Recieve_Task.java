package com.example.maerad7.face_android;

import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by maerad7 on 17. 12. 22.
 */

public class Login_Recieve_Task extends AsyncTask<Void,Void,String> {
    @Override
    protected String doInBackground(Void... voids) {
        Login_Recieve login_recieve = new Login_Recieve();
        String flag=login_recieve.getLoginreturn();
        return flag;
    }
}
