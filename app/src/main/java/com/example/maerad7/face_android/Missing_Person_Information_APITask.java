package com.example.maerad7.face_android;

import android.os.AsyncTask;

/**
 * Created by maerad7 on 17. 12. 19.
 */

public class Missing_Person_Information_APITask extends AsyncTask<Void,Void,Object>{

    @Override
    protected Object doInBackground(Void... voids) {
        Missing_Person_Information_API missing_person_information_api= new Missing_Person_Information_API();
        Missing_Person_Information_Json missing_person_information_json=missing_person_information_api.getMissing_Person_Information_Json();
        return missing_person_information_json;

    }


}
