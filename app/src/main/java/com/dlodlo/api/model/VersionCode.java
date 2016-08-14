package com.dlodlo.api.model;



public class VersionCode{

    private static final String FIELD_VALUE = "value";


    private int mValue;


    public VersionCode(){

    }

    public void setValue(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }


}