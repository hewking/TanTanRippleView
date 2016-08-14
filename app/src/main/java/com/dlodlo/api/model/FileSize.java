package com.dlodlo.api.model;



public class FileSize{

    private static final String FIELD_VALUE = "value";


    private int mValue;


    public FileSize(){

    }

    public void setValue(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }


}