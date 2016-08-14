package com.dlodlo.api.model;



public class Id{

    private static final String FIELD_VALUE = "value";


    private int mValue;


    public Id(){

    }

    public void setValue(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }


}