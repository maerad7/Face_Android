package com.example.maerad7.face_android;

import android.os.AsyncTask;

/**
 * Created by maerad7 on 17. 12. 21.
 */

public class Member_Infromation_APITask extends AsyncTask<Void,Void,Object> {


    @Override
    protected Object doInBackground(Void... voids) {
        Member_Infromation_API member_infromation_api=new Member_Infromation_API();
        Member_Information_Json memberInformationJson= member_infromation_api.getMember_Information_Json();
        return memberInformationJson;

    }
}
