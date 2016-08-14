package com.dlodlo.api.model;



public class Mark{

    private static final String FIELD_VALUE = "value";


    private int mValue;


    public Mark(){

    }

    public void setValue(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }


}