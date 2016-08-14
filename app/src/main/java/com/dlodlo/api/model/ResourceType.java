package com.dlodlo.api.model;



public class ResourceType{

    private static final String FIELD_VALUE = "value";


    private int mValue;


    public ResourceType(){

    }

    public void setValue(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }


}