package com.example.aprivate.custom_sqlite;

import java.util.UUID;


public class Cards {
    private String mType;
    private String mBank;
    private String mNumber;
    private UUID mID;

    public Cards(){
        mID = UUID.randomUUID();
    }

    public String getmType() {
        return mType;
    }
    public void setmType(String mType) {
        this.mType = mType;
    }
    public String getmBank() {
        return mBank;
    }
    public void setmBank(String mBank) {
        this.mBank = mBank;
    }
    public String getmNumber() {
        return mNumber;
    }
    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }
    public UUID getmID() {
        return mID;
    }
}
