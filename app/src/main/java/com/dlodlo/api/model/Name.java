package com.dlodlo.api.model;



public class Name{

    private static final String FIELD_VALUE = "value";


    private String mValue;


    public Name(){

    }

    public void setValue(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }


}