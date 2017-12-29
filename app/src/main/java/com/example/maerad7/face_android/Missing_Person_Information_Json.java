package com.example.maerad7.face_android;

/**
 * Created by maerad7 on 17. 12. 19.
 */

public class Missing_Person_Information_Json {
    String disappearanceAddress;
    String disappearanceDate;
    String mPName;
    String memberID;
    int missingPersonID;
    String missingWeight;
    String missingage;
    String missingHeight;
    String missingAge;
    String missing_Infromation;
    public String getMissing_Infromation(){
        return missing_Infromation;
    }
    public void setMissing_Infromation(String missing_infromation){
        this.missing_Infromation = missing_infromation;
    }

    public String getDisappearanceAddress() {
        return disappearanceAddress;
    }

    public void setDisappearanceAddress(String disappearanceAddress) {
        this.disappearanceAddress = disappearanceAddress;
    }

    public String getDisappearanceDate() {
        return disappearanceDate;
    }

    public void setDisappearanceDate(String disappearanceDate) {
        this.disappearanceDate = disappearanceDate;
    }

    public String getMPName() {
        return mPName;
    }

    public void setMPName(String mPName) {
        this.mPName = mPName;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public int getMissingPersonID() {
        return missingPersonID;
    }

    public void setMissingPersonID(int missingPersonID) {
        this.missingPersonID = missingPersonID;
    }

    public String getMissingWeight() {
        return missingWeight;
    }

    public void setMissingWeight(String missingWeight) {
        this.missingWeight = missingWeight;
    }

    public String getMissingAge() {
        return missingAge;
    }

    public void setMissingAge(String missingAge) {
        this.missingAge = missingAge;
    }

    public String getMissingHeight() {
        return missingHeight;
    }

    public void setMissingHeight(String missingHeight) {
        this.missingHeight = missingHeight;
    }
}
