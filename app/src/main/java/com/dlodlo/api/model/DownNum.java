package com.dlodlo.api.model;



public class DownNum{

    private static final String FIELD_VALUE = "value";


    private int mValue;


    public DownNum(){

    }

    public void setValue(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }


}