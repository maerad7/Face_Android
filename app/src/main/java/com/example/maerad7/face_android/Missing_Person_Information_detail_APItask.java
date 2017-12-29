package com.example.maerad7.face_android;

import android.os.AsyncTask;

/**
 * Created by maerad7 on 17. 12. 21.
 */
//질 문 asyctask 들어오는 값 처리
public class Missing_Person_Information_detail_APItask extends AsyncTask<Integer, Void, Missing_Person_Information_detail_Json> {

    @Override
    protected Missing_Person_Information_detail_Json doInBackground(Integer... pParams) {

        Missing_Person_Information_detail_API missing_person_information_api= new Missing_Person_Information_detail_API();
        int id=pParams[0];
        Missing_Person_Information_detail_Json missing_person_information_detail_json=missing_person_information_api.getMissing_Person_Information_detail_Json(id);
        return missing_person_information_detail_json;
    }
}
